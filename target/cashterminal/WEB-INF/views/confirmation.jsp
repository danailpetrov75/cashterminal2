<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Payment confirmation page</title>
	<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>

<body>	
	<div class="form">		
		
		<c:if test="${fieldErrors != null}" >
			<c:forEach items="${fieldErrors}" var="fe">
				<h4><c:out value="${fe.getDefaultMessage()}"/></h4>
			</c:forEach>
		</c:if>
		
		<c:if test="${fieldErrors == null}" >
			<c:if test="${paymentRequestResult != null && paymentRequestResult.getSuccess().booleanValue()}">
	
				<h3>Your payment request has been processed.</h3>
				<h4>
					Your payment id is:<br>
					<c:out value="${paymentRequestResult.getPaymentId()}" />
				</h4>
				<h4>
					Use your payment id to track further status of your payment <a href="${pageContext.request.contextPath}/paymentstatus">here</a>
				</h4>			
			</c:if>
			
			<c:if
				test="${paymentRequestResult != null && !paymentRequestResult.getSuccess().booleanValue()}">
				<h4>
					Some error occur in your payment request! See details below:
					<c:out value="${paymentRequestResult.getErrors().toString()}" />
				</h4>
			</c:if>
		
		<div class="payment-details">
			<c:if test="${paymentRequestResult != null && payment != null}">
			<h4>Your payment details:</h4>			
			Amount:
			<c:out value="${payment.amounth}" /><br>
			Currency:
			<c:out value="${payment.currency}" /><br>
			Phone number:
			<c:out value="${payment.phoneNumber}" /><br>
			PIN:
			<c:out value="${payment.pin}" /><br>
			Bank Id:
			<c:out value="${payment.receiverBankId}" />
			</c:if>
		</div>
		</c:if>
	</div>
</body>
</html>
