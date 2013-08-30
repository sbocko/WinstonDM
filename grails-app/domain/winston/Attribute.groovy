package winston

class Attribute {
	String title
	int numberOfMissingValues

	static belongsTo = [dataset: Dataset]

    static constraints = {
    }

    String toString(){
    	return title
    }
}
