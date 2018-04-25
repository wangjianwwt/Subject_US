<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>登录</title>  
    <link rel="stylesheet" href="<%=path %>/resources/css/pintuer.css">
    <link rel="stylesheet" href="<%=path %>/resources/css/common.css">
    <script src="<%=path %>/resources/js/jquery.js"></script>
    <script src="<%=path %>/resources/js/pintuer.js"></script>  
	<script type="text/javascript" src="<%=path %>/resources/js/function.js"></script>
    
</head>
<body>
<div class="login-bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">           
            </div>         
            <form action="" method="post">
	            <div class="panel loginbox">
	                <div class="text-center margin-big padding-big-top"><h1>银法对接系统</h1></div>
	                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
	                    <div class="form-group">
	                        <div class="field field-icon-right">
	                            <input type="text" class="input input-big" name="loginName" placeholder="登录账号" data-validate="required:请填写账号" />
	                            <span class="icon icon-user margin-small"></span>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <div class="field field-icon-right">
	                            <input type="password" class="input input-big" name="password" placeholder="登录密码" data-validate="required:请填写密码" />
	                            <span class="icon icon-key margin-small"></span>
	                        </div>
	                    </div>
	                </div>
	                <div style="padding:30px;"><input type="submit" name="submit" class="button button-block bg-main text-big input-big" value="登录"></div>
	            </div>
            </form>          
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=path %>/resources/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/common.js"></script>
<script type="text/javascript">
	layui.use(['layer'],function(){
		$('form').submit(function(e){
			var that = this;
			var data = {loginName:jQuery.trim($('input[name=loginName]').val()),password:jQuery.trim($('input[name=password]').val())};
			var pwd = data.password;
			layer.load();
			fun.post(loader.baseurl+'/login',data,function(res){
				layer.closeAll('loading');
				if(typeof(res)!='object') return;
				if(res.code == 1){
					var data = res.data||{},user = data.userInfo||{},role = data.serRolePermission||{};
					var wait = function(deferred){
						loader.setCookie('token',data.userToken);
						loader.setCookie('JSESSIONID',data.jsessionid);
						loader.setCookie('uid',user.userId);
						loader.setCookie('uname',user.loginName);
						loader.setCookie('role',role.roles);
						loader.setCookie('pwd',pwd);
						deferred.resolve();
					}
					$.Deferred(wait).done(function(){
						location.href = loader.baseurl+'/index';
					});
				}else{
					layer.alert(res.msg,{icon:res.result},function(i){
						layer.close(i);
						that.reset();
					});
				}
			});
			return false;
		});
	});
</script>
</body>
</html>