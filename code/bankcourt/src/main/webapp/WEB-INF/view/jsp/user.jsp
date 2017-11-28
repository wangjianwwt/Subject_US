<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<html>
<head>
    <title>用户管理</title>
</head>
<body>
    <div>
        登录名:<input type="text" id="loginName" name="loginName"/> <br />
        真实姓名:<input type="text" id="realName" name="realName"/> <br />
        手机号码:<input type="text" id="phone" name="phone"/> <br />
        邮箱:<input type="text" id="email" name="email"/> <br />
        <button type="button">提交</button>
    </div>
</body>

<script src="<%=path %>/resources/js/base.js"> </script>
<script src="<%=path %>/resources/common/jquery.min.js"> </script>
<script>
    $(document).ready(function() {


        $("button ").click(function() {

            // 获取cookie中的值
            var userToken = getUserToken();
            if(userToken == ""){
                return;
            }

            var loginName = $('#loginName').val().trim();
            var realName = $('#realName').val().trim();
            var phone = $('#phone').val().trim();
            var email = $('#email').val().trim();
            var param = {
                loginName: loginName,
                realName: realName,
                phone: phone,
                email: email,
                userToken:userToken
            };

            var url = baseurl+"/user/add";

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
                        $('#msg').html(data.msg);
                    }
                },
                error: function (){
                    alert("请求异常!");
                }
            });

            $.post(url, param,
                function(data, status) {
                    if("success" != status){
                        alert("请求异常了,快联系程序员改BUG!");
                        return;
                    }
                    var result = data.result;
                    if(result == 1){
                        window.location.href = baseurl+"/index";
                    }else{
                        $('#password').val("");
                        $('#msg').html(data.msg);
                    }
                });
        });
    });
</script>
</html>
