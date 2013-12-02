
<%@ page import="winston.Attribute" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'attribute.label', default: 'Attribute')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-attribute" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-attribute" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list attribute">
			
				<g:if test="${attributeInstance?.dataset}">
				<li class="fieldcontain">
					<span id="dataset-label" class="property-label"><g:message code="attribute.dataset.label" default="Dataset" /></span>
					
						<span class="property-value" aria-labelledby="dataset-label"><g:link controller="dataset" action="show" id="${attributeInstance?.dataset?.id}">${attributeInstance?.dataset?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${attributeInstance?.numberOfMissingValues}">
				<li class="fieldcontain">
					<span id="numberOfMissingValues-label" class="property-label"><g:message code="attribute.numberOfMissingValues.label" default="Number Of Missing Values" /></span>
					
						<span class="property-value" aria-labelledby="numberOfMissingValues-label"><g:fieldValue bean="${attributeInstance}" field="numberOfMissingValues"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${attributeInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="attribute.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${attributeInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${attributeInstance?.id}" />
					<g:link class="edit" action="edit" id="${attributeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
