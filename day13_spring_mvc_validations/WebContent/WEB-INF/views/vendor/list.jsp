<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 align="center">Last Action Status : ${requestScope.mesg}</h3>
	<h3 align="center">Vendor List</h3>
	<table style="background-color: cyan; margin: auto;" border="1">
		<c:forEach var="v" items="${requestScope.vendor_list}">

			<tr>
				<td>${v.name}</td>
				<td>${v.email}</td>
				<td>${v.city}</td>
				<td>${v.phoneNo}</td>
				<td><a href="<spring:url value='/vendor/update?vid=${v.id}'/>">Update</a></td>
				<td><a href="<spring:url value='/vendor/delete?vid=${v.id}'/>">Delete</a></td>
			</tr>

		</c:forEach>
		<tr>
		<td colspan="2"><a href="<spring:url value='/vendor/add'/>">Add New Vendor</a></td>
		</tr>
	</table>
</body>
</html>