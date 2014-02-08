
<%@ page import="winston.Dataset"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'dataset.label', default: 'Dataset')}" />
<title><g:message code="default.analyze.label"
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

		<div class="choose-target-label">Please choose the target
			attribute, you are interested in</div>

		<g:form>
			<g:radioGroup name="targetAttributeRadioGroup"
				labels="${datasetInstance.attributes.title}"
				values="${datasetInstance.attributes.id}" value="${params.currentTargetAttribute}">
				<p class="radio-button">
					${it.radio}
					${it.label}
				</p>
			</g:radioGroup>

			<fieldset class="buttons">
				<g:hiddenField name="id" value="${datasetInstance?.id}" />
				<g:link class="back" action="show" id="${datasetInstance?.id}">
					<g:message code="default.button.back.label" default="Back" />
				</g:link>
				<g:actionSubmit class="next" action="saveTargetAttribute"
					value="${message(code: 'default.button.next.label', default: 'Next')}" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>
