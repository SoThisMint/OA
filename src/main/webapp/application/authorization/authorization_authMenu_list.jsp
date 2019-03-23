<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/3/18
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <base href="<%=request.getContextPath()+"/"%>">
    <link href="css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/H-ui.admin.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css"/>

</head>
<body id="userBody">
<div class="pd-20">

    <div class="cl pd-5 bg-1 bk-gray"></div>
    <table class="table table-border table-bordered table-hover table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="5">菜单管理</th>
        </tr>
        <tr class="text-c">
            <th width="40">菜单ID</th>
            <th width="100">菜单名称</th>
            <th width="100">菜单类型</th>
            <th width="100">菜单描述</th>
            <th width="70">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="sysMenu">
            <tr class="text-c">
                <td>${sysMenu.menuId}</td>
                <td>${sysMenu.menuName}</td>
                <td>${sysMenu.menuType}</td>
                <td>${sysMenu.menuDesc}</td>
                <td class="f-14">
                    <a title="撤销权限" href="javascript:;" onclick="remove_role_menu(this,${sysMenu.menuId})" class="ml-5"
                       style="text-decoration:none">
                        <i class="Hui-iconfont">&#xe6e2;</i></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--装展示分页导航的容器--%>
    <jsp:include page="/application/common/page.jsp">
        <jsp:param name="bodyId" value="authorization"/>
    </jsp:include>

</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>

<script
        src="https://code.jquery.com/jquery-1.12.4.min.js"
        integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
        crossorigin="anonymous"></script>

<script type="text/javascript">

    /*解除角色和菜单的关系*/
    function remove_role_menu(obj, id) {
        layer.confirm('确定要解除与该菜单的关系？', function (index) {
            //发送ajax请求去删除该条数据
            $.ajax({
                url: "authorization/delUserFormMenu",
                type: "POST",
                data: "menuId=" + id + "&roleId=" +${params}.roleId,
                success: function (data) {
                    if (data.result) {
                        layer.msg('解决授权成功!', {icon: 1, time: 2000}, function () {
                            $("#query").click();
                        });
                    } else {
                        layer.msg('解决授权失败!', {icon: 2, time: 2000});
                    }
                }
            })

        });
    }

</script>
</body>
</html>
