<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Cash terminal page</title>
	<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>
<body>	

	<div class="form">
		<h2>Payment Transaction Details:</h2>
		
		<form:form method="POST" action="${pageContext.request.contextPath}/commit"	modelAttribute="payment">
		<form:errors path = "*" cssClass = "errorblock" element = "div" />	
			<div>
				<form:label path="receiverBankId">Service</form:label>
				<form:select path="receiverBankId">
					<c:forEach items="${availableService}" var="service">
						<form:option value="${service.bankId}" label="${service.name}" />
					</c:forEach>
				</form:select>
			</div>
			
			<div>
				<form:label path="phoneNumber">Phone number</form:label>
				<form:input path="phoneNumber" />
				<form:errors path="phoneNumber" cssClass="error" />				
			</div>	
		
			<div>
				<form:label path="pin">PIN</form:label>
				<form:input path="pin" />
				<form:errors path="pin" cssClass="error" />				
			</div>	
			
			<div>
				<form:label path="amounth">Amount</form:label>
				<form:input path="amounth" />
				<form:errors path="amounth" cssClass="error" />				
			</div>	
			
			<div>
				<form:label path="currency">Currency</form:label>
				<form:select path="currency">					
					<form:option value="EUR" label="EUR" />
					<form:option value="USD" label="USD" />
					<form:option value="BGN" label="BGN" />
					<form:option value="DKK" label="DKK" />
					<form:option value="SEK" label="SEK" />
					<form:option value="NOK" label="NOK" />
				</form:select>
				<form:errors path="currency" />
			</div>	
			
			<div>
				<input class="center" type="submit" value="Pay" />
			</div>
		</form:form>
	</div>	
	
</body>
</html>
