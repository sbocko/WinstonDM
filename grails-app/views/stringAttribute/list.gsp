
<%@ page import="winston.StringAttribute" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'stringAttribute.label', default: 'StringAttribute')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-stringAttribute" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-stringAttribute" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'stringAttribute.title.label', default: 'Title')}" />
					
						<th><g:message code="stringAttribute.dataset.label" default="Dataset" /></th>
					
						<g:sortableColumn property="numberOfDistinctValues" title="${message(code: 'stringAttribute.numberOfDistinctValues.label', default: 'Number Of Distinct Values')}" />
					
						<g:sortableColumn property="numberOfMissingValues" title="${message(code: 'stringAttribute.numberOfMissingValues.label', default: 'Number Of Missing Values')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${stringAttributeInstanceList}" status="i" var="stringAttributeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${stringAttributeInstance.id}">${fieldValue(bean: stringAttributeInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: stringAttributeInstance, field: "dataset")}</td>
					
						<td>${fieldValue(bean: stringAttributeInstance, field: "numberOfDistinctValues")}</td>
					
						<td>${fieldValue(bean: stringAttributeInstance, field: "numberOfMissingValues")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${stringAttributeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
