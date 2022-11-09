<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<h1>Cash Terminal</h1>

	<form:form method="POST"
		action="http://localhost:7001/cashterminal/commit"
		modelAttribute="payment">

		<form:label path="receiverBankId">Service</form:label>
		<form:select path="receiverBankId">
			<c:forEach items="${availableService}" var="service">
				<form:option value="${service.bankId}" label="${service.name}" />
			</c:forEach>
		</form:select>
		<br>
		<br>
		<form:label path="phoneNumber">Phone number</form:label>
		<form:input path="phoneNumber" />
		<form:errors path="phoneNumber"/>
		<br>
		<br>
		<form:label path="pin">PIN</form:label>
		<form:input path="pin" />
		<form:errors path="pin"/>
		<br>
		<br>
		<form:label path="amounth">Amount</form:label>
		<form:input path="amounth" />
		<form:errors path="amounth"/>
		<br>
		<br>
		<form:label path="currency">Currency</form:label>
		<form:input path="currency" />
		<form:errors path="currency"/>
		<br>
		<br>
		<input type="submit" value="Pay" />

	</form:form>
</body>
</html>
