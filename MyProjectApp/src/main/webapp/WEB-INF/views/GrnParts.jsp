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
	<%@include file="UserMenu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white">
				<H3>WELCOME TO GRN PARTS PAGE</H3>
			</div>
			<div class="card-body">
				<table class="table table-bordered table-hover">
					<tr class="bg-warning ">


						<th>PART CODE</th>
						<th>BASE COST</th>
						<th>QUANTITY</th>
						<th>LINE VALUE</th>
						<th>OPERATION</th>
					</tr>
					<c:forEach items="${list}" var="dtl">
						<tr>
							<td>${dtl.partCode}</td>
							<td>${dtl.baseCost}</td>
							<td>${dtl.qnty}</td>
							<td>${dtl.qnty * dtl.baseCost}</td>
							<td>
								<c:choose>
									<c:when test="${'NONE' eq dtl.partStatus }">
										<a href="partStatus?id=${dtl.id}&status=ACCEPT&grnId=${dtl.grn.id}" class="btn btn-success">ACCEPT</a>
										<a href="partStatus?id=${dtl.id}&status=REJECT&grnId=${dtl.grn.id}" class="btn btn-danger">REJECT</a>
									</c:when>
									<c:when test="${'ACCEPT' eq dtl.partStatus }">
										<b class="text-success">ACCEPTED</b>
									</c:when>
									<c:when test="${'REJECT' eq dtl.partStatus }">
										<b class="text-danger">REJECTED</b>
									</c:when>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
