<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/18
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="sysOrg/page/1">首页</a>
<c:if test="${pageInfo.pageNum!=1}">
    <a href="sysOrg/page/${pageInfo.pageNum-1}">上一页</a>
</c:if>
<c:if test="${pageInfo.pageNum!=pageInfo.pages}">
    <a href="sysOrg/page/${pageInfo.pageNum+1}">下一页</a>
</c:if>
<a href="sysOrg/page/${pageInfo.pages}">尾页</a>
</body>
</html>
