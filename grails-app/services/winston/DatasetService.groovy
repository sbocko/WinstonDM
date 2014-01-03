package winston

import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile

import com.sun.org.apache.xalan.internal.xsltc.compiler.ForEach;

import sk.upjs.winston.groovy.DatasetAttributeParser

class DatasetService {
	//default value is true
	static transactional = true

	def fileUploadService

	public static final String DEFAULT_DELIMITER = ","
	private String delimiter = DEFAULT_DELIMITER

	public def saveDataset(def title, def description, def file, def missingValuePattern) {
		println "Dataset file: ${file}"
		def filename = null
		if(file != null && file.size() != 0){
			filename = file.getName();
			println "Dataset filename: ${filename}"
		}

		//get storage path
		def servletContext = ServletContextHolder.servletContext
		def storagePath = servletContext.getRealPath(FileUploadService.DATASET_UPLOAD_DIRECTORY)

		//upload file
		//		fileUploadService = new FileUploadService()
		//		def filePath = fileUploadService.uploadFile(file, filename, FileUploadService.DATASET_UPLOAD_DIRECTORY)
		//		File f = new File("${filePath}")

		//initialize dataset instance
		Dataset datasetInstance = new Dataset()
		datasetInstance.setTitle(title)
		datasetInstance.setDataFile(filename);
		datasetInstance.setDescription(description)
		datasetInstance.setMissingValuePattern(missingValuePattern)
		datasetInstance.setNumberOfMissingValues(getNumberOfMissingValues(file, missingValuePattern));

		//parse file and get attributes
		DatasetAttributeParser dap = new DatasetAttributeParser(file, missingValuePattern, delimiter)
		datasetInstance.setNumberOfInstances(dap.getNumberOfInstances())
		List<Attribute> attrs = dap.getAttributes()
		for(int i = 0; i < attrs.size(); i++){
			def attr = attrs.get(i)
			attr.save()
			datasetInstance.addToAttributes(attr)
		}

		return datasetInstance.save(flush: true)
	}

	public def deleteDatasetFile(Long id){
		def datasetInstance = Dataset.get(id)
		deleteDataFile(datasetInstance.getDataFile())
	}

	private def getNumberOfMissingValues(def file, String missingValuePattern){
		if(missingValuePattern == null || missingValuePattern.length() == 0){
			return 0
		}
		def data = new String(file.getText())
		//		println "DATA SIZE: ${data.class}"
		StringUtils.countOccurrencesOf(data,missingValuePattern)
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
