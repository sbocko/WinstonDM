package winston

class BooleanAttribute extends Attribute{
	int numberOfTrueValues
	int numberOfFalseValues

    static constraints = {
    }
	
	String toString(){
		return "BooleanAttribute: ${title}"
	}
}
