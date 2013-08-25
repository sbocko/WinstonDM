package winston

import sk.upjs.winston.groovy.AttributeType;

class Attribute {
	String title
	AttributeType attributeType
	int numberOfMissingValues

	static belongsTo = [dataset: Dataset]

    static constraints = {
    }

    String toString(){
    	return title
    }
}
