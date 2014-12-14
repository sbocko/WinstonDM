<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title><g:layoutTitle default="Winston" /></title>

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
					<sec:ifLoggedIn>
						<li><g:link controller="logout" >Log out</g:link></li>
					</sec:ifLoggedIn>
					<sec:ifNotLoggedIn>
						<li><g:link controller="login" action="auth">Log in</g:link></li>
					</sec:ifNotLoggedIn>
				</ul>
			</div>
		</div>
	</div>


	<div class="container bs-docs-section">

		<div class="page-header" id="banner">
			<div class="row text-center">
				<h1>Winston</h1>
				<p class="lead">Exploring data mining easier, together</p>
				<a href="/winston/dataset/list">
					<button class="btn btn-success btn-lg">Get Started Now!</button>
				</a>
			</div>
		</div>

		<div class="row bs-docs-section body-bottom-padding">
			<div class="col-lg-4">
				<div class="jumbotron-custom">
					<h2 class="text-center">High efficiency</h2>
					<p>Using a custom designed landmarking and meta-learning
						algorithms Winston is capable of providing you good quality
						results in almost no time. This saves your time and helps you to
						analyze data more precisely.</p>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="jumbotron-custom">
					<h2 class="text-center">Easy to use</h2>
					<p>Winston allows you to upload, visualize and analyze your
						data online. Basically you don't need any knowledge of neither
						data mining methods nor programming. It's as simple as it looks.</p>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="jumbotron-custom">
					<h2 class="text-center">Performance</h2>
					<p>Your data are processed on a high-performance computational
						cluster which is capable of executing many tasks at the same time.
						This is useful when you want to achieve best results possible.</p>
				</div>
			</div>
		</div>



	</div>

	<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="${request.contextPath}/amelia/bootstrap/bootstrap.min.js"></script>
	<script src="${request.contextPath}/amelia/bootstrap/usebootstrap.js"></script>
</body>
</html>
