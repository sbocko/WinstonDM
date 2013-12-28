
<%@ page import="winston.NumericAttribute" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'numericAttribute.label', default: 'NumericAttribute')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-numericAttribute" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-numericAttribute" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list numericAttribute">
			
				<g:if test="${numericAttributeInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="numericAttribute.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${numericAttributeInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${numericAttributeInstance?.average}">
				<li class="fieldcontain">
					<span id="average-label" class="property-label"><g:message code="numericAttribute.average.label" default="Average" /></span>
					
						<span class="property-value" aria-labelledby="average-label"><g:fieldValue bean="${numericAttributeInstance}" field="average"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${numericAttributeInstance?.minimum}">
				<li class="fieldcontain">
					<span id="minimum-label" class="property-label"><g:message code="numericAttribute.minimum.label" default="Minimum" /></span>
					
						<span class="property-value" aria-labelledby="minimum-label"><g:fieldValue bean="${numericAttributeInstance}" field="minimum"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${numericAttributeInstance?.maximum}">
				<li class="fieldcontain">
					<span id="maximum-label" class="property-label"><g:message code="numericAttribute.maximum.label" default="Maximum" /></span>
					
						<span class="property-value" aria-labelledby="maximum-label"><g:fieldValue bean="${numericAttributeInstance}" field="maximum"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${numericAttributeInstance?.dataset}">
				<li class="fieldcontain">
					<span id="dataset-label" class="property-label"><g:message code="numericAttribute.dataset.label" default="Dataset" /></span>
					
						<span class="property-value" aria-labelledby="dataset-label"><g:link controller="dataset" action="show" id="${numericAttributeInstance?.dataset?.id}">${numericAttributeInstance?.dataset?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${numericAttributeInstance?.distinctValues}">
				<li class="fieldcontain">
					<span id="distinctValues-label" class="property-label"><g:message code="numericAttribute.distinctValues.label" default="Distinct Values" /></span>
					
						<span class="property-value" aria-labelledby="distinctValues-label"><g:fieldValue bean="${numericAttributeInstance}" field="distinctValues"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${numericAttributeInstance?.numberOfMissingValues}">
				<li class="fieldcontain">
					<span id="numberOfMissingValues-label" class="property-label"><g:message code="numericAttribute.numberOfMissingValues.label" default="Number Of Missing Values" /></span>
					
						<span class="property-value" aria-labelledby="numberOfMissingValues-label"><g:fieldValue bean="${numericAttributeInstance}" field="numberOfMissingValues"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${numericAttributeInstance?.id}" />
					<g:link class="edit" action="edit" id="${numericAttributeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
