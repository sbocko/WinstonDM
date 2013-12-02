
<%@ page import="winston.Dataset" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dataset.label', default: 'Dataset')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-dataset" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-dataset" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'dataset.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="dataFile" title="${message(code: 'dataset.dataFile.label', default: 'Data File')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'dataset.description.label', default: 'Description')}" />
					
						<%-- <g:sortableColumn property="descriptionFile" title="${message(code: 'dataset.descriptionFile.label', default: 'Description File')}" /> --%>
					
						<g:sortableColumn property="missingValuePattern" title="${message(code: 'dataset.missingValuePattern.label', default: 'Missing Value Pattern')}" />
					
						<g:sortableColumn property="numberOfMissingValues" title="${message(code: 'dataset.numberOfMissingValues.label', default: 'Number Of Missing Values')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${datasetInstanceList}" status="i" var="datasetInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${datasetInstance.id}">${fieldValue(bean: datasetInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: datasetInstance, field: "dataFile")}</td>
					
						<td>${fieldValue(bean: datasetInstance, field: "description")}</td>
					
						<%-- <td>${fieldValue(bean: datasetInstance, field: "descriptionFile")}</td> --%>
					
						<td>${fieldValue(bean: datasetInstance, field: "missingValuePattern")}</td>
					
						<td>${fieldValue(bean: datasetInstance, field: "numberOfMissingValues")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${datasetInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
