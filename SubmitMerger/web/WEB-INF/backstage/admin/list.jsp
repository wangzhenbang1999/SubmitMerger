<%--
  Created by IntelliJ IDEA.
  User: ae
  Date: 2018/12/9
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- 分页 -->
    <link rel="stylesheet" type="text/css" href="backstage/css/page.css"/>
</head>
<body>
<jsp:include page="../nav.jsp">
    <jsp:param value="列表" name="op"/>
</jsp:include>
<table align="center" border="1" width="800" style="margin-top: 40px;">
    <tr>
        <td colspan="7">
            <form action="list.jsp">
                用户名：<input type="text" name="username" value="${param.username}"/>
                昵称：<input type="text" name="nickname" value="${param.nickname}"/>
                <input type="submit" value="查询"/>
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="7">
            共有${pageInfo.getRecordsFiltered()}条记录，当前是第${pageInfo.getCurrentPage()}页，每页显示${pageInfo.getPageSize()}条记录
        </td>
    </tr>
    <tr>
        <td>用户标识</td><td>用户名</td><td>用户密码</td><td>用户昵称</td><td>用户类型</td><td>用户状态</td>
        <c:if test="${admin.getAdminType()==1}">
            <td>操作</td>
        </c:if>
    </tr>
    <c:forEach items="${pageInfo.getDatas()}" var="a">
        <tr>
            <td>${a.getId()}</td>
            <td>${a.getUsername()}</td>
            <td>${a.getPassword()}</td>
            <td>${a.getNickname()}</td>
            <td>
                <c:choose>
                    <c:when test="${a.getAdminType()==0}">
                        普通管理员
                    </c:when>
                    <c:otherwise>
                        <span style='color:green'>超级管理员</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${a.getStatus()==0}">
                        <span style='color:green'>启用</span>
                        <c:if test="${admin.getAdminType()==1}">
                            <a href="<%=request.getContextPath()%>/AdminSetStatus?id=${a.getId()}">冻结</a>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <span style='color:red'>冻结</span>
                        <c:if test="${admin.getAdminType()==1}">
                            <a href="<%=request.getContextPath()%>/AdminSetStatus?id=${a.getId()}">启用</a>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </td>
            <c:if test="${admin.getAdminType()==1}">
                <td>
                    <a href="<%=request.getContextPath()%>/AdminDelete?id=${a.getId()}">删除</a>&nbsp;<a href="<%=request.getContextPath()%>/AdminUpdate?id=${a.getId()}">更新</a>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>

<div style="text-align: center;margin: 42px 0;">
    <div id="pagination" class="paging"></div>
</div>

<script src="backstage/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="backstage/js/page.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function() {
        Pagination.Init(document.getElementById('pagination'), {
            pageTotal: parseInt("${pageInfo.getPages()}"),    // 总页数
            currentPage: parseInt("${pageInfo.getCurrentPage()}"), // 当前页
            step: 3,          // 当前页前后显示的页数
            url:"list.jsp?username=${param.username}&nickname=${param.nickname}"
        });
    });
</script>

</body>
</html>