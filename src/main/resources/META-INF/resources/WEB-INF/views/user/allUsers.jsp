<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users</title>
</head>
<body>


<div align="center">
	<h1>Users</h1>
	
	<table align="center">
		<tr>
			<th>Login</th>
			<th>First name</th>
			<th>Last name</th>
			<th>Role:</th>
			<th>Projects number:</th>
			<th>Action:</th>
		</tr>
		<c:forEach items="${users }" var="user">
			<tr>
				<td align="center">${user.login}</td>
				<td align="center">${user.firstName}</td>
				<td align="center">${user.lastName}</td>
				<td align="center">${user.userRole}</td>
				<td align="center">${user.projects.size()}</td>
				<td align="center"><a href="../user/delete/${user.id}">Delete</a><a
					href="../user/edit/${user.id}""> | Edit</a></td>
			</tr>
		</c:forEach>
	</table>

</div>

</body>
</html>