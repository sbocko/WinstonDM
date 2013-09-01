package sk.upjs.winston.groovy

import sk.upjs.winston.groovy.validator.BooleanAttributeDataValidator
import sk.upjs.winston.groovy.validator.NumericAttributeDataValidator;
import sk.upjs.winston.groovy.validator.StringAttributeDataValidator;
import winston.Attribute

class DatasetAttributeParser {
	public static final String DEFAULT_DELIMITER = ","
	private File file
	private int numberOfAttributes
	private int numberOfInstances
	private String delimiter
	private String missingValuePattern

	public DatasetAttributeParser(File file, int numberOfInstances, String missingValuePattern) {
		this.file = file
		this.numberOfInstances = numberOfInstances
		this.delimiter = DEFAULT_DELIMITER
		this.missingValuePattern = missingValuePattern
		numberOfAttributes = getNumberOfAttributes()
	}

	public List<Attribute> getAttributes(){
		List<Attribute> resultAttrs = new ArrayList<Attribute>()
		String[][] data = parseDatasetToArrays()
		for(int i = 0; i < data.length; i++){
			resultAttrs.add(createAttributeFromData(data[i]))
		}
		return resultAttrs
	}
	
	private Attribute createAttributeFromData(String[] attrData){
		BooleanAttributeDataValidator badv = new BooleanAttributeDataValidator(attrData, missingValuePattern)
		if(badv.isApplicableToData()){
			return badv.createAttributeFromData()
		}
		NumericAttributeDataValidator nadv = new NumericAttributeDataValidator(attrData, missingValuePattern)
		if(nadv.isApplicableToData()){
			return nadv.createAttributeFromData()
		}
		
		return new StringAttributeDataValidator(attrData, missingValuePattern).createAttributeFromData()
	}

	private String[][] parseDatasetToArrays(){
		String[][] resultData = new String[numberOfAttributes][numberOfInstances]
		int actLine = 0
		file.eachLine { line ->
			def values = line.split(delimiter)
			values.eachWithIndex { val, idx ->
				resultData[idx][actLine] = val.trim()
			}
			actLine++ 
		}
		return resultData
	}

	private int getNumberOfAttributes(){
		def firstLine
		file.withReader { firstLine = it.readLine() }
		def result = firstLine.split(delimiter).length
		return result
	}
}
