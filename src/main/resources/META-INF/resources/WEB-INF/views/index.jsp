<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRM - index page</title>
</head>
<body>
	<div>
		<h3>Menu</h3>
		<h4>
			<a href="${pageContext.request.contextPath}/index">Index page</a>
			<a href="${pageContext.request.contextPath}/logout">Logout</a>
			<a href="${pageContext.request.contextPath}/index/adminPanel">Admin panel</a>
		</h4>
	</div>


<h1 align="center">Hello ${loggedUser.fullName}</h1>

<div align="center">
		<h1>Projects</h1>
		<table align="center">
			<tr>
				<th>Name</th>
				<th>Identifier</th>
				<th>Is active?</th>
				<th>Created</th>
				<th>Tasks</th>
			</tr>
			<c:forEach items="${projects }" var="project">
				<tr>
					<td align="center">${project.name}</td>
					<td align="center">${project.identifier}</td>
					<td align="center">${project.isProjectActive}</td>
					<td align="center">${project.fullDate}</td>
					<td align="center"><a
						href="../task/getTaskByProjectId/${project.id}">Tasks</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div align="center">
		<h1>Activities</h1>
		<table align="center">
			<tr>
				<th>Content</th>
				<th>Created</th>
			</tr>
			<c:forEach items="${activities }" var="activity">
				<tr>
					<td align="center">${activity.content}</td>
					<td align="center">${activity.fullDate}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>