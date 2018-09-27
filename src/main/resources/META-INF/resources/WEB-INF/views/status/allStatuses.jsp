<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Status</title>
</head>
<body>


<div align="center">
	<h1>Status</h1>
	
	<table align="center">
		<tr>
			<th>Name</th>
			<th>Is active?</th>
			<th>Sorting number</th>
			<th>Action:</th>
		</tr>
		<c:forEach items="${statuses }" var="status">
			<tr>
				<td align="center">${status.name}</td>
				<td align="center">${status.isStatusActive}</td>
				<td align="center">${status.sortingOrderNumber}</td>
				<td align="center"><a href="../status/delete/${status.id}">Delete</a><a
					href="../status/edit/${status.id}""> | Edit</a></td>
			</tr>
		</c:forEach>
	</table>

</div>

</body>
</html>