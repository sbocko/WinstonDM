package sk.upjs.winston.groovy.validator

import winston.Attribute

interface AttributeDataValidator {
	public boolean isApplicableToData()
	public Attribute createAttributeFromData()
}
