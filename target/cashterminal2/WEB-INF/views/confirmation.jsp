<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Payment confirmation page</title>
</head>

<body>

	<%!String contextPath;%>
	<%=contextPath = request.getContextPath()%>

	<c:if test="${paymentRequestResult != null && paymentRequestResult.getSuccess().booleanValue()}">		
		<h1>
			Your payment request is ready to be processed.
			
			Your payment id is: <c:out value="${paymentRequestResult.getPaymentId()}" />
			
			Use your payment id to track further status of your payment <a href=<%=contextPath + "/paymentstatus"%>>here</a>
		</h1>
	</c:if>
	
	<c:if test="${paymentRequestResult != null && !paymentRequestResult.getSuccess().booleanValue()}">
		<h1>
			Some error occur in your payment request! See details below:						
			<c:out value="${paymentRequestResult.getErrors().toString()}" />
		</h1>		
	</c:if>

	<c:if test="${paymentRequestResult != null && payment != null}">
		<h2>Your payment details:</h2>
		<br> Amount:
		<c:out value="${payment.amounth}" />
		Currency:
		<c:out value="${payment.currency}" />
		Phone number:
		<c:out value="${payment.phoneNumber}" />
		PIN:
		<c:out value="${payment.pin}" />
		Bank Id:
		<c:out value="${payment.receiverBankId}" />
	</c:if>	
	
</body>
</html>
