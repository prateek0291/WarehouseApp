<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="UserMenu.jsp" %>
<div class="container">
<h3>Welcome to Order Method Register Page</h3>
<form:form action="save" method="POST" modelAttribute="orderMethod">
<pre>
Order Mode:
 <form:radiobutton path="orderMode" value="Sale"/> Sale
 <form:radiobutton path="orderMode" value="Purchase"/> Purchase
Order Code:
 <form:input path="orderCode"/>
Order Type:
 <form:select path="orderType">
 	<form:option value="">-SELECT-</form:option>
 	<form:option value="FIFO">FIFO</form:option>
 	<form:option value="LIFO">LIFO</form:option>
 	<form:option value="FCFO">FCFO</form:option>
 	<form:option value="FEFO">FEFO</form:option>
 	
 	
 </form:select>  
Order Accept:
 <form:checkbox path="orderAcpt" value="Multi-Model"/> Multi-Model
 <form:checkbox path="orderAcpt" value="Accept Return"/> Accept Return
Description:
 <form:textarea path="description"/>
  <input type="submit" value="Create Order Method"/> 
</pre>
</form:form>
${message}
</div>
</body>
</html>