<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style type="text/css">
	.layui-form-label {width: auto;}
	span#total{float: right;padding:0px 5px;line-height: 18px;margin-top: 10px;}
</style>

<div class="panel admin-panel">
	<div class="panel-head">
		<strong><span class="icon-pencil-square-o"></span>个案管理</strong>
	</div>
	<div class="panel-body" style="overflow: unset;">
		<form class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">案号</label>
				<div class="layui-input-inline">
					<input type="text" name="caseNumber" autocomplete="off" placeholder="案号" class="layui-input">
				</div>
				<label class="layui-form-label">处理状态</label>
				<div class="layui-input-block" style="width: 200px;float: left;margin-left: 0px;margin-right: 20px;">
      				<select name="processStatus">
      					<option value="">所有状态</option>
				        <option value="1">催收</option>
				        <option value="2">正在收款</option>
				        <option value="3">移送执行</option>
				        <option value="4">坏账处理</option>
      				</select>
    			</div>
				<div class="layui-input-inline">
					<button type="submit" lay-submit="" class="button bg-mix" lay-filter="query">提交查询</button>
					<a class="button bg-main" href="javascript:loader.load('#admin_container','jump/case')">刷新</a>
				</div>
			</div>
		</form>
	</div>
	<div class="panel-head">
		<a class="button bg-main" href="javascript:loader.load('#admin_container','jump/case_add')">新增个案</a>
	</div>
</div>

<table id="list" lay-filter="case">

</table>
<script type="text/html" id='handle'>
  	<button class="border-blue button button-little" lay-event="edit">编辑</button>
  	<button class="border-red button button-little" lay-event="del">删除</button>
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
		url:loader.baseurl+'/case/list',
		page: true,
		limit:10,
		cols:[[
			{templet:'#num_tpl', width:80,title:'序号'},
			{field:'caseNumber', width:250,title:'案号'},
			{field:'moneyNatureCode', width:160, sort:true,title:'款项性质编码'},
			{field:'moneyNatureName',width:200,title:'款项性质名称'},
			{field:'receivableAmount',width:100,title:'应收金额'},
			{field:'actualAmount',width:100,title:'实收金额'},
			{field:'diffAmount',width:100,title:'差额'},
			{field:'processStatusStr',width:100,title:'处理方式'},
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
	
//	var param = {userToken:loader.getCookie('token')};
//	fun.get(loader.baseurl+'/user/list',param,function(rs){
//		console.log(rs);
//	});

	table.on('tool(case)',function(obj){
		if(obj.event === 'del'){  //删除	
			layer.load();
			fun.post(loader.baseurl+'/case/del',{id:obj.data.id},function(res){
				done(res,'删除成功','删除失败');
			});
		}
		if(obj.event === 'edit'){  //编辑
			loader.temp_data = obj.data;
			loader.load('#admin_container','jump/case/edit');
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
			if(res.code==1) loader.load('#admin_container','jump/case');  //刷新当前页面
		});
	}
	
});
</script>
</html>