<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tasks</title>
</head>
<body>

	<div align="center">
		<h1>Tasks</h1>

		<table border="1">
			<tr>
				<th>Topic</th>
				<th>Project's name</th>
				<th>Description</th>
				<th>Created</th>
				<th>Status</th>
				<th>Priority</th>
				<th>User</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${tasks }" var="task">
				<tr>
					<td align="center">${task.topic}</td>
					<td align="center">${task.project.name}</td>
					<td align="center">${task.description}</td>
					<td align="center">${task.fullDate}</td>
					<td align="center">${task.status.name}</td>
					<td align="center">${task.priority.name}</td>
					<td align="center">${task.user.fullName}</td>
					<td align="center"><a href="../task/delete/${task.id}">Delete</a><a
						href="../task/edit/${task.id}"> | Edit</a></td>
				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>