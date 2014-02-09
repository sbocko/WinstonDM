package winston

/**
 * 
 * @author Stefan Bocko
 *	This domain class represents general dataset attribute.
 *	Dataset objects consists of StringAttributes, NumericAttributes, BooleanAttributes..., which describes data more accurate.
 *	These classes are inherited from Attribute class.
 */
class Attribute {
	String title
	int numberOfMissingValues
	boolean isTarget
	//the position is zero based
	int positionInDataFile

	static belongsTo = [dataset: Dataset]

	static constraints = { title(nullable:true)  }

	String toString(){
		return "${title} - missingValues: ${numberOfMissingValues}"
	}
}
