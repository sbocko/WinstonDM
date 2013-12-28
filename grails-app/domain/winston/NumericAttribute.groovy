package winston

class NumericAttribute extends Attribute{
	double average
	double minimum
	double maximum
	int distinctValues

    static constraints = {
    }
	
	String toString(){
		return "NumericAttribute: ${title}"
	}
}
