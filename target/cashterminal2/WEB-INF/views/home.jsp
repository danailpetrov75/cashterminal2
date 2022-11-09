<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>CashTerminal home page</title>
</head>
<body>
	<h1>Wellcome to CashTerminal payment service!</h1>
	<h2>Please select operation:"</h2>

	<%!String contextPath;%>
	<%=contextPath = request.getContextPath()%>

	<ul>
		<li><a href=<%=contextPath + "/terminal"%>>>>Create and commit payment</a></li>
		<li><a href=<%=contextPath + "/paymentstatus"%>>>>Check status of the payment</a></li>		
	</ul>
</body>
</html>
