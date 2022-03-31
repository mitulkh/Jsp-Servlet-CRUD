
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Person Details</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-light bg-light justify-content-between">
		<a class="navbar-brand">Person Management System</a>
		<form class="form-inline">

			<a href="<%=request.getContextPath()%>/add" class="btn btn-outline-success my-2 my-sm-0">Add
				Person</a>
		</form>
	</nav>
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Name</th>
				<th scope="col">Mobile No</th>
				<th scope="col">Email</th>
				<th scope="col">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="person" items="${personList}">
				<tr>
					<td>${person.id}</td>
					<td>${person.name}</td>
					<td>${person.mobile}</td>
					<td>${person.email}</td>
					<td>
						<a href="edit?id=<c:out value='${person.id}' />">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="delete?id=<c:out value='${person.id}' />">Delete</a>
					</td>
				</tr>
				
			</c:forEach>
		</tbody>
	</table>
</body>
</html>