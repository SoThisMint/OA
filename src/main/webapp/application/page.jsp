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
<a href="sysOrg/page/${pageInfo.pages-1}">上一页</a>
<a href="sysOrg/page/${pageInfo.pages+1}">下一页</a>
<a href="sysOrg/page/${pageInfo.pages}">尾页</a>
</body>
</html>
