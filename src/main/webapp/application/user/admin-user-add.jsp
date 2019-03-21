<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/19
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()+"/"%>"/>
    <link href="css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/H-ui.admin.css" rel="stylesheet" type="text/css"/>
    <link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css"/>
    <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css"/>
    <link href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet" />
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-user-add">
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>用户名称：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="" placeholder="" id="userName" name="userName"
                       datatype="*2-16" nullmsg="用户名不能为空">
            </div>
            <div class="col-4"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>密码：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="" placeholder="" id="userPassword" name="userPassword"
                       datatype="*2-16" nullmsg="密码不能为空">
            </div>
            <div class="col-4"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>组织Id：</label>
            <div class="formControls col-5">
                <input type="text" placeholder="组织Id" name="orgId" id="orgId" autocomplete="off"
                       value="" class="input-text" datatype="*1-20" nullmsg="组织Id不能为空">
                <input class="btn btn-primary radius" type="button" value="选择组织Id" onclick="selectOrgId()"/>
            </div>
            <div class="col-4"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>电话：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="" placeholder="" id="phone" name="phone"
                       datatype="m" errormsg="电话格式错误" nullmsg="电话不能为空">
            </div>
            <div class="col-4"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>邮箱：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="" placeholder="" id="email" name="email"
                       datatype="e" errormsg="邮箱格式错误" nullmsg="邮箱不能为空">
            </div>
            <div class="col-4"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>生日：</label>
            <div class="formControls col-5">
                <input id="d11" class="input-text Wdate" readonly="readonly" name="birthday" type="text" onClick="WdatePicker({maxDate:'%y-%M-%d'})"/>
            </div>
            <div class="col-4"></div>
        </div>
        <div>
            <label class="form-label col-3">是否有效：</label>
            <div class="formControls col-5"> <span class="select-box" style="width:150px;">
				<select class="select" name="flag" id="flag" size="1">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
				</span></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">个人简介：</label>
            <div class="formControls col-5">
                <textarea name="introduce" id="introduce" cols="" rows="" class="textarea" placeholder="说点什么...100个字符以内"
                          dragonfly="true"
                          onKeyUp="textarealength(this,100)"></textarea>
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
<div id="orgName" style="display: none">
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

        $("#form-user-add").Validform({
            tiptype: 2,
            callback: function (form) {
                //表单验证通过后，点击添加才会进入这个方法
                $.ajax({
                    url: "sysUser/add",
                    type: "POST",
                    data: $("#form-user-add").serialize(),
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

    function selectOrgId() {
        //弹出一个提示框（有一个树）
        var index = layer.open({
            title: "选择组织Id",
            type: 1,
            content: $("#orgName"),
            area: ["500px", "300px"],
            btn: "确定"
        });
    }

    //渲染
    //通过ajax请求去查询用户的信息
    $.ajax({
        url: "sysOrg/list",
        type: "POST",
        success: function (data) {
            // console.log(data[0]);
            console.log(data);
            var zTreeObj;
            //zTree的参数配置，深入使用参考api文档（setting配置详解）
            var setting = {
                data: {
                    key: {
                        name: "orgName"
                    },
                    simpleData: {
                        enable: true,
                        idKey: "orgId",
                        pIdKey: "orgParentId"
                    }
                },
                callback: {
                    onClick: function (event, treeId, treeNode) {
                        $("#orgName").val(treeNode.orgName);
                        $("#orgId").val(treeNode.orgId);
                    }
                }
            };
            var zNodes = data;
            $(document).ready(function(){
                zTreeObj = $.fn.zTree.init($("#zTree"), setting, zNodes);
            });
        }
    })

</script>
</body>
</html>
