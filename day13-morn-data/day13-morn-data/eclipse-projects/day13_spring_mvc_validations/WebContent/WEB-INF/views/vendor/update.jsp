<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3 align="center">${requestScope.mesg}</h3>
	<s:form method="post" modelAttribute="vendor">
		<table style="background-color: cyan; margin: auto;">
			<tr>
				<td>Your ID</td>
				<td><s:input path="id" readonly="true"/></td>
			</tr>
			<tr>
				<td>Enter User Email</td>
				<td><s:input path="email" readonly="true" /></td>
			</tr>
			<tr>
				<td>Enter Password</td>
				<td><s:password path="password" showPassword="true"/></td>
			</tr>
			<tr>
				<td>Enter User Name</td>
				<td><s:input path="name" /></td>
			</tr>
			<tr>
				<td>Enter City</td>
				<td><s:input path="city" /></td>
			</tr>
			<tr>
				<td>Enter Phone Number</td>
				<td><s:input path="phoneNo" /></td>
			</tr>
			<tr>
				<td>Enter Role</td>
				<td><s:input path="role" /></td>
			</tr>
			<tr>
				<td>Enter Reg Date</td>
				<td><s:input type="date" path="regDate" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Update Vendor" /></td>
			</tr>
		</table>
	</s:form>

</body>
</html>