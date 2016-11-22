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

		<form:form name="notesForm" method="post" action="save.do"
			modelAttribute="notes">
			<%-- action="folders/${id}/notes" --%>
			<form:hidden path="id" value="${notes.id}" />
			<input type="hidden" name="folder.id"
				value="<%=request.getParameter("FID")%>" />
			<form:hidden path="user.id" value="2" />

			<table class="table table-striped" border="1" style="width: 100%;">
				<thead>
					<tr>
						<th>New Note</th>
					</tr>
					<tr>
						<td colspan="2"><form:input path="name" size="25" type="text"
								class="form-control " id="name" placeholder="Name" /></td>
					</tr>
					<tr>
						<td colspan="2"><form:textarea path="content" cols="80"
								rows="4" class="form-control " id="content"
								placeholder="Content" /></td>
					</tr>
					<tr>
						<td colspan="2"><form:select path="type" id="type"
								class="form-control">
								<form:option value=".txt"></form:option>
								<form:option value=".pdf"></form:option>
								<form:option value=".xls"></form:option>
							</form:select></td>
					</tr>
					<tr>
						<td colspan="2" style="azimuth: right: ;"><c:if
								test="${notes.id > 0}">
								<button class="btn btn-primary" type="submit">Update
									Notes</button>
							</c:if> <c:if test="${notes.id == null }">
								<button class="btn btn-primary" type="submit">Add
									Notes</button>
							</c:if>
							<button class="btn btn-primary" ng-click="content = '',id=NULL">Clear</button></td>
					</tr>
				</thead>
			</table>
		</form:form>
		<BR>
		<div></div>
		<div>
			<table class="table table-striped" border="1">
				<thead>
					<tr>
						<th colspan="6">All Notes</th>
					</tr>
					<tr>
						<th>S#</th>
						<th>Name</th>
						<th>Content</th>
						<th>Type</th>
						<th>Created On</th>
						<th>Action</th>
					</tr>
				</thead>
				<c:forEach var="notes" items="${allNotes}" varStatus="loop">
					<tr>
						<td>${loop.index+1}</td>
						<td>${notes.name}</td>
						<td>${notes.content}</td>
						<td>${notes.type}</td>
						<td>${notes.createdDate}</td>
						<td><spring:url value="/getNotes.do?NID=${notes.id}"
								var="updateUrl" /> <spring:url
								value="deleteNotes.do?id=${notes.id}" var="deleteUrl" />
							<button class="btn btn-primary"
								onclick="location.href='${updateUrl}'">Update</button> <form:form
								id="deletenotesForm" name="deletenotesForm" method="post"
								action="deleteNotes.do" modelAttribute="notes">
								<form:hidden path="id" value="${notes.id}" />
								<form:hidden path="folder.id" value="${notes.folder.id}" />
								<button class="btn btn-danger" type="submit">Delete</button>
							</form:form></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>