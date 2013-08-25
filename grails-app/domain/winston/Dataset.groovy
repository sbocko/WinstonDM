package winston

class Dataset {
	String title
	String dataFile
	String description
	String descriptionFile
	String missingValuePattern
	int numberOfMissingValues
	int numberOfInstances
    static hasMany = [attributes: Attribute]

    static constraints = {
    	title()
    	dataFile()
    	description(maxSize:5000)
    	descriptionFile(nullable:true)
    	missingValuePattern(nullable:true)
    	numberOfMissingValues()
    	numberOfInstances()
    }

    String toString(){
        return "${title}: ${dataFile}"
    }
}
