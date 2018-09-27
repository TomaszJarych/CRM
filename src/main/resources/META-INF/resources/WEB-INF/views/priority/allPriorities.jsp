<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Priority</title>
</head>
<body>


<div align="center">
	<h1>Priority</h1>
	
	<table align="center">
		<tr>
			<th>Name</th>
			<th>Is active?</th>
			<th>Action:</th>
		</tr>
		<c:forEach items="${priorities }" var="priority">
			<tr>
				<td align="center">${priority.name}</td>
				<td align="center">${priority.isPriorityActive}</td>
				<td align="center"><a href="../priority/delete/${priority.id}">Delete</a><a
					href="../priority/edit/${priority.id}""> | Edit</a></td>
			</tr>
		</c:forEach>
	</table>

</div>

</body>
</html>