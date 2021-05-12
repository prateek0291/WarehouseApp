<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
	<%@include file="UserMenu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white">
				<h1>Welcome to Part Register Page</h1>
			</div>
			<div class="card-body">
				<form:form id="myForm" action="save" method="POST"
					modelAttribute="part">
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="partCode"> PARTCODE</label>
						</div>
						<div class="col-4">
							<form:input path="partCode" class="form-control" />
						</div>
						<div class="col-4" id="partCodeError"></div>
					</div>
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="partLen"> PARTLEN</label>
						</div>
						<div class="col-4">
							<form:input path="partLen" class="form-control" />
						</div>
						<div class="col-4" id="partLenError"></div>
					</div>
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="partWid"> PARTWID</label>
						</div>
						<div class="col-4">
							<form:input path="partWid" class="form-control" />
						</div>
						<div class="col-4" id="partWidError"></div>
					</div>
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="partHgt"> PARTHGT</label>
						</div>
						<div class="col-4">
							<form:input path="partHgt" class="form-control" />
						</div>
						<div class="col-4" id="partHgtError"></div>
					</div>
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="baseCost"> BASECOST</label>
						</div>
						<div class="col-4">
							<form:input path="baseCost" class="form-control" />
						</div>
						<div class="col-4" id="baseCostError"></div>
					</div>
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="baseCurrency"> BASECURRENCY</label>
						</div>
						<div class="col-4">
							<form:select path="baseCurrency" class="form-control">
								<form:option value="">-SELECT-</form:option>
								<form:option value="INR">INR</form:option>
								<form:option value="USD">USD</form:option>
								<form:option value="AUD">AUD</form:option>
							</form:select>
						</div>
						<div class="col-4" id="baseCurrencyError"></div>
					</div>
					
					
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for=""> UOM </label>
						</div>
						<div class="col-4">
							<form:select path="uomOb.id" class="form-control">
								<form:option value="">-SELECT-</form:option>
								<form:options items="${uomMap}" />
							</form:select>
						</div>
						<div class="col-4" id="baseCurrencyError"></div>
					</div>
					
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for=""> ORDER METHOD  </label>
						</div>
						<div class="col-4">
							<form:select path="omSaleOb.orderId" class="form-control">
								<form:option value="">-SELECT SALE-</form:option>
								<form:options items="${omSaleMap}" />
							</form:select>
							
							<form:select path="omPurcasheOb.orderId" class="form-control">
								<form:option value="">-SELECT PURCHASE-</form:option>
								<form:options items="${omPurchaseMap}" />
							</form:select>
						</div>
						<div class="col-4" id="baseCurrencyError"></div>
					</div>
					
					
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="note"> NOTE</label>
						</div>
						<div class="col-4">
							<form:textarea path="note" class="form-control" />
						</div>
						<div class="col-4" id="noteError"></div>
					</div>
					<input type="submit" value="Register" class="btn btn-success" />
				</form:form>
			</div>
			<div class="card-footer bg-info text-white">
				<b>${message}</b>
			</div>
		</div>
	</div>
</body>
</html>