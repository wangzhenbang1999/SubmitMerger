<%--
  Created by IntelliJ IDEA.
  User: ae
  Date: 2018/12/9
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>出现错误：${msg}</h1>
<a href="login.jsp">返回</a>
<script>
    function back(){
        location.href = "login.jsp";
    }
    setTimeout("back()", 3000);
</script>
</body>
</html>
