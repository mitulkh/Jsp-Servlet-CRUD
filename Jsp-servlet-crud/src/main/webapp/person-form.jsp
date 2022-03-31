<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Form</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
	
	<c:if test="${person != null}">
		<form action="update" method="post">
	</c:if>

	<c:if test="${person == null}">
		<form action="insert" method="post">
	</c:if>
	
	<caption>
		<h2 align="center">
			<c:if test="${person != null}">
			Edit Person
			</c:if>
			<c:if test="${person == null}">
			Add New Person
			</c:if>
		</h2>
	</caption>
	
	<c:if test="${person != null}">
		<input type="hidden" name="id" value="<c:out value='${person.id}' />" />
	</c:if>
	<form class="form-horizontal" action="save" method="post">
		<div class="form-group">
			<label class="control-label col-sm-2" >Name:</label>
			<div class="col-sm-10">
			<input type="text" value="<c:out value='${person.name}' />" class="form-control"
				placeholder="Enter name" required>&nbsp;
			</div> 
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">Mobile:</label> 
			<div class="col-sm-10">
			<input type="text" value="<c:out value='${person.mobile}' />" class="form-control" 
				placeholder="Enter Mobile" required>&nbsp;
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">Email:</label> 
			<div class="col-sm-10">
			<input type="email" value="<c:out value='${person.email}' />" class="form-control"
				placeholder="Enter Email" required>&nbsp;
			</div>
		</div>
		<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
		<button type="submit" class="btn btn-primary">Submit</button>
		</div>
		</div>
	</form>
	</div>
</body>
</html>