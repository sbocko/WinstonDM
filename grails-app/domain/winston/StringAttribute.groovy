package winston

class StringAttribute extends Attribute{

    static constraints = {
    }
	
	String toString(){
		return "StringAttribute: ${title}"
	}
}
