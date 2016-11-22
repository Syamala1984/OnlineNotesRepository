<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title></title>

<!-- Bootstrap core CSS -->
<link href="webjars/bootstrap/3.3.4/css/bootstrap.min.css"
	rel="stylesheet">
<link href="webjars/bootstrap/3.3.4/css/bootstrap-theme.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="assets/css/styles.css" rel="stylesheet">
<link href="webjars/font-awesome/4.5.0/css/font-awesome.min.css"
	rel="stylesheet">

<script type="text/javascript" src="webjars/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript"
	src="webjars/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/angularjs/1.5.0/angular.js"></script>
<script type="text/javascript"
	src="webjars/angularjs/1.5.0/angular-route.js"></script>
<script type="text/javascript" src="assets/js/app.js"></script>
<script type="text/javascript" src="assets/js/controllers.js"></script>


</head>

<body>

	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">My Notes</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="login.do">Login</a></li>
				</ul>
			</div>
		</div>
	</div>
	<BR />

	<br />
	<div class="container">

		<div style="azimuth: center; vertical-align: middle;">
			<form:form action="register.do" method="POST" modelAttribute="user">
				<h3>Register</h3>
				<div>
					<div>
						User Name&nbsp;
						<form:input type="text" ng-model="userName" path="userName" />
					</div>
					<div>
						Password&nbsp;
						<form:input type="password" ng-model="password" path="password" />
					</div>
					<div>
						Re Enter Password&nbsp; <input type="password"
							ng-model="retypePassword" name="retypePassword" />
					</div>
					<div>
						Last 4 Digits Of SSN&nbsp;
						<form:input type="text" ng-model="ssn" path="ssn"  size="4"/>
					</div>
					<div>
						<button type="submit" class="btn btn-primary">Submit</button>

					</div>
				</div>

			</form:form>
		</div>
	</div>
</body>
</html>
