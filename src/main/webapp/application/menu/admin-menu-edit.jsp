<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/19
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()+"/"%>"/>
    <link href="css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/H-ui.admin.css" rel="stylesheet" type="text/css"/>
    <link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css"/>
    <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css"/>
    <link href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-menu-edit">
        <input type="hidden" name="menuId" value="${sysMenu.menuId}">
        <div class="row cl">
        <label class="form-label col-3"><span class="c-red">*</span>菜单名称：</label>
        <div class="formControls col-5">
        <input type="text" class="input-text" value="${sysMenu.menuName}" placeholder="" id="menuName" name="menuName"
        datatype="*2-16" nullmsg="菜单名不能为空">
        </div>
        <div class="col-4"></div>
        </div>
        <div class="row cl">
        <label class="form-label col-3"><span class="c-red">*</span>父菜单Id：</label>
        <div class="formControls col-5">
        <input type="text" placeholder="父菜单Id" name="menuParentId" id="menuParentId" autocomplete="off"
        value="${sysMenu.menuParentId}" class="input-text" datatype="*1-20" nullmsg="父菜单不能为空">
        <input class="btn btn-primary radius" type="button" value="选择父菜单" onclick="selectMenuParentId()"/>
        </div>
        <div class="col-4"></div>
        </div>
        <div>
        <label class="form-label col-3">是否公布：</label>
        <div class="formControls col-5"> <span class="select-box" style="width:150px;">
        <select class="select" name="isPublish" id="isPublish" size="1" value="${sysMenu.isPublish==true?1:0}">
        <option value="1">是</option>
        <option value="0">否</option>
        </select>
        </span></div>
        </div>
        <div class="row cl">
        <label class="form-label col-3">菜单介绍：</label>
        <div class="formControls col-5">
        <textarea name="menuDesc" id="menuDesc" cols="" rows="" class="textarea" placeholder="说点什么...100个字符以内"
        dragonfly="true"
        onKeyUp="textarealength(this,100)">${sysMenu.menuDesc}</textarea>
        <p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
        </div>
        <div class="col-4"></div>
        </div>
        <div class="row cl">
        <div class="col-9 col-offset-3">
        <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
        </div>
        </div>
    </form>
</div>
<!--装树的一个容器-->
<div id="menuParentTree" style="display: none">
    <div id="zTree" class="ztree"></div>
</div>

<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script>
<script type="text/javascript" src="lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script src="lib/zTree/v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="/lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    $(function () {
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });

        $("#form-menu-edit").Validform({
            tiptype: 2,
            callback: function (form) {
                //表单验证通过后，点击添加才会进入这个方法
                $.ajax({
                    url: "sysMenu/update",
                    type: "POST",
                    data: $("#form-menu-edit").serialize(),
                    success: function (data) {
                        // alert(data.data);
                        var icon;
                        if (data.result) {
                            icon = 6;
                        } else {
                            icon = 5;
                        }
                        layer.alert(data.data, {icon: icon}, function () {
                            /*关闭弹出框*/
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.$('.btn-refresh').click();
                            parent.layer.close(index);
                            parent.location.reload();
                        });
                    }
                });
                return false;
            }
        });
    });

    function selectMenuParentId() {
        //弹出一个提示框（有一个树）
        var index = layer.open({
            title: "选择父用户",
            type: 1,
            content: $("#menuParentTree"),
            area: ["500px", "300px"],
            btn: "确定"
        });
    }

    //渲染
    //通过ajax请求去查询用户的信息
    $.ajax({
        url: "sysMenu/list",
        type: "POST",
        success: function (data) {
            // console.log(data[0]);
            console.log(data);
            var zTreeObj;
            //zTree的参数配置，深入使用参考api文档（setting配置详解）
            var setting = {
                data: {
                    key: {
                        name: "menuName"
                    },
                    simpleData: {
                        enable: true,
                        idKey: "menuId",
                        pIdKey: "menuParentId"
                    }
                },
                callback: {
                    onClick: function (event, treeId, treeNode) {
                        $("#menuParentId").val(treeNode.menuId);
                        $("#menuParentName").val(treeNode.menuName);
                    }
                }
            };
            var zNodes = data;
            $(document).ready(function () {
                zTreeObj = $.fn.zTree.init($("#zTree"), setting, zNodes);
            });
        }
    })

</script>
</body>
</html>
