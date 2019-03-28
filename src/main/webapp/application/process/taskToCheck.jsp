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
<body id="taskBody">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span
        class="c-gray en">&gt;</span> 待办事项 <a class="btn btn-success radius r mr-20"
                                              style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
    <table class="table table-border table-bordered table-hover table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="7">待办事项</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" value="" name=""></th>
            <th width="40">任务id</th>
            <th width="100">任务名称</th>
            <th width="200">描述</th>
            <th width="100">创建时间</th>
            <th width="70">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${taskList}" var="task">
            <tr class="text-c">
                <td><input type="checkbox" class="delBox" name="" value="${task.id}"></td>
                <td>${task.id}</td>
                <td>${task.name}</td>
                <td>${task.description}</td>
                <td>
                    <fmt:formatDate value="${task.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td class="f-14">
                    <button id="agree${task.id}" class="btn btn-success radius wow">同意</button>
                    <button id="disagree${task.id}" class="btn btn-danger radius wow">驳回</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <jsp:include page="/application/common/page.jsp">
        <jsp:param name="bodyId" value="taskBody" />
    </jsp:include>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script type="text/javascript">
    $(function () {
        $(".wow").click(function () {
            // console.log($(this).attr("id"));
            var flag = false;
            var id = 0;
            var content = $(this).attr("id");
            if(content.startsWith("agree")){
                flag = true;
                id = content.substring(5);
                // alert(flag);
                // alert(id);
            }else{
                id = content.substring(8);
            }

            $.ajax({
                url:"process/checkTask",
                type:"POST",
                data:{
                    "flag":flag,
                    "id":id
                },
                success:function (data) {
                    location.replace(location.href);
                }
            })
        })
    });
</script>
</body>
</html>
