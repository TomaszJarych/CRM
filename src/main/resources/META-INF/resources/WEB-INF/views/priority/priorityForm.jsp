<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new Priority:</title>
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

	<h1 align="center">Add new priority</h1>

	<form:form method="post" modelAttribute="priority" action="add">
		<h3>
			Name
			<form:input path="name" />
			<form:errors path="name" cssClass="errors" ></form:errors>
		</h3>
		<h3>
			Is active? 
			<form:checkbox path="isActive"/>
			<form:errors path="isActive" cssClass="errors" ></form:errors>
		</h3>
		
		<input type="submit" value="Send">
		<form:hidden path="id"/>
	</form:form>
</body>
</html>