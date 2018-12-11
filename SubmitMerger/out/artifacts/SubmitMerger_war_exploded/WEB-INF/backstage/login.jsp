<%--
  Created by IntelliJ IDEA.
  User: ae
  Date: 2018/12/9
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h2 align="center">用户登录</h2>
<hr/>
<form action="${pageContext.request.contextPath}/AdminLogin" method="post">
    <table align="center" width="250">
        <tr>
            <td>用户名称：<input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>用户密码：<input type="password" name="password"/></td>
        </tr>
        <tr><td><input type="submit" value="用户登录"/></td></tr>
    </table>
    <h3>${msg}</h3>

</form>
</body>
</html>
