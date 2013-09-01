package sk.upjs.winston.groovy.validator

import winston.Attribute;
import winston.BooleanAttribute;

class BooleanAttributeDataValidator implements AttributeDataValidator{
	private static final String TRUE = "true"
	private static final String FALSE = "false"
	private String[] data
	private String missingValue

	public BooleanAttributeDataValidator(String[] data, String missingValue){
		this.data = data
		this.missingValue = missingValue
	}

	@Override
	public boolean isApplicableToData() {
		for(int i = 0; i < data.length; i++){
			def value = data[i]
			if(!value.toLowerCase().equals(TRUE) && !value.toLowerCase().equals(FALSE) && !value.equals(missingValue)){
				return false
			}
		}
		return true;
	}

	@Override
	public Attribute createAttributeFromData() {
		if(!isApplicableToData()){
			return null;
		}
		BooleanAttribute resultAttr = new BooleanAttribute()

		Map<String, Integer> occurences = countOccurences()
		//for missing values
		if(occurences.containsKey(missingValue)){
			resultAttr.setNumberOfMissingValues(occurences.get(missingValue))
		}else{
			resultAttr.setNumberOfMissingValues(0)
		}
		//for true values
		if(occurences.containsKey(TRUE)){
			resultAttr.setNumberOfTrueValues(occurences.get(TRUE))
		}else{
			resultAttr.setNumberOfTrueValues(0)
		}
		//for false values
		if(occurences.containsKey(FALSE)){
			resultAttr.setNumberOfFalseValues(occurences.get(FALSE))
		}else{
			resultAttr.setNumberOfFalseValues(0)
		}

		return resultAttr
	}

	private Map<String, Integer> countOccurences(){
		Map<String, Integer> result = new HashMap<String, Integer>()
		data.each {
			if(it == null){
				return
			}
			if(!it.equals(missingValue)){
				it = it.toLowerCase()
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
