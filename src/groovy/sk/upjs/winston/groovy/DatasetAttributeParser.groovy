package sk.upjs.winston.groovy

import java.io.File;
import java.util.List;

import org.codehaus.groovy.ast.stmt.ContinueStatement;

import sk.upjs.winston.groovy.validator.BooleanAttributeDataValidator
import sk.upjs.winston.groovy.validator.NumericAttributeDataValidator;
import sk.upjs.winston.groovy.validator.StringAttributeDataValidator;
import winston.Attribute

/**
 * 
 * @author Stefan Bocko
 * This class can parse data file and return List of Attributes of appropriate type.
 *
 */
class DatasetAttributeParser {
	private File file
	private int numberOfAttributes
	private int numberOfInstances
	private String delimiter
	private String missingValuePattern
	private boolean hasAttributeTitlesInFirstLine = false

	public DatasetAttributeParser(File file, String missingValuePattern, String delimiter) {
		this.file = file
		this.delimiter = delimiter
		this.missingValuePattern = missingValuePattern
		this.numberOfInstances = calculateNumberOfInstances()
		this.hasAttributeTitlesInFirstLine = hasFirstLineAttributeTitles()
		numberOfAttributes = getNumberOfAttributes()
	}

	public List<Attribute> getAttributes(){
		List<Attribute> resultAttrs = new ArrayList<Attribute>()
		println "Parsing dataset."
		String[][] data = parseDatasetToArrays()
		for(int i = 0; i < data.length; i++){
			resultAttrs.add(createAttributeFromData(data[i]))
		}
		
		if(hasAttributeTitlesInFirstLine){
			addAttributeTitles(resultAttrs)
		}
		
		println "Dataset parsed."
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
		println "ATTRIBUTES: ${numberOfAttributes} , INSTANCES: ${numberOfInstances}"
		String[][] resultData = new String[numberOfAttributes][numberOfInstances]
		int actLineIndex = 0
		file.eachLine { line, fileLineIdx ->
			if(hasAttributeTitlesInFirstLine && fileLineIdx == 1){
				// skip the line with attribute names
			}else{
				def values = line.split(delimiter)
				values.eachWithIndex { val, idx ->
					resultData[idx][actLineIndex] = val.trim()
				}
				actLineIndex++
			}
		}
		return resultData
	}

	/*
	 * Splits the first line of file by defined delimiter and returns 
	 * the length of this array. When the first line contains attribute 
	 * names, then the second line is used.
	 * @return number of attributes in dataset file
	 */
	private int getNumberOfAttributes(){
		def line
		file.withReader {
			line = it.readLine()
			if(hasAttributeTitlesInFirstLine){
				line = it.readLine()
			}
		}
		def result = line.split(delimiter).length
		return result
	}
	
	private def calculateNumberOfInstances(){
		def result = 0
		if(hasFirstLineAttributeTitles()){
			result = -1
		}

		if(file == null){
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

	private boolean hasFirstLineAttributeTitles(){
		def line
		file.withReader { line = it.readLine()}
		def list = parseStringToList(line);
		list.each { item ->
			try{
				Double.parseDouble(item);
			}catch(NumberFormatException e){
				return false;
			}
		}
		return true;
	}

	/*
	 * The method returns a list containing strings
	 * from input line separated by delimiter. The only
	 * exception are values between " and ". In this
	 * case the delimiter is ignored.
	 */
	private List<String> parseStringToList(String line){
		def list = new ArrayList<String>()
		println "INITIAL LINE: ${line}"
		while(line.length() != 0){
			line = line.trim()
			if(line.startsWith("\"")){
				line = line.substring(1)
				int endIdx = line.indexOf("\"")
				list.add(line.substring(0, endIdx))
				line = line.substring(endIdx)
				line = line.substring(line.indexOf(delimiter)+1);
			}else{
				if(!line.contains(delimiter)){
					list.add(line)
					break
				}
				list.add(line.substring(0,line.indexOf(delimiter)))
				line = line.substring(line.indexOf(delimiter)+1)
			}
		}
		return list
	}
	
	/*
	 * This method reads the first line of dataset file 
	 * which contains the attribute titles and assigns them 
	 * to appropriate attributes. If the first line does not 
	 * contain attribute titles, then the original list is 
	 * returned.
	 */
	private List<Attribute> addAttributeTitles(List<Attribute> attributeList){
		if(hasAttributeTitlesInFirstLine){
			def line
			file.withReader { line = it.readLine()}
			def list = parseStringToList(line);
			println "list size: ${list.size()} and data: ${list}"
			list.eachWithIndex { item, idx ->
				attributeList.get(idx).setTitle(item)
			}
		}
		return attributeList
	}
	
	public def getNumberOfInstances(){
		return numberOfInstances
	}
}
