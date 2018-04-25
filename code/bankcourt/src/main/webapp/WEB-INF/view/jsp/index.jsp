<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />-->
		<meta name="renderer" content="webkit">
		<title>银法对接系统</title>
		<link rel="stylesheet" href="<%=path %>/resources/layui/css/layui.css"/>
		<link rel="stylesheet" href="<%=path %>/resources/css/pintuer.css"/>
		<link rel="stylesheet" href="<%=path %>/resources/css/common.css"/>
		<script type="text/javascript" src="<%=path %>/resources/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path %>/resources/layui/layui.js"></script>
		<script type="text/javascript" src="<%=path %>/resources/js/common.js"></script>
		<script type="text/javascript" src="<%=path %>/resources/js/function.js"></script>
		<script type="text/javascript" src="<%=path %>/resources/vuejs/vue.min.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path %>/resources/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="<%=path %>/resources/easyui/themes/bootstrap/easyui.css">
		<script type="text/javascript" src="<%=path %>/resources/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=path %>/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
	</head>
	<body style="background-color:#f2f9fd;">
		<div class="header bg-main" id="index">
			<div class="logo margin-big-left fadein-top">
				<h1><img src="<%=path %>/resources/images/logo.png" height="50" alt="" />银法对接系统</h1>
			</div>
			<div class="float-right layui-nav margin-big-right fadein-top">
				<div class="logo">
					<img src="<%=path %>/resources/images/y.jpg" class="radius-circle rotate-hover" height="40" alt="" />
				</div>
  				<div class="layui-nav-item" lay-unselect="">
					<a href="javascript:;">你好,<span class="nickname">{{uname}}<span></a>
					<dl class="layui-nav-child">
				      	<dd><a href="javascript:;">个人资料</a></dd>
				      	<dd><a href="javascript:;">修改密码</a></dd>
				      	<dd><a href="javascript:;" @click="logout">退出登录</a></dd>
					</dl>
  				</div>
			</div>
		</div>
		<div class="leftnav">
			<div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
			<h2><span class="icon-gear"></span>数据维护</h2>
			<ul>
				<li><a href="javascript:loader.load('#admin_container','jump/courtpayorder')" target="right"><span class="icon-caret-right"></span>法院收支记录</a></li>
				<li><a href="javascript:loader.load('#admin_container','jump/pospay')" target="right"><span class="icon-caret-right"></span>pos数据维护</a></li>
				<li><a href="javascript:loader.load('#admin_container','jump/interpay')" target="right"><span class="icon-caret-right"></span>网银数据维护</a></li>
			</ul>
			<h2><span class="icon-gavel"></span>个案管理</h2>
			<ul>
				<li><a href="javascript:loader.load('#admin_container','jump/case')" target="right"><span class="icon-caret-right"></span>个案列表</a></li>
			</ul>
			<h2><span class="icon-user"></span>权限管理</h2>
			<ul>
				<li><a href="javascript:loader.load('#admin_container','jump/user')" target="right"><span class="icon-caret-right"></span>用户管理</a></li>
				<li><a href="javascript:loader.load('#admin_container','jump/role')" target="right"><span class="icon-caret-right"></span>角色管理</a></li>
				<li><a href="javascript:loader.load('#admin_container','jump/permission')" target="right"><span class="icon-caret-right"></span>资源管理</a></li>
			</ul>
			<h2><span class="icon-usd"></span>财务台账</h2>
			<ul>
				<li><a href="javascript:loader.load('#admin_container','jump/finance_subject_list')" target="right"><span class="icon-caret-right"></span>科目明细统计</a></li>
				<li><a href="javascript:loader.load('#admin_container','jump/finance_month_list')" target="right"><span class="icon-caret-right"></span>月收入统计</a></li>
				<li><a href="javascript:loader.load('#admin_container','jump/finance_property_list')" target="right"><span class="icon-caret-right"></span>资产负债情况</a></li>
			</ul>
			<h2><span class="icon-pencil-square-o"></span>业务分析</h2>
			<ul>
				<li><a href="javascript:loader.load('#admin_container','jump/business_finance_list')" target="right"><span class="icon-caret-right"></span>财务分析</a></li>
			</ul>
		</div>
		<ul class="bread">
			<li><a href="<%=path %>/index" target="right" class="icon-home">首页</a></li>
			<li><a href="javascript:void(0)" id="a_leader_txt">银法对接系统</a></li>
		</ul>
		<div id="admin_container"></div>
		<script type="text/javascript">
			var user_ctrl = new Vue({
				el:"#index",
				data:{
					uname:'',
				},
				methods:{
					logout:function(){
						var param = {userToken:loader.getCookie('token')};
						fun.post(loader.baseurl+'/layout',param,function(res){
							if(res.code!=1){
								layer.msg(res.msg);
							}else{
								var wait = function(deferred){
									loader.clearCookie();
									deferred.resolve();
								}
								jQuery.Deferred(wait).done(function(){
									location.href = 'login';
								});
							}
						});
					}
				},
				created:function(){
					
				},
				mounted:function(){
					
				}
			});
			
			document.onreadystatechange = function(){
				if(document.readyState == 'complete'){
					loader.init();
					user_ctrl.uname = loader.getCookie('uname');
				}
			}
		</script>
	</body>
</html>
