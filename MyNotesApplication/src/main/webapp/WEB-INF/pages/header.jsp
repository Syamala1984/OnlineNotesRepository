<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
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

   
<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="folders.do">My Notes </a>
		</div>
		<c:if test="${LOGGEDIN_USER}== NULL">
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="login.do">Login</a></li>
				</ul>
			</div>
		</c:if>
	</div>
</div>
</head>