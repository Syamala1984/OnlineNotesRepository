<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en" ng-app="myApp">
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
			<!-- <div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="login.do">Login</a></li>
				</ul>
			</div> -->
		</div>
	</div>

	<div class="container">

		<%-- <c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if> --%>


		<h1></h1>
		<table class="table table-striped" border="1">
			<thead>
				<tr>
					<th>S#</th>
					<th>Name</th>
					<th>Content</th>
					<th>Type</th>
					<th>Status</th>
					<th>Created On</th>
					<th>Action</th>

				</tr>
			</thead>

			<c:forEach var="folder" items="${allFolders}" varStatus="loop">
				<tr>
					<td>${loop.index+1}</td>
					<td>${notes.name}</td>
					<td>${notes.content}</td>
					<td>${notes.type}</td>
					<td>${notes.status}</td>
					<td>${notes.createdDate}</td>

					<td><spring:url value="update.do?id=${notes.id}"
							var="updateUrl" /> <spring:url value="delete.do?id=${notes.id}"
							var="deleteUrl" />

						<button class="btn btn-primary"
							onclick="location.href='${updateUrl}'">Update</button>
						<button class="btn btn-danger"
							onclick="location.href='${deleteUrl}'">Delete</button></td>
				</tr>
			</c:forEach>
		</table>

	</div>
	

</body>
</html>