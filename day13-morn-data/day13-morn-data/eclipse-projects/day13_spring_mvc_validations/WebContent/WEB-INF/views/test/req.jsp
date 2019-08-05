<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Hello , ${requestScope.user_nm}</h4>
<h4>Quantity  , ${requestScope.product_qty}</h4>
<h4>Server Date : <fmt:formatDate value="${requestScope.date}" pattern="dd-MM-yyyy"/>
 </h4>
<h4>Request Params : ${param}</h4>
</body>
</html>