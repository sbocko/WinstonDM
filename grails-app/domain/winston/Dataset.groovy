package winston

/**
 * 
 * @author Stefan Bocko
 * This class is fundamental domain model which represents one dataset entry.
 * It contains metadata of dataset as well as metadata of all of its attributes. 
 *
 */
class Dataset {
	public static final String TITLE_VAR = "title"
	public static final String DATA_FILE_VAR = "dataFile"
	public static final String DESCRIPTION_VAR = "description"
	public static final String MISSING_VALUE_PATTERN_VAR = "missingValuePattern"
	public static final String NUMBER_OF_MISSING_VALUES_VAR = "numberOfMissingValues"
	public static final String NUMBER_OF_INSTANCES_VAR = "numberOfInstances"
	
	String title
	String dataFile
	String description
	String missingValuePattern
	int numberOfMissingValues
	int numberOfInstances
	List attributes
    static hasMany = [attributes: Attribute, dmMethods: DMMethod]

    static constraints = {
    	title()
    	dataFile(nullable:false)
    	description(maxSize:5000, nullable:true)
    	missingValuePattern(nullable:true)
    	numberOfMissingValues()
    	numberOfInstances()
    }

    String toString(){
        return "${title}: ${dataFile}"
    }
}
