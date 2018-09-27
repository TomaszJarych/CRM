<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Project Details: ${project.name}</title>
</head>
<body>
	<div>
		<a href="${pageContext.request.contextPath}/project/all">Projects
			main page</a>
	</div>


	<div align="center">
		<h1 >Project details</h1>

		<h2>Created: ${project.fullDate}</h2>
		<h2>Name: ${project.name}</h2>
		<h2>Project's identifier: ${project.identifier}</h2>
		<h3>Description: ${project.description}</h3>
	</div>

	<div align="center">
		<h1>Users</h1>

		<table align="center" border="1">
			<tr>
				<th>First name</th>
				<th>Last name</th>
				<th>Role</th>
			</tr>
			<c:forEach items="${project.users}" var="user">
				<tr>
					<td align="center">${user.firstName}</td>
					<td align="center">${user.lastName}</td>
					<td align="center">${user.userRole}</td>
			</c:forEach>
		</table>

	</div>

</body>
</html>