<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Welcome to Order Method Data Page</h3>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>MODE</th>
			<th>CODE</th>
			<th>TYPE</th>
			<th>NOTE</th>
			<th colspan="3">OPERATION</th>
		</tr>
		<c:forEach items="${list}" var="ob">
			<tr>
				<td>${ob.orderId}</td>
				<td>${ob.orderMode}</td>
				<td>${ob.orderCode}</td>
				<td>${ob.orderType}</td>
				<td>${ob.description}</td>

				<td><a href="delete?oid=${ob.orderId}">DELETE</a></td>
				<td><a href="view?oid=${ob.orderId}">VIEW</a></td>
				<td><a href="edit?oid=${ob.orderId}">EDIT</a></td>
			</tr>
		</c:forEach>
	</table>
	${message}

</body>
</html>




