<%@ page import="winston.NumericAttribute" %>



<div class="fieldcontain ${hasErrors(bean: numericAttributeInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="numericAttribute.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${numericAttributeInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: numericAttributeInstance, field: 'average', 'error')} required">
	<label for="average">
		<g:message code="numericAttribute.average.label" default="Average" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="average" value="${fieldValue(bean: numericAttributeInstance, field: 'average')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: numericAttributeInstance, field: 'dataset', 'error')} required">
	<label for="dataset">
		<g:message code="numericAttribute.dataset.label" default="Dataset" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="dataset" name="dataset.id" from="${winston.Dataset.list()}" optionKey="id" required="" value="${numericAttributeInstance?.dataset?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: numericAttributeInstance, field: 'distinctValues', 'error')} required">
	<label for="distinctValues">
		<g:message code="numericAttribute.distinctValues.label" default="Distinct Values" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="distinctValues" type="number" value="${numericAttributeInstance.distinctValues}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: numericAttributeInstance, field: 'numberOfMissingValues', 'error')} required">
	<label for="numberOfMissingValues">
		<g:message code="numericAttribute.numberOfMissingValues.label" default="Number Of Missing Values" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numberOfMissingValues" type="number" value="${numericAttributeInstance.numberOfMissingValues}" required=""/>
</div>

