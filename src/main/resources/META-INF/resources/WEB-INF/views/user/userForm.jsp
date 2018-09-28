<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new User:</title>
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
	<h1 align="center">Add new user</h1>

	<form:form method="post" modelAttribute="user" action="add">
		<h3>
			First name: 
			<form:input path="firstName" />
			<form:errors path="firstName" cssClass="errors" ></form:errors>
		</h3>
		<h3>
			Last name: 
			<form:input path="lastName" />
			<form:errors path="lastName" cssClass="errors" ></form:errors>
		</h3>
		<h3>
			login: 
			<form:input path="login" />
			<form:errors path="login" cssClass="errors" ></form:errors>
		</h3>
		<h3>
			Password: 
			<form:password path="password" />
			<form:errors path="password" cssClass="errors" ></form:errors>
		</h3>
		<h3>
		<form:select path="userRole">
			<form:option value="USER" label="User"></form:option>
			<form:option value="ADMIN" label="Admin"></form:option>
		</form:select>
		</h3>
		
		<input type="submit" value="Send">
		<form:hidden path="id"/>
	</form:form>
</body>
</html>