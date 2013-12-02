package winston

class StringAttribute extends Attribute{
	int numberOfDistinctValues

    static constraints = {
    }
	
	String toString(){
		return "StringAttribute: ${title}"
	}
}
