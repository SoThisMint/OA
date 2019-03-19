<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/18
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Title</title>
</head>
<body>
<div id="container"></div>
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script>
    $.ajax({
        url: "my/page",
        type: "POST",
        success: function (result) {
            $.each(result.list, function (index, item) {
                // alert(item.orgName);
                $("<tr></tr>").append("<td></td>").text(item.orgName).appendTo($("#container"));
            })
        }
    });
</script>
</body>
