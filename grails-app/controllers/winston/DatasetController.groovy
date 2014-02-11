package winston

import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile

import sk.upjs.winston.groovy.DatasetAttributeParser

class DatasetController {
	def datasetService
	def splitAttributeService
	private static final double MIN_PERCENT_OF_DISTINCT_VALUES = 0.05

	static allowedMethods = [save: "POST", delete: "POST"]

	def servletContext

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

	/**
	 * Parses form for creating new dataset, creates attributes and counts missing values...
	 * Saves this dataset to database.
	 * 
	 */
	def save() {
		//get dataset attribute values
		def file = request.getFile(Dataset.DATA_FILE_VAR)
		File myFile = new File(file.getOriginalFilename())
		file.transferTo(myFile)
		def missingValuePattern = params.get(Dataset.MISSING_VALUE_PATTERN_VAR)
		def title = params.get(Dataset.TITLE_VAR)
		def description = params.get(Dataset.DESCRIPTION_VAR)

		def datasetInstance = datasetService.saveDataset(title, description, myFile, missingValuePattern)

		//println "dataset ${datasetInstance}"

		//if some error occures
		if (!datasetInstance) {
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

	def analyze(Long id) {
		def datasetInstance = Dataset.get(id)
		if (!datasetInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'dataset.label', default: 'Dataset'),
				id
			])
			redirect(action: "list")
			return
		}
		
		datasetInstance.attributes.each { attr ->
			if(attr.isTarget){
				params.currentTargetAttribute = attr.id;
			}
		}
		if(!params.currentTargetAttribute){
			params.currentTargetAttribute = "noTargetAttribute"
		}

		render(view: "analyze", model: [datasetInstance: datasetInstance])
	}
	
	def attributeAnalysis(Long id) {
		def datasetInstance = Dataset.get(id)
		if (!datasetInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'dataset.label', default: 'Dataset'),
				id
			])
			redirect(action: "list")
			return
		}
		
		
		def attrIdList = new ArrayList<Attribute>()
		/*
		 * get attributes, which has less than MIN_PERCENT_OF_DISTINCT_VALUES
		 */
		def minCount = datasetInstance.getNumberOfInstances()*MIN_PERCENT_OF_DISTINCT_VALUES
		datasetInstance.attributes.each { attr ->
			if(attr instanceof NumericAttribute && attr.getNumberOfDistinctValues() < minCount && !attr.isTarget){
				attrIdList.add(attr)
			}
		}

		render(view: "attribute_analysis", model: [datasetInstance: datasetInstance, attrIdList: attrIdList])
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
			datasetService.deleteDatasetFile(id)
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
	
	def saveTargetAttribute(Long id){
		def datasetInstance = Dataset.get(id)
		if (!datasetInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'dataset.label', default: 'Dataset'),
				id
			])
			redirect(action: "list")
			return
		}
		
		def answer = params.targetAttributeRadioGroup
		datasetInstance.attributes.each { attr ->
			if(attr.id == Long.parseLong(answer)){
				attr.isTarget = true
				attr.save()
			}else if(attr.isTarget){
				attr.isTarget = false
				attr.save()
			}
		}
		
		redirect(action: "attributeAnalysis", id: params.id);
	}
	
	def prepareData(Long id){
		def datasetInstance = Dataset.get(id)
		if (!datasetInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'dataset.label', default: 'Dataset'),
				id
			])
			redirect(action: "list")
			return
		}
		
		/*
		 * check which attributes will be splitted based on user input
		 */
		Map<Attribute,Boolean> attributesToSplit = new HashMap<Attribute, Boolean>()
		datasetInstance.attributes.each { attr ->
			if(params.get('radioGroup'+attr.getId()) == "1"){
				attributesToSplit.put(attr,true)
			}else{
				attributesToSplit.put(attr, false)
			}
		}
		
		splitAttributeService.splitDatasetAttributesIntoFile(datasetInstance, attributesToSplit)
		
		redirect(action: "attributeAnalysis", id: id)
	}

}
