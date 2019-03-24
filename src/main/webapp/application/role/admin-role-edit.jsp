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
    <link href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-role-edit">
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>角色名称：</label>
            <div class="formControls col-5">
                <input type="hidden" name="roleId" value="${sysRole.roleId}"/>
                <input type="text" class="input-text" value="${sysRole.roleName}" placeholder="" id="roleName"
                       name="roleName"
                       datatype="*2-16" nullmsg="角色名不能为空">
            </div>
            <div class="col-4"></div>
        </div>
        <div>
            <label class="form-label col-3">是否有效：</label>
            <div class="formControls col-5"> <span class="select-box" style="width:150px;">
				<select class="select" name="flag" id="flag" size="1" value="${sysRole.flag==true?"1":"0"}">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
				</span></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">备注：</label>
            <div class="formControls col-5">
                <textarea name="roleDesc" id="roleDesc" cols="" rows="" class="textarea" placeholder="说点什么...100个字符以内"
                          dragonfly="true"
                          onKeyUp="textarealength(this,100)">${sysRole.roleDesc}</textarea>
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

<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script>
<script type="text/javascript" src="lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script src="lib/zTree/v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
    $(function () {
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });

        $("#form-role-edit").Validform({
            tiptype: 2,
            callback: function (form) {
                //表单验证通过后，点击提交才会进入这个方法
                $.ajax({
                    url: "sysRole/update",
                    type: "POST",
                    data: $("#form-role-edit").serialize(),
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

</script>
</body>
</html>
