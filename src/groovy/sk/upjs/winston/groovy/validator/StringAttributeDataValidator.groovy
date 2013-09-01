package sk.upjs.winston.groovy.validator

import java.util.Map;

import winston.Attribute
import winston.StringAttribute

class StringAttributeDataValidator implements AttributeDataValidator {
	private String[] data
	private String missingValue

	public StringAttributeDataValidator(String[] data, String missingValue){
		this.data = data
		this.missingValue = missingValue
	}

	/**
	 * StringAttributeDataValidator is always applicable to its data
	 * @return true
	 */
	@Override
	public boolean isApplicableToData() {
		return true;
	}

	/**
	 * Variable numberOfDistinctValues of StringAttribute represents the number of distinct values missingValue do NOT count
	 */
	@Override
	public Attribute createAttributeFromData() {
		if(!isApplicableToData()){
			//should not happen
			return null
		}

		StringAttribute stringAttr = new StringAttribute()

		def valuesMap = countOccurences()
		if(valuesMap.containsKey(missingValue)){
			stringAttr.setNumberOfMissingValues(valuesMap.get(missingValue))
			stringAttr.setNumberOfDistinctValues(valuesMap.size()-1)
		}else{
			stringAttr.setNumberOfMissingValues(0)
			stringAttr.setNumberOfDistinctValues(valuesMap.size())
		}
		return stringAttr
	}

	private Map<String, Integer> countOccurences(){
		Map<String, Integer> result = new HashMap<String, Integer>()
		data.each {
			if(it == null){
				return
			}
			if(result.containsKey(it)){
				result.put(it, result.get(it)+1)
			}else{
				result.put(it, 1);
			}
		}
		return result
	}
}
