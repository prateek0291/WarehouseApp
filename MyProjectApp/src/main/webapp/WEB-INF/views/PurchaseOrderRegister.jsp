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
<%@include file="UserMenu.jsp"%>
	<div class="container">
<H3>WELCOME TO PURCHASE ORDER REGISTER PAGE</H3>
<form:form action="save" method="POST" modelAttribute="purchaseOrder">
<pre>
ORDER CODE   : <form:input path="orderCode"/>
SHIPMENT CODE: <form:select path="sob.shipId">
					<form:option value="">-SELECT-</form:option>
					<form:options items="${shipMap}"/>
			   </form:select>
VENDOR  : <form:select path="vendor.id">
			<form:option value="">-SELECT-</form:option>
			<form:options items="${whVenMap}"/>
		 </form:select>			   
REFERENCE NUMBER : <form:input path="refNum"/>
QUALITY CHECK :
   <form:radiobutton path="qltyChk" value="Required"/> Required
   <form:radiobutton path="qltyChk" value="Not Required"/>Not Required
DEFAULT STATUS  : <form:input path="status" readonly="true"/>
NOTE  : <form:textarea path="note"/>
 <input type="submit" value="Place Order"/>
</pre>
</form:form>
${message}
</div>
</body>
</html>







