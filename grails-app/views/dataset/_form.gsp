<%@ page import="winston.Dataset" %>



<div class="fieldcontain ${hasErrors(bean: datasetInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="dataset.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${datasetInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: datasetInstance, field: 'dataFile', 'error')} ">
	<label for="dataFile">
		<g:message code="dataset.dataFile.label" default="Data File" />
		
	</label>
	<input type="file" name="dataFile" id="dataFile" />
	<!-- <g:textField name="dataFile" value="${datasetInstance?.dataFile}"/> -->
</div>

<div class="fieldcontain ${hasErrors(bean: datasetInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="dataset.description.label" default="Description" />
		
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="5000" value="${datasetInstance?.description}"/>
</div>

<!-- <div class="fieldcontain ${hasErrors(bean: datasetInstance, field: 'descriptionFile', 'error')} ">
	<label for="descriptionFile">
		<g:message code="dataset.descriptionFile.label" default="Description File" />
		
	</label>
	<g:textField name="descriptionFile" value="${datasetInstance?.descriptionFile}"/>
</div> -->

<div class="fieldcontain ${hasErrors(bean: datasetInstance, field: 'missingValuePattern', 'error')} ">
	<label for="missingValuePattern">
		<g:message code="dataset.missingValuePattern.label" default="Missing Value Pattern" />
		
	</label>
	<g:textField name="missingValuePattern" value="${datasetInstance?.missingValuePattern}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: datasetInstance, field: 'numberOfMissingValues', 'error')} required">
	<label for="numberOfMissingValues">
		<g:message code="dataset.numberOfMissingValues.label" default="Number Of Missing Values" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numberOfMissingValues" type="number" value="${datasetInstance.numberOfMissingValues}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: datasetInstance, field: 'numberOfInstances', 'error')} required">
	<label for="numberOfInstances">
		<g:message code="dataset.numberOfInstances.label" default="Number Of Instances" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numberOfInstances" type="number" value="${datasetInstance.numberOfInstances}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: datasetInstance, field: 'attributes', 'error')} ">
	<label for="attributes">
		<g:message code="dataset.attributes.label" default="Attributes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${datasetInstance?.attributes?}" var="a">
    <li><g:link controller="attribute" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="attribute" action="create" params="['dataset.id': datasetInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'attribute.label', default: 'Attribute')])}</g:link>
</li>
</ul>

</div>

