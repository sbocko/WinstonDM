package sk.upjs.winston.groovy.validator

import java.util.Map;

import winston.Attribute
import winston.NumericAttribute;

class NumericAttributeDataValidator implements AttributeDataValidator {
	private String[] data
	private String missingValue

	public NumericAttributeDataValidator(String[] data, String missingValue){
		this.data = data
		this.missingValue = missingValue
	}

	@Override
	public boolean isApplicableToData() {
		for(int i = 0; i < data.length; i++){
			def value = data[i]
			try{
				if(!value.equals(missingValue)){
					Double.parseDouble(value)
				}
			}catch(NumberFormatException e){
				return false
			}
		}
		return true
	}

	@Override
	public Attribute createAttributeFromData() {
		if(!isApplicableToData()){
			return null
		}
		NumericAttribute resultAttr = new NumericAttribute()

		def valuesMap = countOccurences()
		if(valuesMap.containsKey(missingValue)){
			resultAttr.setNumberOfMissingValues(valuesMap.get(missingValue))
			valuesMap.remove(missingValue)
		}else{
			resultAttr.setNumberOfMissingValues(0)
		}
		resultAttr.setAverage(getAverage(valuesMap))

		return resultAttr
	}

	private double getAverage(Map<String, Integer> values){
		double sum = 0
		int count = 0

		for(entry in values){
			sum += entry.value*Double.parseDouble(entry.key)
			count += entry.value
		}

		return sum/count
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
