package sk.upjs.winston.groovy.validator

import org.springframework.uaa.client.VersionHelper.GetNumbersResult;

import winston.Attribute

class DefaultAttributeDataValidator implements AttributeDataValidator {
	private String[] data
	private String missingValue
	
	public DefaultAttributeDataValidator(String[] data, String missingValue){
		this.data = data
		this.missingValue = missingValue
	}

	/**
	 * DefaultAttributeDataValidator is always applicable to its data
	 * @return true
	 */
	@Override
	public boolean isApplicableToData() {
		return true;
	}

	@Override
	public Attribute createAttributeFromData() {
		if(!isApplicableToData()){
			//should not happen
			return null
		}
		
		Attribute defaultAttr = new Attribute()
		defaultAttr.setNumberOfMissingValues(countMissingValues())
		return defaultAttr
	}

	private int countMissingValues(){
		int result = 0;
		data.each { value ->
			if(value.equals(missingValue)){
				result++
			}
		}
		return result
	}
}
