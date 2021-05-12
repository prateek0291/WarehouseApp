<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
</head>
<body>
	<%@include file="UserMenu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white">
				<h1>Welcome to Part Data Page</h1>
			</div>
			<div class="card-body">
				<table class="table table-bordered table-hover">
					<tr class="bg-warning ">
						<td>PARTCODE</td>
						<td>PARTLEN</td>
						<td>PARTWID</td>
						<td>PARTHGT</td>
						<td>BASECOST</td>
						<td>BASECURRENCY</td>
						<td>UOM</td>
						<td>ORDER SALE</td>
						<td>ORDER PURCHASE</td>
						<td>NOTE</td>
						<td colspan='2'>OPERATIONS</td>
					</tr>
					<c:forEach items="${list}" var="ob">
						<tr>
							<td><c:out value="${ob.partCode}" /></td>
							<td><c:out value="${ob.partLen}" /></td>
							<td><c:out value="${ob.partWid}" /></td>
							<td><c:out value="${ob.partHgt}" /></td>
							<td><c:out value="${ob.baseCost}" /></td>
							<td><c:out value="${ob.baseCurrency}" /></td>
							<td><c:out value="${ob.uomOb.uomModel}" /></td>
							<td><c:out value="${ob.omSaleOb.orderCode}" /></td>
							<td><c:out value="${ob.omPurcasheOb.orderCode}" /></td>
							<td><c:out value="${ob.note}" /></td>
							<td><a href='delete?id=${ob.id}'>DELETE</a></td>
							<td><a href='edit?id=${ob.id}'>EDIT</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="card-footer bg-info text-white">
				<b>${message}</b>
			</div>
		</div>
	</div>
</body>
</html>
