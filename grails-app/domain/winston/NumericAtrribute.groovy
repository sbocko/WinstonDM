package winston

class NumericAtrribute extends Attribute{
	double average
	int distinctValues

    static constraints = {
    }
	
	String toString(){
		return "NumericAttribute: ${title}"
	}
}
