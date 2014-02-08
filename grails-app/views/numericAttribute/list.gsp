
<%@ page import="winston.NumericAttribute" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'numericAttribute.label', default: 'NumericAttribute')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-numericAttribute" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-numericAttribute" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'numericAttribute.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="average" title="${message(code: 'numericAttribute.average.label', default: 'Average')}" />
					
						<th><g:message code="numericAttribute.dataset.label" default="Dataset" /></th>
					
						<g:sortableColumn property="distinctValues" title="${message(code: 'numericAttribute.distinctValues.label', default: 'Distinct Values')}" />
					
						<g:sortableColumn property="numberOfMissingValues" title="${message(code: 'numericAttribute.numberOfMissingValues.label', default: 'Number Of Missing Values')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${numericAttributeInstanceList}" status="i" var="numericAttributeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${numericAttributeInstance.id}">${fieldValue(bean: numericAttributeInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: numericAttributeInstance, field: "average")}</td>
					
						<td>${fieldValue(bean: numericAttributeInstance, field: "dataset")}</td>
					
						<td>${fieldValue(bean: numericAttributeInstance, field: "numberOfDistinctValues")}</td>
					
						<td>${fieldValue(bean: numericAttributeInstance, field: "numberOfMissingValues")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${numericAttributeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
