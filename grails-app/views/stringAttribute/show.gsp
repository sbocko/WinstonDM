
<%@ page import="winston.StringAttribute" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'stringAttribute.label', default: 'StringAttribute')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-stringAttribute" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-stringAttribute" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list stringAttribute">
			
				<g:if test="${stringAttributeInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="stringAttribute.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${stringAttributeInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${stringAttributeInstance?.dataset}">
				<li class="fieldcontain">
					<span id="dataset-label" class="property-label"><g:message code="stringAttribute.dataset.label" default="Dataset" /></span>
					
						<span class="property-value" aria-labelledby="dataset-label"><g:link controller="dataset" action="show" id="${stringAttributeInstance?.dataset?.id}">${stringAttributeInstance?.dataset?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${stringAttributeInstance?.numberOfDistinctValues}">
				<li class="fieldcontain">
					<span id="numberOfDistinctValues-label" class="property-label"><g:message code="stringAttribute.numberOfDistinctValues.label" default="Number Of Distinct Values" /></span>
					
						<span class="property-value" aria-labelledby="numberOfDistinctValues-label"><g:fieldValue bean="${stringAttributeInstance}" field="numberOfDistinctValues"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${stringAttributeInstance?.numberOfMissingValues}">
				<li class="fieldcontain">
					<span id="numberOfMissingValues-label" class="property-label"><g:message code="stringAttribute.numberOfMissingValues.label" default="Number Of Missing Values" /></span>
					
						<span class="property-value" aria-labelledby="numberOfMissingValues-label"><g:fieldValue bean="${stringAttributeInstance}" field="numberOfMissingValues"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${stringAttributeInstance?.id}" />
					<g:link class="edit" action="edit" id="${stringAttributeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
