package winston

class DMMethod {
	String title;

	static belongsTo = [dataset: Dataset]
	
    static constraints = {
    }
	
	String toString(){
		return "${title}"
	}
}
