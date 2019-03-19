<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/19
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="lib/layui/css/layui.css" media="all">
    <script src="lib/layui/layui.js"></script>
    <%--<script src="lib/jquery/1.9.1/jquery.min.js"></script>--%>
    <script src="lib/jquery/1.9.1/jquery.js"></script>
    <%--http://libs.baidu.com/jquery/2.0.0/jquery.min.js--%>
</head>
<body>
<div id="app"></div>
<script>
    layui.use('laypage', function () {
        var laypage = layui.laypage;

        //执行一个laypage实例
        laypage.render({
            elem: 'app', //注意，这里的 test1 是 ID，不用加 # 号
            count: ${pageInfo.total}, //数据总数，从服务端得到
            limit: ${pageInfo.pageSize},
            limits: [4, 6, 8, 10],
            curr: ${pageInfo.pageNum},
            groups: 5,
            layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
            jump: function (obj, first) {
                if (!first) {
                    location.href = "sysOrg/page/" + obj.curr + "?pageSize=" + obj.limit;
                    // alert("test")
                    // $.ajax({
                    //     url: "sysOrg/p",
                    //     type: "POST",
                    //     data: {"pageNum": obj.curr, "pageSize": obj.pageSize},
                    //     success: function (result) {
                    //         // console.log(result)
                    //     },
                    //     error: function () {
                    //         alert("error");
                    //     }
                    // });
                }
            }
        });
    });
</script>
</body>
</html>
