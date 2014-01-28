
<%@ page import="winston.Dataset"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'dataset.label', default: 'Dataset')}" />
<title><g:message code="default.analyze.label" args="[entityName]" /></title>
</head>
<body>
	<h1><g:message code="default.analyze.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
	
	IT works! Lets analyze me.

	<g:radioGroup name="attributes" labels="${datasetInstance.attributes}"
		values="${datasetInstance.attributes}">
		<p>
			${it.label}
			${it.radio}
		</p>
	</g:radioGroup>

</body>
</html>
