<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="UserMenu.jsp" %>
	<div class="container">

		<div class="card">

			<div class="card-header bg-primary text-center text-white text-uppercase">
				<h3>Welcome to Shipment Type Register Page</h3>
			</div>

			<div class="card-body">

				<form:form action="save" method="POST" modelAttribute="shipmentType">


					<div class="row">
						<div class="col-4">
							<label for="shipMode">SHIPMENT MODE</label>
						</div>
						<div class="col-4">
							<form:select path="shipMode" class="form-control">
								<form:option value="">-SELECT-</form:option>
								<form:option value="Air">Air</form:option>
								<form:option value="Truck">Truck</form:option>
								<form:option value="Ship">Ship</form:option>
								<form:option value="Train">Train</form:option>
							</form:select>
						</div>
						<div class="col-4">
							<!-- Error Message -->
						</div>
					</div>


					<div class="row">
						<div class="col-4">
							<label for="shipCode">SHIPMENT CODE</label>
						</div>
						<div class="col-4">
							<form:input path="shipCode" class="form-control" />
						</div>
						<div class="col-4">
							<!-- Error Message -->
						</div>
					</div>

					<div class="row">
						<div class="col-4">
							<label for="enbShip">ENABLE SHIPMENT</label>
						</div>
						<div class="col-4">
							<form:select path="enbShip" class="form-control">
								<form:option value="">-SELECT-</form:option>
								<form:option value="YES">YES</form:option>
								<form:option value="NO">NO</form:option>
							</form:select>
						</div>
						<div class="col-4"></div>
					</div>



					<div class="row">
						<div class="col-4">
							<label for="shipGrad">SHIPMENT GRADE</label>
						</div>
						<div class="col-4">
							<form:radiobutton path="shipGrad" value="A"  />
							A
							<form:radiobutton path="shipGrad" value="B" />
							B
							<form:radiobutton path="shipGrad" value="C" />
							C

						</div>
						<div class="col-4"></div>
					</div>


					<div class="row">
						<div class="col-4">
							<label for="shipDesc">DESCRIPTION</label>
						</div>
						<div class="col-4">
							<form:textarea path="shipDesc" class="form-control"/>

						</div>
						<div class="col-4"></div>
					</div>

					<div class="row">
						<div class="col-4"></div>
						<div class="col-4">
							<input type="submit" value="Create" class="btn btn-success"/>
							 <input type="reset" value="Clear" class="btn btn-danger"/>

						</div>
					</div>
				</form:form>
			</div>
			<c:if test="${!empty message }">
				<div class="card-footer bg-info text-white text-center"><b>${message}</b></div>
			</c:if>
		</div>
		<!-- card end -->
	</div>
	<!-- container end -->
</body>
</html>






