
<%@ page import="winston.Dataset" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dataset.label', default: 'Dataset')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-dataset" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-dataset" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list dataset">
			
				<g:if test="${datasetInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="dataset.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${datasetInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${datasetInstance?.dataFile}">
				<li class="fieldcontain">
					<span id="dataFile-label" class="property-label"><g:message code="dataset.dataFile.label" default="Data File" /></span>
					
						<span class="property-value" aria-labelledby="dataFile-label"><g:fieldValue bean="${datasetInstance}" field="dataFile"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${datasetInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="dataset.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${datasetInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${datasetInstance?.descriptionFile}">
				<li class="fieldcontain">
					<span id="descriptionFile-label" class="property-label"><g:message code="dataset.descriptionFile.label" default="Description File" /></span>
					
						<span class="property-value" aria-labelledby="descriptionFile-label"><g:fieldValue bean="${datasetInstance}" field="descriptionFile"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${datasetInstance?.missingValuePattern}">
				<li class="fieldcontain">
					<span id="missingValuePattern-label" class="property-label"><g:message code="dataset.missingValuePattern.label" default="Missing Value Pattern" /></span>
					
						<span class="property-value" aria-labelledby="missingValuePattern-label"><g:fieldValue bean="${datasetInstance}" field="missingValuePattern"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${datasetInstance?.numberOfMissingValues}">
				<li class="fieldcontain">
					<span id="numberOfMissingValues-label" class="property-label"><g:message code="dataset.numberOfMissingValues.label" default="Number Of Missing Values" /></span>
					
						<span class="property-value" aria-labelledby="numberOfMissingValues-label"><g:fieldValue bean="${datasetInstance}" field="numberOfMissingValues"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${datasetInstance?.numberOfInstances}">
				<li class="fieldcontain">
					<span id="numberOfInstances-label" class="property-label"><g:message code="dataset.numberOfInstances.label" default="Number Of Instances" /></span>
					
						<span class="property-value" aria-labelledby="numberOfInstances-label"><g:fieldValue bean="${datasetInstance}" field="numberOfInstances"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${datasetInstance?.attributes}">
				<li class="fieldcontain">
					<span id="attributes-label" class="property-label"><g:message code="dataset.attributes.label" default="Attributes" /></span>
					
						<g:each in="${datasetInstance.attributes}" var="a">
						<span class="property-value" aria-labelledby="attributes-label"><g:link controller="attribute" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${datasetInstance?.id}" />
					<g:link class="edit" action="edit" id="${datasetInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
