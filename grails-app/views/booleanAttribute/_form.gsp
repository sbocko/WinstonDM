<%@ page import="winston.BooleanAttribute" %>



<div class="fieldcontain ${hasErrors(bean: booleanAttributeInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="booleanAttribute.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${booleanAttributeInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: booleanAttributeInstance, field: 'dataset', 'error')} required">
	<label for="dataset">
		<g:message code="booleanAttribute.dataset.label" default="Dataset" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="dataset" name="dataset.id" from="${winston.Dataset.list()}" optionKey="id" required="" value="${booleanAttributeInstance?.dataset?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: booleanAttributeInstance, field: 'numberOfFalseValues', 'error')} required">
	<label for="numberOfFalseValues">
		<g:message code="booleanAttribute.numberOfFalseValues.label" default="Number Of False Values" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numberOfFalseValues" type="number" value="${booleanAttributeInstance.numberOfFalseValues}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: booleanAttributeInstance, field: 'numberOfMissingValues', 'error')} required">
	<label for="numberOfMissingValues">
		<g:message code="booleanAttribute.numberOfMissingValues.label" default="Number Of Missing Values" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numberOfMissingValues" type="number" value="${booleanAttributeInstance.numberOfMissingValues}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: booleanAttributeInstance, field: 'numberOfTrueValues', 'error')} required">
	<label for="numberOfTrueValues">
		<g:message code="booleanAttribute.numberOfTrueValues.label" default="Number Of True Values" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numberOfTrueValues" type="number" value="${booleanAttributeInstance.numberOfTrueValues}" required=""/>
</div>

