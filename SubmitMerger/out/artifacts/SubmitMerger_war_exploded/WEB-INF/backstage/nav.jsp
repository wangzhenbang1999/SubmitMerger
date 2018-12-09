<%--
  Created by IntelliJ IDEA.
  User: ae
  Date: 2018/12/9
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="inc/top.jsp" %>
<h2 align="center">用户<%=request.getParameter("op") %>功能</h2>

<a href="<%=request.getContextPath() %>/list.jsp">用户列表</a>&nbsp;
<a href="<%=request.getContextPath() %>/add.jsp">添加用户</a>&nbsp;

<hr/>
