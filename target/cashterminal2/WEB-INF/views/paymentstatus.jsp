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
	<h1>Check status of the payment</h1>

	<form method="GET"	action="http://localhost:7001/cashterminal/checkpaymentstatus">
		<label for="paymentstatus">Payment ID:</label>
		<input type="text" id="paymentid" name="paymentid" value="" />
		<input type="submit" value="Check status" />
	</form>	

</body>
</html>
