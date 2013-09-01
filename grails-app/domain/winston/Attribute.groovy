package winston

class Attribute {
	String title
	int numberOfMissingValues

	static belongsTo = [dataset: Dataset]

    static constraints = {
		title(nullable:true) 
    }

    String toString(){
    	return "${title} - missingValues: ${numberOfMissingValues}"
    }
}
