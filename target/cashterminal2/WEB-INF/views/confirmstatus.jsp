<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Payment status</title>
</head>
<body>
	<h1>Cash Terminal</h1>	
	
	<c:if test="${paymentstatus != null}">
		<h2>
			Status of the payment with id: <c:out value="${paymentstatus.getDescription()}"/>
		</h2>	
	</c:if>

</body>
</html>
