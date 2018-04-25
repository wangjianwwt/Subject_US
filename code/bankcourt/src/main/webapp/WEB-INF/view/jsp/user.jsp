<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<html>
<style type="text/css">
	.layui-form-label {width: auto;}
	span#total{float: right;padding:0px 5px;line-height: 18px;margin-top: 10px;}
</style>
<script type="text/html" id="num_tpl">
  {{loader.send_data++}}
</script>
<div class="panel admin-panel">
	<div class="panel-head">
		<strong><span class="icon-pencil-square-o"></span>用户管理</strong>
	</div>
	<div class="panel-body">
		<form class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">用户名</label>
				<div class="layui-input-inline">
					<input type="text" name="loginName" autocomplete="off" placeholder="用户名" class="layui-input">
				</div>
				<!--<label class="layui-form-label">创建时间</label>
				<div class="layui-input-inline">
					<input type="text" name="startDte" class="layui-input" id="start" placeholder="开始时间">
				</div>
				<label class="layui-form-label">-</label>
				<div class="layui-input-inline">
					<input type="text" name="end" class="layui-input" id="end" placeholder="结束时间">
				</div>-->
				<label class="layui-form-label">启用状态</label>
				<div class="layui-input-inline">
					<input type="radio" name="enableStatus" value="1" title="启用" checked="">
   				   	<input type="radio" name="enableStatus" value="0" title="禁用">
				</div>
				<div class="layui-input-inline">
					<button type="submit" lay-submit="" class="button bg-mix" lay-filter="query">提交查询</button>
					<a class="button bg-main" href="javascript:loader.load('#admin_container','jump/user')">刷新</a>
				</div>
			</div>
		</form>
	</div>
	<div class="panel-head">
		<a class="button bg-main" href="javascript:loader.load('#admin_container','jump/user/add')">新增用户</a>
	</div>
</div>
<table class="layui-hide" id="list" lay-filter="user"></table>
<script type="text/html" id='handle'>
  	<button class="border-blue button button-little" lay-event="edit">编辑</button>
  	<button class="border-red button button-little" lay-event="del">删除</button>
  	<button class="border-green button button-little" lay-event="enable">启用</button>
  	<button class="border-red button button-little" lay-event="disabled">禁用</button>
</script>
<script type="text/javascript">
	layui.use(['form', 'table', 'laydate'], function() {
		var form = layui.form,
			table = layui.table,
			laydate = layui.laydate;
		form.render();
		loader.send_data = 1;
		
		var config = {
			elem:'#list',
			where:{userToken:loader.getCookie('token')},
			url:loader.baseurl+'/user/list',
			page: true,
			limit:10,
			cols:[[
				{templet:'#num_tpl', width:80,title:'序号'},
				{field:'loginName', width:250,title:'登录名'},
				{field:'realName', width:160, sort:true,title:'用户名'},
				{field:'phone',width:200,title:'联系方式'},
				{field:'rolesStr',width:100,title:'角色名称'},
				{field:'enableStatusStr',width:100,title:'启用状态'},
				{width:260,title:'操作',toolbar:'#handle'}
			]],
			response:{
				statusCode:1,
			},
			done:function(){
				
			}
		};

		var user_list = table.render(config);

		form.on('submit(query)',function(obj){
			obj.field.userToken = loader.getCookie('token');
			config.where = obj.field;
			user_list.reload(config);
			return false;
		});

		laydate.render({elem: '#start'});
		laydate.render({elem: '#end'});
		
//		var param = {userToken:loader.getCookie('token')};
//		fun.get(loader.baseurl+'/user/list',param,function(rs){
//			console.log(rs);
//		});

		table.on('tool(user)',function(obj){
			if(obj.event === 'del'){  //删除	
				layer.load();
				fun.post(loader.baseurl+'/user/del',{id:obj.data.id},function(res){
					done(res,'删除成功','删除失败');
				});
			}
			if(obj.event === 'edit'){  //编辑
				loader.temp_data = obj.data;
				loader.load('#admin_container','jump/user/edit');
			}
			if(obj.event === 'enable'){  //启用
				if(obj.data.enableStatus) return;
				layer.load();
				fun.post(loader.baseurl+'/user/enable',{id:obj.data.id},function(res){
					done(res,'启用成功','启用失败');
				});
			}
			if(obj.event === 'disabled'){ //禁用
				if(!obj.data.enableStatus) return;
				layer.load();
				fun.post(loader.baseurl+'/user/disable',{id:obj.data.id},function(res){
					done(res,'禁用成功','禁用失败');
				});
			}
		})
		
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
				if(res.code==1) loader.load('#admin_container','jump/user');  //刷新当前页面
			});
		}
		
	});
</script>
</html>