<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="base">${pageContext.request.requestURL}</c:set>
<c:set var="url"
	value="${fn:replace(base, pageContext.request.requestURI, pageContext.request.contextPath)}" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="../resources/css/custom.css" />

<!-- ****************MENU BAR STARTS HERE********* -->

<nav class="navbar navbar-expand-sm bg-dark navbar-dark ">
	<!-- Brand -->
	<a class="navbar-brand " href="#">
	<!-- <img src="../resources/images/loginLogo1.jpg" width="100" height="60" /> -->
	WAREHOUSE APP
	</a>

	<!-- Links -->
	<ul class="navbar-nav mybg">
		



		<!-- **** START: Uom ***-->
		<li class="nav-item dropdown">
		<a class="nav-link dropdown-toggle text-white"
			href="#" id="navbardrop" data-toggle="dropdown"> <b> User </b> </a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="${url}/user/register">Register</a>
				<a class="dropdown-item" href="${url}/user/login">Login</a>
			</div>
       </li>
       <!--** END UOM **-->

		<!-- Logout Link -->
		<li class="nav-item"><a class="nav-link text-white" href="${url}/user/register"><b>Register</b></a></li>
		<li class="nav-item"><a class="nav-link text-white" href="${url}/user/login"><b>Login</b></a></li>
		
	</ul>
</nav>