package winston

import org.springframework.dao.DataIntegrityViolationException

import sk.upjs.winston.groovy.FileUploadService

class DatasetController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

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
		def file = request.getFile("dataFile")
		def fName = file.getOriginalFilename();
		println "filename: ${fName}"
        def datasetInstance = new Dataset()
		datasetInstance.setTitle(params.get("title"))
		datasetInstance.setDataFile(fName);
		datasetInstance.setDescription(params.get("description"))
		datasetInstance.setMissingValuePattern(params.get("missingValuePattern"))
		println "dataset ${datasetInstance}"
		println ""
		println "file data: ${request.getFile("dataFile").inputStream.text}"
		println ""
		def fileUploadService = new FileUploadService()
		fileUploadService.uploadFile(file, fName, FileUploadService.DATASET_UPLOAD_DIRECTORY)
		println ""
		
//		if some error occures
        if (!datasetInstance.save(flush: true)) {
            render(view: "create", model: [datasetInstance: datasetInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'dataset.label', default: 'Dataset'), datasetInstance.id])
        println "saving dataset ${params}"
        redirect(action: "show", id: datasetInstance.id)
    }

    def show(Long id) {
        def datasetInstance = Dataset.get(id)
        if (!datasetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataset.label', default: 'Dataset'), id])
            redirect(action: "list")
            return
        }

        [datasetInstance: datasetInstance]
    }

    def edit(Long id) {
        def datasetInstance = Dataset.get(id)
        if (!datasetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataset.label', default: 'Dataset'), id])
            redirect(action: "list")
            return
        }

        [datasetInstance: datasetInstance]
    }

    def update(Long id, Long version) {
        def datasetInstance = Dataset.get(id)
        if (!datasetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataset.label', default: 'Dataset'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (datasetInstance.version > version) {
                datasetInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'dataset.label', default: 'Dataset')] as Object[],
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

        flash.message = message(code: 'default.updated.message', args: [message(code: 'dataset.label', default: 'Dataset'), datasetInstance.id])
        redirect(action: "show", id: datasetInstance.id)
    }

    def delete(Long id) {
        def datasetInstance = Dataset.get(id)
        if (!datasetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataset.label', default: 'Dataset'), id])
            redirect(action: "list")
            return
        }

        try {
            datasetInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'dataset.label', default: 'Dataset'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'dataset.label', default: 'Dataset'), id])
            redirect(action: "show", id: id)
        }
    }
}
