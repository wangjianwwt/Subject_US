<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>角色列表</title>
</head>
<style type="text/css">
	.layui-form-label {width: auto;}
	span#total{float: right;padding:0px 5px;line-height: 18px;margin-top: 10px;}
</style>
<div class="panel admin-panel">
	<div class="panel-head">
		<strong><span class="icon-pencil-square-o"></span>角色管理</strong>
	</div>
	<div class="panel-body">
		<form class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">角色名/编码</label>
				<div class="layui-input-inline">
					<input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="角色名/编码" class="layui-input">
				</div>
				<div class="layui-input-inline">
					<button class="button bg-mix">提交查询</button>
				</div>
			</div>
		</form>
	</div>
	<div class="panel-head">
		<a class="button bg-main" href="javascript:loader.load('#admin_container','jump/role/add')">新增角色</a>
	</div>
</div>
<table lay-filter="role" id="list" class="layui-hide"></table>
<script type="text/html" id="handle">
	<a class="border-blue button button-little" lay-event="edit">编辑</a>
	<button class="border-red button button-little" lay-event="del">删除</button>
	<button class="border-blue button button-little" lay-event="enable">启用</button>
	<button class="border-red button button-little" lay-event="del">禁用</button>
	<button class="border-green button button-little" lay-event="allot">分配权限</button>
</script>
<script type="text/javascript">
	layui.use(['form', 'table', 'laydate'], function() {
		var form = layui.form,
			table = layui.table,
			laydate = layui.laydate;
		form.render();

		table.render({
			elem:'#list',
			where:{userToken:loader.getCookie('token')},
			url:loader.baseurl+'/role/list',
			page: true,
			limit:10,
			cols:[[
				{templet:'#num_tpl', width:80,title:'序号'},
				{field:'roleName', width:250,title:'角色名称'},
				{field:'roleCode', width:160, sort:true,title:'角色编码'},
				{field:'enableStatusStr',width:200,title:'启用状态'},
				{width:500,title:'操作',toolbar:'#handle'}
			]],
			response:{
				statusCode:1,
			},
			done:function(){
				
			}
		})
		
		
//		table.on('tool(user)',function(obj){
//			if(obj.event === 'del'){  //删除	
//				
//			}
//			if(obj.event === 'edit'){  //编辑
//				loader.temp_data = obj.data;
//				loader.load('#admin_container','ability_user_edit.html');
//			}
//			if(obj.event === 'enable'){  //启用
//				if(obj.data.enableStatus) return;
//				layer.load();
//				fun.post(loader.baseurl+'/user/enable',{id:obj.data.id},function(res){
//					done(res,'启用成功','启用失败');
//				});
//			}
//			if(obj.event === 'disabled'){ //禁用
//				if(!obj.data.enableStatus) return;
//				layer.load();
//				fun.post(loader.baseurl+'/user/disable',{id:obj.data.id},function(res){
//					done(res,'禁用成功','禁用失败');
//				});
//			}
//		})

		table.on('tool(role)',function(obj){
			if(obj.event === 'del'){
				layer.load();
				fun.post(loader.baseurl+'/role/del',{id:obj.data.id},function(res){
					done(res,'删除成功','删除失败');
				});
			}
			if(obj.event === 'allot'){
				layer.open({
				  	type: 1,
				  	title:'权限分配',
				  	skin: 'layui-layer-rim', //加上边框
				  	area: ['420px', '240px'], //宽高
				  	content: '权限分配'
				});
			}
		});
		
		/**
		 * 请求成功的处理函数
		 * @param res 结果集
		 * @param success 成功提示
		 * @param fail 失败提示
		 * **/
		var done = function(res,success,fail){
			layer.closeAll('loading');
			var msg = res.msg||(res.code == 1 ? success : fail);
			layer.alert(msg,{icon:res.code},function(i){
				layer.close(i);
				if(res.code==1) loader.load('#admin_container','jump/role');  //刷新当前页面
			});
		}

		laydate.render({elem: '#start'});
		laydate.render({elem: '#end'});
		
	});
</script>
</html>