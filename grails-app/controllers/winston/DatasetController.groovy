package winston

import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile

import sk.upjs.winston.groovy.DatasetAttributeParser
import sk.upjs.winston.groovy.FileUploadService

class DatasetController {

	static allowedMethods = [save: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[datasetInstanceList: Dataset.list(params), datasetInstanceTotal: Dataset.count()]
	}

	def create() {
		[datasetInstance: new Dataset(params)]
	}

	def save() {
		//get file
		def file = request.getFile(Dataset.DATA_FILE_VAR)
		def filename = null
		if(!file.isEmpty()){
			filename = file.getOriginalFilename();
			println "filename: ${filename}"
		}

		int numberOfInstances = getNumberOfInstances(file)

		//get storage path and parse attributes
		def servletContext = ServletContextHolder.servletContext
		def storagePath = servletContext.getRealPath(FileUploadService.DATASET_UPLOAD_DIRECTORY)
		File f = new File("${storagePath}/${filename}")
		def missingValuePattern = params.get(Dataset.MISSING_VALUE_PATTERN_VAR)

		//initialize dataset instance
		Dataset datasetInstance = new Dataset()
		datasetInstance.setTitle(params.get(Dataset.TITLE_VAR))
		datasetInstance.setDataFile(filename);
		datasetInstance.setDescription(params.get(Dataset.DESCRIPTION_VAR))
		datasetInstance.setMissingValuePattern(missingValuePattern)
		datasetInstance.setNumberOfMissingValues(getNumberOfMissingValues(file, missingValuePattern));
		datasetInstance.setNumberOfInstances(numberOfInstances)

		DatasetAttributeParser dap = new DatasetAttributeParser(f, numberOfInstances, missingValuePattern)
		List<Attribute> attrs = dap.getAttributes()
		for(int i = 0; i < attrs.size(); i++){
			def attr = attrs.get(i)
			attr.save()
			datasetInstance.addToAttributes(attr)
		}

		//parse file and get attributes


		//println "dataset ${datasetInstance}"
		println ""

		//upload file
		def fileUploadService = new FileUploadService()
		fileUploadService.uploadFile(file, filename, FileUploadService.DATASET_UPLOAD_DIRECTORY)

		//if some error occures
		if (!datasetInstance.save(flush: true)) {
			render(view: "create", model: [datasetInstance: datasetInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'dataset.label', default: 'Dataset'),
			datasetInstance.id
		])
		redirect(action: "show", id: datasetInstance.id)
	}

	def show(Long id) {
		def datasetInstance = Dataset.get(id)
		if (!datasetInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'dataset.label', default: 'Dataset'),
				id
			])
			redirect(action: "list")
			return
		}

		[datasetInstance: datasetInstance]
	}

	def edit(Long id) {
		def datasetInstance = Dataset.get(id)
		if (!datasetInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'dataset.label', default: 'Dataset'),
				id
			])
			redirect(action: "list")
			return
		}

		[datasetInstance: datasetInstance]
	}

	def update(Long id, Long version) {
		def datasetInstance = Dataset.get(id)
		if (!datasetInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'dataset.label', default: 'Dataset'),
				id
			])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (datasetInstance.version > version) {
				datasetInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'dataset.label', default: 'Dataset')] as Object[],
						"Another user has updated this Dataset while you were editing")
				render(view: "edit", model: [datasetInstance: datasetInstance])
				return
			}
		}
		datasetInstance.properties = params

		if (!datasetInstance.save(flush: true)) {
			render(view: "edit", model: [datasetInstance: datasetInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'dataset.label', default: 'Dataset'),
			datasetInstance.id
		])
		redirect(action: "show", id: datasetInstance.id)
	}

	def delete(Long id) {
		def datasetInstance = Dataset.get(id)
		if (!datasetInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'dataset.label', default: 'Dataset'),
				id
			])
			redirect(action: "list")
			return
		}

		try {
			deleteDataFile(datasetInstance.getDataFile())
			datasetInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'dataset.label', default: 'Dataset'),
				id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'dataset.label', default: 'Dataset'),
				id
			])
			redirect(action: "show", id: id)
		}
	}

	private def getNumberOfMissingValues(MultipartFile file, String missingValuePattern){
		if(missingValuePattern == null || missingValuePattern.length() == 0){
			return 0
		}
		def data = new String(file.getBytes())
		StringUtils.countOccurrencesOf(data,missingValuePattern)
	}

	private def getNumberOfInstances(MultipartFile multipartFile){
		def result = 0
		//get storage path
		def servletContext = ServletContextHolder.servletContext
		def storagePath = servletContext.getRealPath(FileUploadService.DATASET_UPLOAD_DIRECTORY)

		def filename = multipartFile.getOriginalFilename()
		File file = new File("${storagePath}/${filename}")
		if(!file.exists()){
			println "file ${file.getPath()} does not exists!"
			return 0
		}

		file.eachLine { line ->
			if(line != null && line.trim().length() > 0){
				result++
			}
		}
		return result
	}

	private void deleteDataFile(String filename){
		//get storage path
		def servletContext = ServletContextHolder.servletContext
		def storagePath = servletContext.getRealPath(FileUploadService.DATASET_UPLOAD_DIRECTORY)

		File file = new File("${storagePath}/${filename}")
		if(file.exists()){
			file.delete()
		}
	}
}
