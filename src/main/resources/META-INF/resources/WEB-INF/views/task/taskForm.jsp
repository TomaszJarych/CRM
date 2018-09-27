<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new Task</title>
</head>
<body>
	<h1 align="center">Add new Task</h1>

	<form:form method="post" modelAttribute="task" action="add">
		<h3>
			Topic
			<form:input path="topic" />
			<form:errors path="topic" cssClass="errors"></form:errors>
		</h3>
		<h3>
			Description
			<form:input path="description" />
			<form:errors path="description" cssClass="errors"></form:errors>
		</h3>
		<h3>
			Project:
			<form:select path="project.id" >
			<form:option items="${project}"  value="${project.id}" label="${project.name}" />
			</form:select>
			<form:errors path="project" cssClass="errors"></form:errors>
		</h3>
		
		<h3>
			Users:
			<form:select path="user.id" >
			<form:options items="${project.users}"  itemValue="id" itemLabel="fullName" />
			</form:select>
			<form:errors path="user" cssClass="errors"></form:errors>
		</h3>
		<h3>
			Status:
			<form:select path="status.id" >
			<form:options items="${statuses}"  itemValue="id" itemLabel="name" />
			</form:select>
			<form:errors path="status" cssClass="errors"></form:errors>
		</h3>
		<h3>
			Status:
			<form:select path="priority.id" >
			<form:options items="${priorities}"  itemValue="id" itemLabel="name" />
			</form:select>
			<form:errors path="priority" cssClass="errors"></form:errors>
		</h3>
		<input type="submit" value="Send">
		<form:hidden path="id" />
	</form:form>
</body>
</html>