<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new Project:</title>
</head>
<body>
	<h1 align="center">Add new Project</h1>

	<form:form method="post" modelAttribute="project" action="add">
		<h3>
			Name
			<form:input path="name" />
			<form:errors path="name" cssClass="errors"></form:errors>
		</h3>
		<h3>
			Description:
			<form:input path="description" />
			<form:errors path="description" cssClass="errors"></form:errors>
		</h3>
		<h3>
			Website:
			<form:input path="website" />
			<form:errors path="website" cssClass="errors"></form:errors>
		</h3>
		<h3>
			Users:
			<form:select path="users" multiple="true"  >
			<form:options items="${users}"  itemValue="id" itemLabel="fullName" />
			</form:select>
			<form:errors path="users" cssClass="errors"></form:errors>
		</h3>

		<h3>
			Is active?
			<form:checkbox path="isActive" />
			<form:errors path="isActive" cssClass="errors"></form:errors>
		</h3>

		<input type="submit" value="Send">
		<form:hidden path="id" />
	</form:form>
</body>
</html>