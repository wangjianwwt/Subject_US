<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
</head>
<body>
<span id="msg" style="color:#ff0000;"></span> <br/> 登录名称: <input type="text" id="loginName"/> <br/> 登录密码: <input
        type="password" id="password"/> <br/>
<button type="button ">登录</button>
</body>

<script src="<%=path %>/resources/js/base.js"></script>
<script src="<%=path %>/resources/common/jquery.min.js"></script>
<script>
    $(document).ready(function () {

        $("button ").click(function () {
            var loginName = $('#loginName').val().trim();
            var password = $('#password').val().trim();
            if (loginName.length < 1 || password.length < 1) {
                $('#msg').html("登录名或密码不能为空！");
                return;
            }
            $('#msg').html("");

            var param = {
                loginName: loginName,
                password: password
            };

            var url = baseurl + "/login";


            $.ajax({
                type: "POST",  //提交方式
                url: url,//路径
                data: JSON.stringify(param),//数据，这里使用的是Json格式进行传输
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {//返回数据根据结果进行相应的处理
                    var result = data.result;
                    if (result == 1) {
                        window.location.href = baseurl + "/index";
                    } else {
                        $('#password').val("");
                        $('#msg').html(data.msg);
                    }
                },
                error: function (){
                    alert("请求异常!");
                }
            });

        });
    });
</script>
</html>
