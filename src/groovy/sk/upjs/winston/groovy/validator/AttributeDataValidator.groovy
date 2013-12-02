package sk.upjs.winston.groovy.validator

import winston.Attribute

/**
 * 
 * @author Stefan Bocko
 * Interface for Data validators. These classes has two important methods. 
 * The isApplicableToData() method returns true if attribute's data are applicable to given Attribute class.
 * The createAttributeFromData() creates given Attribute if the data are applicable for that attribute type.
 *
 */
interface AttributeDataValidator {
	public boolean isApplicableToData()
	public Attribute createAttributeFromData()
}
