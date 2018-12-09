<%--
  Created by IntelliJ IDEA.
  User: ae
  Date: 2018/12/9
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.wangzhenbang.SubmitMerger.po.Admin"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div style="text-align:right;">
    欢迎 [ ${admin.getNickname()} ] 使用我们的系统！
    &nbsp;<a href="<%=request.getContextPath()%>/update.jsp?id=${admin.getId()}">修改个人信息</a>
    &nbsp;<a href="<%=request.getContextPath()%>/AdminLogout">退出系统 </a>
</div>