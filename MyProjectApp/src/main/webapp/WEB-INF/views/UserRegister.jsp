<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="UiMenu.jsp"%>
<div class="container">
<h3>Welcome to User Register Page</h3>
<form action="save" method="post">
<pre>
	NAME     : <input type="text" name="userName"/>
	EMAIL    : <input type="text" name="userEmail"/>
	PASSWORD : <input type="password" name="userPwd"/>
	ROLES    :
		<input  type="checkbox" name="roles" value="ADMIN">ADMIN
		<input  type="checkbox" name="roles" value="EMPLOYEE">EMPLOYEE
	<input type="submit" value="Register"/>		
</pre>
</form>
${message}
</div>	
</body>
</html>



