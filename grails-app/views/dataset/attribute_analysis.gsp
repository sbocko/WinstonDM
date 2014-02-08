
<%@ page import="winston.Dataset"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'dataset.label', default: 'Dataset')}" />
<title><g:message code="default.attribute_analysis.label"
		args="[entityName]" /></title>
</head>
<body>
	<div id="show-dataset" class="content scaffold-show" role="main">
		<h1>
			<g:message code="default.analyze.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>

		<div class="attribute-questions-description">Now answer these questions
			about data attributes to continue to the last step.</div>

		<div></div>

		<g:form>

			<% attrIdList.each { attr -> %>
				<p class="attribute-question"><%="Attribute: <b>${attr.title}</b>" %></p>
				<p class="attribute-question">This attribute has only <%=attr.getNumberOfDistinctValues()%> different values. Are they independent and does not have any order?</p>
			
				<g:radioGroup name="${attr.title}RadioGroup"
					labels="['yes','no']"
					values="[1,0]">
						<p class="radio-button">
							${it.radio}
							${it.label}
						</p>
				</g:radioGroup>
				
				<hr class="attribute-division-line">
      		<% }%>

			<fieldset class="buttons">
				<g:hiddenField name="id" value="${datasetInstance?.id}" />
				<g:link class="back" action="analyze" id="${datasetInstance?.id}">
					<g:message code="default.button.back.label" default="Back" />
				</g:link>
				<g:actionSubmit class="next" action="attributeAnalysis"
					value="${message(code: 'default.button.next.label', default: 'Next')}" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>
