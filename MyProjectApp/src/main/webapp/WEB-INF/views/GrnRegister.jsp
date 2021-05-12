<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
	<%@include file="UserMenu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white">
				<h1>Welcome to Grn Register Page</h1>
			</div>
			<div class="card-body">
				<form:form id="myForm" action="save" method="POST"
					modelAttribute="grn">
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="grnCode"> GRNCODE</label>
						</div>
						<div class="col-4">
							<form:input path="grnCode" class="form-control" />
						</div>
						<div class="col-4" id="grnCodeError"></div>
					</div>
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="grnType"> GRNTYPE</label>
						</div>
						<div class="col-4">
							<form:input path="grnType" class="form-control" />
						</div>
						<div class="col-4" id="grnTypeError"></div>
					</div>
					
					
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="po"> PURCHASE ORDER</label>
						</div>
						<div class="col-4">
							<form:select path="po.id" class="form-control">
								<form:option value="">-SELECT-</form:option>
								<form:options items="${poMap}"/>
							</form:select>
						</div>
						<div class="col-4" id="poError"></div>
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