package sk.upjs.winston.groovy

import winston.Attribute

class DatasetAttributeParser {
	public static final String DEFAULT_DELIMITER = ","
	File file
	int numberOfAttributes
	public DatasetAttributeParser(File file) {
		this.file = file;
		numberOfAttributes = getNumberOfAttributes()
	}

	public List<Attribute> getAttributes(){
	}

	private List<Attribute> parseDataset(){
	}

	private int getNumberOfAttributes(){
		def delimiter = DEFAULT_DELIMITER
		def line
		file.withReader { line = it.readLine() }
		def result = line.split(delimiter).length
		println "length: ${result}"
		return result
	}
}
