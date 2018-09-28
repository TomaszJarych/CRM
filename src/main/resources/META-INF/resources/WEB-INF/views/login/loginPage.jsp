<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login user</title>
</head>
<body>
	<div align="center">
		<h1>Select user:</h1>
		<form  method="post" >
		<select name="userLogin">
			<c:forEach items="${userList}" var="user">
				<option value="${user.login}" >${user.fullName}</option>
			</c:forEach>
		</select> 
		<br>
		<br>
		<input type="submit" value="Select">
		</form>
	</div>

</body>
</html>