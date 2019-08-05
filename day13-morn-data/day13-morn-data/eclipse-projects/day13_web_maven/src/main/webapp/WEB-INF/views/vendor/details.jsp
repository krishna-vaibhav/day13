<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- import spring supplied JSP tag library -->
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 align="center">${requestScope.mesg}</h3>
<h4>Vendor Details : ${sessionScope.vendor_dtls}</h4>
<h4 align="center"><a href="<spring:url value='/vendor/logout'/>">Log Out</a></h4>
</body>
</html>





