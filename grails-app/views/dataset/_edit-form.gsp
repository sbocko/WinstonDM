<%@ page import="winston.Dataset" %>



<div class="fieldcontain ${hasErrors(bean: datasetInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="dataset.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${datasetInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: datasetInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="dataset.description.label" default="Description" />
		
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="5000" value="${datasetInstance?.description}"/>
</div>