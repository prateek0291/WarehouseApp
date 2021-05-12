<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<%@include file="UiMenu.jsp" %>
<div class="container">
<form action="../login" method="POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<div class="row">	
	<div class="col-4">USER NAME</div>
	<div class="col-4"><input type="text" name="username" class="form-control"/></div>
</div>
<div class="row">	
	<div class="col-4">PASSWORD</div>
	<div class="col-4"><input type="password" name="password" class="form-control"/></div>
</div>
<div class="row">	
	<div class="col-4"></div>
	<div class="col-4">
 	<input type="submit" value="Login" class="btn btn-info"/>
	</div>
</div>
<c:if test="${param.error!=null}">
	<div class="text-danger">Invaild Username/password</div>
</c:if>
<c:if test="${param.logout!=null }">
	<div class="text-success">Logout Successful</div>
</c:if>

</form>
</div>
</body>
</html>