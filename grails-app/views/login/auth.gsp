<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Winston - Login</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${request.contextPath}/amelia/theme/bootstrap.css" media="screen">
<link rel="stylesheet"
	href="${request.contextPath}/amelia/theme/usebootstrap.css">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="bootstrap/html5shiv.js"></script>
      <script src="bootstrap/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<!-- Navigation bar -->
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="#" class="navbar-brand">Winston</a>
			</div>
			<div class="navbar-collapse collapse" id="navbar-main">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="http://data-mining.sk/web/" target="_blank">About</a></li>
				</ul>
			</div>
		</div>
	</div>


<div id='login' class="container bs-docs-section">
	<div class="well col-lg-6 centered">

		<g:if test='${flash.message}'>
			<div class="alert alert-dismissable alert-warning">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<h4>Oh no!</h4>
				<p>${flash.message}</p>
			</div>
		</g:if>
		<h1 class="text-center">Please sign in</h1>
		<form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
			<p>
				<input type='text' class='form-control' name='j_username' id='username' placeholder="Email"/>
			</p>

			<p>
				<input type='password' class='form-control' name='j_password' id='password' placeholder="<g:message code="springSecurity.login.password.label"/>"/>
			</p>
		
					<input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
					<label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>

			<!-- <div class="col-lg-6"> -->
				<input type='submit' class="btn btn-primary to-right" id="submit" value='${message(code: "springSecurity.login.button")}'/>
			<!-- </div> -->
			
		</form>
	</div>

</div>
	<p class="text-center">
		Don't have an account? <g:link controller="register" >Click here</g:link>
	</p>

	<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="${request.contextPath}/amelia/bootstrap/bootstrap.min.js"></script>
	<script src="${request.contextPath}/amelia/bootstrap/usebootstrap.js"></script>
</body>
</html>
