<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Payment status confirmaiton page</title>
	<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>
<body>
	
	<div class="form">	
		<h2>Payment verification result:</h2>
		<c:if test="${paymentStatusResult != null}">
			<h3>
				Payment status: <c:out value="${paymentStatusResult.getDescription()}"/>
			</h3>	
		</c:if>
	</div>
</body>
</html>
