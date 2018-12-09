<%@page import="com.wangzhenbang.SubmitMerger.exception.SubmitMergerException"%>
<%@page import="com.wangzhenbang.SubmitMerger.util.DAOFactory"%>
<%@page import="com.wangzhenbang.SubmitMerger.dao.IAdminDao"%>
<%@page import="com.wangzhenbang.SubmitMerger.po.Admin"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户更新</title>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	IAdminDao ad = DAOFactory.getAdminDao();
	Admin a = ad.findById(id);
%>
</head>
<body>
<jsp:include page="../nav.jsp">
	<jsp:param value="更新" name="op"/>
</jsp:include>

<form action="${pageContext.request.contextPath}/AdminUpdate" method="post">
<input type="hidden" name="id" value="<%=a.getId()%>"/>
<table align="center" width="500" border="1">
	<tr>
		<td>用户名称:</td><td>
		<%=a.getUsername() %>
		</td>
	</tr>
	<tr>
		<td>用户密码:</td><td><input type="password" name="password" value="<%=a.getPassword()%>"/>
		<% if(request.getAttribute("passwordError")!=null) { %><span><%=request.getAttribute("passwordError") %></span><%} %>
		</td>
	</tr>
	<tr>
		<td>用户昵称:</td><td><input type="text" name="nickname" value="<%=a.getNickname()%>"/>
		<% if(request.getAttribute("nicknameError")!=null) { %><span><%=request.getAttribute("nicknameError") %></span><%} %>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="更新用户"/><input type="reset" value="重置信息"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>