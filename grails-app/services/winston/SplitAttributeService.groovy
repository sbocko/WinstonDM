package winston

import org.apache.commons.io.FileUtils;
import org.codehaus.groovy.grails.web.context.ServletContextHolder;

import sk.upjs.winston.groovy.DatasetAttributeParser;
import sk.upjs.winston.groovy.validator.AttributeDataValidator;

class SplitAttributeService {
	//default value is true
	static transactional = true

	public static final String PREPARED_DATAFILES_DIRECTORY = "/prepared-datasets"
	private static final String CONTAINS_ATTRIBUTE_VALUE = "1"
	private static final String DOES_NOT_CONTAIN_ATTRIBUTE_VALUE = "0"

	public String splitDatasetAttributesIntoFile(Dataset dataset, Map<Attribute, Boolean> attributesToSplit){
		if(!attributesBelongsToDataset(dataset, attributesToSplit)){
			return null
		}

		String[][] datasetAttributesData = getDatasetAttributesData(dataset)
		List<String[]> dataToSave = splitAttributes(datasetAttributesData,attributesToSplit)

		return saveDataToFile(dataToSave, dataset.getTitle())
	}

	private String saveDataToFile(List<String[]> data, String datasetTitle){
		// get storage path
		def servletContext = ServletContextHolder.servletContext
		def storagePath = servletContext.getRealPath(PREPARED_DATAFILES_DIRECTORY)
		//	Create storage path directory if it does not exist
		def storagePathDirectory = new File(storagePath)
		if(!storagePathDirectory.exists()){
			print "CREATING DIRECTORY ${storagePath}"
			if(storagePathDirectory.mkdirs()){
				println "SUCCESS"
			}else{
				println "FAILED"
			}
		}

		def file = new File("${storagePathDirectory}/${datasetTitle}.csv");
		// delete old file
		if(file.exists()){
			file.delete()
		}
		file.createNewFile();

		for(int i = 0; i < data.get(0).length; i++){
			String toWrite = ""
			for(int j = 0; j < data.size()-1; j++){
				toWrite += data.get(j)[i] + DatasetService.DEFAULT_DELIMITER
			}
			//last line without new line character
			if(i == data.get(0).length-1){
				toWrite += data.get(data.size()-1)[i]
			}else{
				toWrite += data.get(data.size()-1)[i] + "\n"
			}
			FileUtils.writeStringToFile(file, toWrite, true)
		}

		return file.getName()
	}

	private List<String[]> splitAttributes(String[][] datasetAttributesData, Map<Attribute, Boolean> attributesToSplit){
		List<String[]> result = new ArrayList<String[]>()

		for (int i = 0; i < datasetAttributesData.length; i++) {
			attributesToSplit.each { attr, split ->
				if(attr.getPositionInDataFile() == i){
					result.addAll(getNewAttributeData(datasetAttributesData[i], attr, split))
				}
			}
		}

		println "resultSize: ${result.size()}"
		return result
	}

	/*
	 *  @param splitAttribute null if this attribute should not be devided
	 */
	private List<String[]> getNewAttributeData(String[] attributeData, Attribute splitAttribute, boolean split){
		List<String[]> result = new ArrayList<String[]>()

		if(!split){
			String[] data = addAttributeTitleToData(attributeData, splitAttribute.getTitle()) 
			result.add(data)
			return result
		}

		Map<String, Integer> attributeValuePositionMap = new HashMap<String, Integer>()
		for (int i = 0; i < attributeData.length; i++) {
			String dataValue = attributeData[i]
			if(attributeValuePositionMap.containsKey(dataValue)){
				int dataValueIndex = attributeValuePositionMap.get(dataValue)
				println "attrDataLength: ${attributeData.length}"
				result.get(dataValueIndex)[i+1] = CONTAINS_ATTRIBUTE_VALUE
			}else{
				String[] newColumn = initializeArrayWithValue(attributeData.length+1, DOES_NOT_CONTAIN_ATTRIBUTE_VALUE)
				newColumn[0] = splitAttribute.title + ":" + dataValue
				newColumn[i+1] = CONTAINS_ATTRIBUTE_VALUE
				result.add(newColumn)
				attributeValuePositionMap.put(dataValue, result.size()-1)
			}
		}

		return result
	}
	
	private String[] addAttributeTitleToData(String[] data, String title){
		String[] result = new String[data.length+1]
		result[0] = title
		for(int i = 1; i < result.length; i++){
			result[i] = data[i-1]
		}
		return result
	}

	private String[][] getDatasetAttributesData(Dataset dataset){
		def servletContext = ServletContextHolder.servletContext
		def storagePath = servletContext.getRealPath(FileUploadService.DATASET_UPLOAD_DIRECTORY)

		File file = new File(storagePath + "/" + dataset.getDataFile())

		DatasetAttributeParser dap = new DatasetAttributeParser(file, dataset.getMissingValuePattern(), DatasetService.DEFAULT_DELIMITER)
		return dap.parseDatasetToArrays()
	}

	private boolean attributesBelongsToDataset(Dataset dataset, Map<Attribute, Boolean> attributesToSplit){
		attributesToSplit.each { key , value ->
			if(!key.dataset.equals(dataset)){
				return false
			}
		}
		return true
	}

	private String[] initializeArrayWithValue(int length, String value){
		String[] array = new String[length]
		for(int i = 0; i < array.length; i++){
			array[i] = value
		}
		return array
	}

}
