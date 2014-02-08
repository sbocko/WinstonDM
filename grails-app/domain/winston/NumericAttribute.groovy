package winston

class NumericAttribute extends Attribute{
	double average
	double minimum
	double maximum
	int numberOfDistinctValues

    static constraints = {
    }
	
	String toString(){
		return "NumericAttribute: ${title}"
	}
}
