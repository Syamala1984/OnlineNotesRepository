<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="header.jsp" />

<body>

	<div class="container">

		<%-- <c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if> --%>
		<BR> <BR>
		<h1></h1>

		<form:form name="folderForm" method="post" action="addFolder.do"
			modelAttribute="folder">
			<form:hidden path="id" value="${folder.id}" />

			<table class="table table-striped" border="1">
				<thead>
					<tr>
						<th>New Folder</th>
						<td><form:input path="name" size="15" type="text"
								class="form-control " id="name" placeholder="Name" /></td>
						<td><c:if test="${folder.id > 0}">
								<button class="btn btn-primary" type="submit">Update
									Folder</button>
							</c:if> <c:if test="${folder.id == null}">
								<button class="btn btn-primary" type="submit">Add
									Folder</button></</c:if></td>
						<td><button class="btn btn-primary"
								ng-click="name = '',id=NULL">Clear</button></td>
					</tr>
				</thead>
			</table>
		</form:form>
		<table class="table table-striped" border="1">
			<thead>
				<tr>
					<th>S#</th>
					<th>Name</th>
					<th>Created On</th>
					<th>Action</th>
				</tr>
			</thead>
			<c:forEach var="folder" items="${allFolders}" varStatus="loop">
				<tr>
					<td>${loop.index+1}</td>
					<td><a href="allNotes.do?FID=${folder.id}">${folder.name}</a></td>
					<td>${folder.createdDate}</td>

					<td><spring:url value="getFolder.do?id=${folder.id}"
							var="updateUrl" /> <spring:url
							value="deleteFolder.do?id=${folder.id}" var="deleteUrl" />
						<button class="btn btn-primary"
							onclick="location.href='${updateUrl}'">Update</button> <form:form
							id="deletefolderForm" name="deletefolderForm" method="post"
							action="deleteFolder.do" modelAttribute="folder">
							<form:hidden path="id" value="${folder.id}" />
							<button class="btn btn-danger" type="submit">Delete</button>
						</form:form></td>
				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>