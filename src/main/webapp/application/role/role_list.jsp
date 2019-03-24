<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/18
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()+"/"%>"/>
    <link href="css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/H-ui.admin.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css"/>
</head>
<body id="rolebody">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span
        class="c-gray en">&gt;</span> 角色管理 <a class="btn btn-success radius r mr-20"
                                              style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
    <div class="text-c">
        <div class="row cl ">
            <div class="formControls col-4">
                角色名： <input type="text" class="input-text" style="width: 250px" id="roleName"
                             value="${sysRole.roleName}">
            </div>
            <div class="formControls col-4">
                是否有效：<span class="select-box" style="width:150px">
              <select name="brandclass" class="select" size="1" id="flag">
                    <option value="1" selected="selected">是</option>
                    <option value="0">否</option>
              </select>
			</span>
            </div>
        </div>
        <div class="row cl">
            <div class="cl pd-5">
                <button type="button"
                        class="btn btn-success radius" id="" name="" onclick="searchWithConditions()">
                    <i class="Hui-iconfont">&#xe665;</i> 搜索
                </button>
            </div>
        </div>
    </div>
    <div class="cl pd-5 bg-1 bk-gray"><span class="l"> <a href="javascript:;" onclick="batchDelete()"
                                                          class="btn btn-danger radius"><i
            class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" href="javascript:;"
                                                          onclick="admin_role_add('添加角色','application/role/admin-role-add.jsp','800','500')"><i
            class="Hui-iconfont">&#xe600;</i> 添加角色</a> </span> <span class="r">共有数据：<strong>${pageInfo.total}</strong> 条</span>
    </div>
    <table class="table table-border table-bordered table-hover table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="7">角色管理</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" value="" name=""></th>
            <th width="40">角色ID</th>
            <th width="100">角色名称</th>
            <th width="500">描述</th>
            <th width="100">创建时间</th>
            <th width="70">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="sysRole">
            <tr class="text-c">
                <td><input type="checkbox" class="delBox" name="" value="${sysRole.roleId}"></td>
                <td>${sysRole.roleId}</td>
                <td>${sysRole.roleName}</td>
                <td>${sysRole.roleDesc}</td>
                <td><fmt:formatDate value="${sysRole.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                <td class="f-14">
                    <a title="编辑" href="javascript:;" onclick="admin_role_edit('角色编辑','sysRole/toUpdate/${sysRole.roleId}')"
                       style="text-decoration:none">
                        <i class="Hui-iconfont">&#xe6df;</i></a>
                    <a title="删除" href="javascript:;" onclick="admin_role_del(this,${sysRole.roleId})" class="ml-5"
                       style="text-decoration:none">
                        <i class="Hui-iconfont">&#xe6e2;</i></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <jsp:include page="/application/common/page.jsp">
        <jsp:param name="bodyId" value="rolebody"/>
    </jsp:include>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script type="text/javascript">

    $("#flag").val(${sysRole.flag=="false"?0:1});

    /*管理员-角色-添加*/
    function admin_role_add(title, url, w, h) {
        layer_show(title, url, w, h);
    }

    /*管理员-角色-编辑*/
    function admin_role_edit(title, url, id, w, h) {
        layer_show(title, url, w, h);
    }

    /*管理员-角色-删除*/
    function admin_role_del(obj, id) {
        layer.confirm('角色删除须谨慎，确认要删除吗？', function (index) {
            //此处请求后台程序，下方是成功后的前台处理……
            $.ajax({
                url: "sysRole/delete",
                type: "POST",
                // data: "roleId=" + id,
                data: {"roleId": id},
                success: function (data) {
                    if (data.result) {
                        $(obj).parents("tr").remove();
                        layer.msg(data.data, {icon: 1, time: 0.001}, function () {
                            location.reload();
                        });
                    } else {
                        layer.msg(data.data, {icon: 2, time: 1000});
                    }
                }
            })
        });
    }


    function batchDelete() {
        var obj = $(".delBox:checked");
        if (obj == null) {
            layer.msg("请选中要删除的数据！", {icon: 2, time: 2000});
            return;
        }
        var ids = [];
        for (var i = 0; i < obj.length; i++) {
            ids.push(obj[i].value);
        }
        $.ajax({
            url: "sysRole/batchDelete",
            type: "POST",
            data: "ids=" + ids,
            success: function (data) {
                if (data.result) {
                    layer.msg(data.data, {icon: 1, time: 0.001}, function () {
                        location.reload();
                    });
                } else {
                    layer.msg(data.data, {icon: 2, time: 1000});
                }
            }
        })
    }

    function searchWithConditions() {
        $("#rolebody").load("sysRole/searchWithConditions", {
            "roleName": $("#roleName").val(),
            "flag": $("#flag").val()
        });
    }
</script>
</body>
</html>
