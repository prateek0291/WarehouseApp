<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<H3>WELCOME TO ORDER METHOD VIEW PAGE</H3>
<table border="1">
	<tr>
		<th>ID</th> <td>${ob.orderId }</td>
	</tr>
	<tr>
		<th>MODE</th> <td>${ob.orderMode }</td>
	</tr>
	<tr>
		<th>CODE</th> <td>${ob.orderCode }</td>
	</tr>
	<tr>
		
		<th>TYPE</th> <td>${ob.orderType }</td>
	</tr>
	<tr>
		<th>ACCEPT</th> <td>${ob.orderAcpt }</td>
	</tr>
	<tr>
		<th>NOTE</th> <td>${ob.description }</td>
	</tr>
</table>
</body>
</html>






