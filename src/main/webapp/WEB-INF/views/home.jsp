<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
   
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home page</title>
	<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>

<body>	
		
	<div class="form">
		<div class="header">
			<h2>Cash Terminal Online Service</h2>
		</div>
		<h3>Available operations:</h3>		
		<ul>
			<li><h3><a href="${pageContext.request.contextPath}/terminal">Payment</a></h3></li>
			<li><h3><a href="${pageContext.request.contextPath}/paymentstatus">Check payment status</a></h3></li>		
		</ul>
	</div>
</body>
</html>
