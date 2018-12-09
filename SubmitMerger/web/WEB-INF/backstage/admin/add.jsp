<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户添加</title>
</head>
<body>
<jsp:include page="../nav.jsp">
	<jsp:param value="添加" name="op"/>
</jsp:include>

<form action="${pageContext.request.contextPath}/AdminAdd" method="post">
	<table align="center" width="250">
		<tr>
			<td>用户名称：<input type="text" name="username"/></td>
		</tr>
		<tr>
			<td>用户密码：<input type="password" name="password"/></td>
		</tr>
		<tr>
			<td>用户昵称：<input type="text" name="nickname"/></td>
		</tr>
		<tr><td><input type="submit" value="提交"/></td></tr>
	</table>
</form>
</body>
</html>