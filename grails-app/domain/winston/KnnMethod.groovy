package winston

class KnnMethod extends DMMethod {
	int k;
	
    static constraints = {
    }
	
	String toString(){
		return "${title} - k=$k"
	}
}
