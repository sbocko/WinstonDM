package winston

class Dataset {
	private static final String TITLE_VAR = "title"
	private static final String DATAFILE_VAR = "dataFile"
	private static final String DESCRIPTION_VAR = "description"
	private static final String MISSING_VALUE_PATTERN_VAR = "missingValuePattern"
	private static final String TITLE_NUMBER_OF_MISSING_VALUES_VAR = "numberOfMissingValues"
	private static final String NUMBER_OF_INSTANCES_VAR = "numberOfInstances"
	
	
	String title
	String dataFile
	String description
	String missingValuePattern
	int numberOfMissingValues
	int numberOfInstances
    static hasMany = [attributes: Attribute]

    static constraints = {
    	title()
    	dataFile()
    	description(maxSize:5000, nullable:true)
    	missingValuePattern(nullable:true)
    	numberOfMissingValues()
    	numberOfInstances()
    }

    String toString(){
        return "${title}: ${dataFile}"
    }
}
