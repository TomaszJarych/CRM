<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Project</title>
</head>
<body>
	<div align="center">
		<h1>Project</h1>

		<table align="center">
			<tr>
				<th>Name</th>
				<th>Identifier</th>
				<th>Is active?</th>
				<th>Created</th>
				<th>Tasks</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${projects }" var="project">
				<tr>
					<td align="center">${project.name}</td>
					<td align="center">${project.identifier}</td>
					<td align="center">${project.isProjectActive}</td>
					<td align="center">${project.fullDate}</td>
					<td align="center"><a
						href="../task/getTaskByProjectId/${project.id}">Tasks</a></td>
					<td align="center"><a href="../project/delete/${project.id}">Delete</a><a
						href="../project/edit/${project.id}"> | Edit</a> <a
						href="../project/details/${project.id}"> | Details</a></td>
				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>