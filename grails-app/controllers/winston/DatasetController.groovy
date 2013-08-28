package winston

import groovy.sql.DataSet;

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartFile;

import org.springframework.util.StringUtils
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
		
		//initialize dataset instance
		def datasetInstance = new Dataset()
		datasetInstance.setTitle(params.get(Dataset.TITLE_VAR))
		datasetInstance.setDataFile(filename);
		datasetInstance.setDescription(params.get(Dataset.DESCRIPTION_VAR))
		def missingValuePattern = params.get(Dataset.MISSING_VALUE_PATTERN_VAR)
		datasetInstance.setMissingValuePattern(missingValuePattern)
		datasetInstance.setNumberOfMissingValues(getNumberOfMissingValues(file, missingValuePattern));
		datasetInstance.setNumberOfInstances(getNumberOfInstances(file))
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
		println "update params: ${params}"
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
	
	private def getNumberOfInstances(MultipartFile file){
		if(file.isEmpty()){
			return 0
		}
		def data = new String(file.getBytes())
		return StringUtils.countOccurrencesOf(data,"\n") + 1
	}
}
