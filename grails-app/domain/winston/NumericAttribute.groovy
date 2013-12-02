package winston

class NumericAttribute extends Attribute{
	double average
	int distinctValues

    static constraints = {
    }
	
	String toString(){
		return "NumericAttribute: ${title}"
	}
}
