<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin panel</title>
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

	<h1 align="center">Hello admin ${loggedUser.fullName}</h1>

	<div>
		<h1>Admin panel</h1>
		<h2>Users</h2>
		<h3>
			<a href="${pageContext.request.contextPath}/user/all"> Show all users</a>
			<br>
		</h3>
		<h3>
			<a href="${pageContext.request.contextPath}/user/add"> Add new User </a>
			<br>
		</h3>
		<h2>Project</h2>
		<h3>
			<a href="${pageContext.request.contextPath}/project/all"> Show all projects</a>
			<br>
		</h3>
		<h3>
			<a href="${pageContext.request.contextPath}/project/add"> Add new project</a>
			<br>
		</h3>
		<h2>Task</h2>
		<h3>
			<a href="${pageContext.request.contextPath}/task/all"> Show all tasks</a>
			<br>
		</h3>
		<h2>Status</h2>
		<h3>
			<a href="${pageContext.request.contextPath}/status/all"> Show all statuses</a>
			<br>
		</h3>
		<h3>
			<a href="${pageContext.request.contextPath}/status/add"> Add new status</a>
			<br>
		</h3>
		<h2>Priority</h2>
		<h3>
			<a href="${pageContext.request.contextPath}/priority/all"> Show all priorities</a>
			<br>
		</h3>
		<h3>
			<a href="${pageContext.request.contextPath}/priority/add"> Add new priority</a>
			<br>
		</h3>
		
	</div>


</body>
</html>