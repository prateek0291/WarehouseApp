<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
	<%@include file="UserMenu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white">
				<h1>Welcome to WhUserType Register Page</h1>
			</div>
			<div class="card-body">
				<form:form id="myForm" action="save" method="POST"
					modelAttribute="whusertype" >
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="userType"> USERTYPE</label>
						</div>
						<div class="col-4">
							<form:radiobutton path="userType" value="Vendor" />Vendor
							<form:radiobutton path="userType" value="Customer" />Customer
						</div>
						<div class="col-4" id="userTypeError"></div>
					</div>
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="userCode"> USERCODE</label>
						</div>
						<div class="col-4">
							<form:input path="userCode" class="form-control" />
						</div>
						<div class="col-4" id="userCodeError"></div>
					</div>
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="userFor"> USERFOR</label>
						</div>
						<div class="col-4">
							<form:input path="userFor" class="form-control" />
						</div>
						<div class="col-4" id="userForError"></div>
					</div>
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="userEmail"> USEREMAIL</label>
						</div>
						<div class="col-4">
							<form:input path="userEmail" class="form-control" />
						</div>
						<div class="col-4" id="userEmailError"></div>
					</div>
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="userContact"> USERCONTACT</label>
						</div>
						<div class="col-4">
							<form:input path="userContact" class="form-control" />
						</div>
						<div class="col-4" id="userContactError"></div>
					</div>
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="userIdType"> USERIDTYPE</label>
						</div>
						<div class="col-4">
							<form:select path="userIdType" class="form-control">
								<form:option value="">-SELECT-</form:option>
								<form:option value="PANCARD">PANCARD</form:option>
								<form:option value="AADHAR">AADHAR</form:option>
								<form:option value="OTHER">OTHER</form:option>
							</form:select>
						</div>
						<div class="col-4" id="userIdTypeError"></div>
					</div>
					
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="ifOther"> IFOTHER</label>
						</div>
						<div class="col-4">
							<form:input path="ifOther" class="form-control" />
						</div>
						<div class="col-4" id="ifOtherError"></div>
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
					
<!-- 					<div class="row">
						<div class="col-4">
							<label for="fileOb">IMAGE</label>
						</div>
						<div class="col-4">
							<input type="file" name="fileOb"  id="fileOb"/>
						</div>
						
					</div>
 -->					
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
