<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
	<div class="card-header bg-info">
		<h3 class="text-white">WELCOME TO PURCHASE ORDER PARTS (SCREEN#2)</h3>
	</div>

<div class="card-body">
<table class="table table-bordered">
	<tr>
		<td>ORDER CODE</td>
		<td class="text-success"><b>${parent.orderCode}</b></td>
	
		<td>ORDER STATUS</td>
		<td class="text-success"><b>${parent.status}</b></td>
	</tr>
</table>
<hr/>
<c:if test="${'OPEN' eq parent.status || 'PICKING' eq parent.status}">
<form:form action="addPart" method="post" modelAttribute="purchaseDtl">
<div class="row">
	<div class="col-4">SELECT PART</div>
	<div class="col-4">
			<form:select path="part.id" class="form-control">
				<form:options items="${partsMap}"/>
			</form:select>
	</div>
</div>
<div class="row">
	<div class="col-4">
		Specify Quantity 
	</div>
	<div class="col-4">
		<form:input path="qty" class="form-control"/>
	</div>
</div>
<div class="row">
	<div class="col-4"></div>
	<div class="col-4"><input type="submit" value="Add Part" class="btn btn-info"/></div>
</div>
<input type="hidden" name="po.id" value="${parent.id}"/>
</form:form>
</c:if>	

</div> <!-- card body -->

<table class="table table-bordered">
	<tr>
		<th>SlNo</th>
		<th>PART</th>
		<th>BASE COST</th>
		<th>QTY</th>
		<c:if test="${'PICKING' eq parent.status }">
		<th>OPERATION</th>
		</c:if>
	</tr>
	<c:forEach items="${childs}" var="dtl">
		<tr>
			<td>${dtl.slno}</td>
			<td>${dtl.part.partCode}</td>
			<td>${dtl.part.baseCost}</td>
			<td>${dtl.qty}</td>
			<c:if test="${'PICKING' eq parent.status }">
			<td><a href="removePart?dtlId=${dtl.id}&poId=${parent.id}" class="btn btn-danger">DELETE</a></td>
			</c:if>
		</tr>
	</c:forEach>	
</table>
	<c:if test="${'PICKING' eq parent.status }">
		<a href="placeOrder?poId=${parent.id}" class="btn btn-success">PLACE ORDER(CONFORM)</a>
	</c:if>
</div>
</div>	
</body>
</html>


