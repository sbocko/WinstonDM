<%@ page import="winston.Attribute" %>



<div class="fieldcontain ${hasErrors(bean: attributeInstance, field: 'dataset', 'error')} required">
	<label for="dataset">
		<g:message code="attribute.dataset.label" default="Dataset" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="dataset" name="dataset.id" from="${winston.Dataset.list()}" optionKey="id" required="" value="${attributeInstance?.dataset?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: attributeInstance, field: 'numberOfMissingValues', 'error')} required">
	<label for="numberOfMissingValues">
		<g:message code="attribute.numberOfMissingValues.label" default="Number Of Missing Values" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numberOfMissingValues" type="number" value="${attributeInstance.numberOfMissingValues}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: attributeInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="attribute.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${attributeInstance?.title}"/>
</div>

