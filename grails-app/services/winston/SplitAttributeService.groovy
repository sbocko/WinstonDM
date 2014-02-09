package winston

import org.codehaus.groovy.grails.web.context.ServletContextHolder;

import sk.upjs.winston.groovy.DatasetAttributeParser;

class SplitAttributeService {
	//default value is true
	static transactional = true

		
	public String splitDatasetAttributesIntoFile(Dataset dataset, List<Attribute> attributesToSplit){
		if(!attributesBelongsToDataset(dataset, attributesToSplit)){
			return null
		}
		
		String[][] datasetAttributesData = getDatasetAttributesData(dataset)
		
		println "length: ${datasetAttributesData.length}"
		
		return "IT WORKS!"
	}
	
	private String[][] getDatasetAttributesData(Dataset dataset){
		def servletContext = ServletContextHolder.servletContext
		def storagePath = servletContext.getRealPath(FileUploadService.DATASET_UPLOAD_DIRECTORY)
		
		File file = new File(storagePath + "/" + dataset.getDataFile())
		
		DatasetAttributeParser dap = new DatasetAttributeParser(file, dataset.getMissingValuePattern(), DatasetService.DEFAULT_DELIMITER)
		return dap.parseDatasetToArrays()
	}
	
	private boolean attributesBelongsToDataset(Dataset dataset, List<Attribute> attributesToSplit){
		attributesToSplit.each { attr ->
			if(!attr.dataset.equals(dataset)){
				return false
			}
		}
		return true
	}

}
