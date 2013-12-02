<%@ page import="winston.StringAttribute" %>



<div class="fieldcontain ${hasErrors(bean: stringAttributeInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="stringAttribute.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${stringAttributeInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stringAttributeInstance, field: 'dataset', 'error')} required">
	<label for="dataset">
		<g:message code="stringAttribute.dataset.label" default="Dataset" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="dataset" name="dataset.id" from="${winston.Dataset.list()}" optionKey="id" required="" value="${stringAttributeInstance?.dataset?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stringAttributeInstance, field: 'numberOfDistinctValues', 'error')} required">
	<label for="numberOfDistinctValues">
		<g:message code="stringAttribute.numberOfDistinctValues.label" default="Number Of Distinct Values" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numberOfDistinctValues" type="number" value="${stringAttributeInstance.numberOfDistinctValues}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: stringAttributeInstance, field: 'numberOfMissingValues', 'error')} required">
	<label for="numberOfMissingValues">
		<g:message code="stringAttribute.numberOfMissingValues.label" default="Number Of Missing Values" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numberOfMissingValues" type="number" value="${stringAttributeInstance.numberOfMissingValues}" required=""/>
</div>

