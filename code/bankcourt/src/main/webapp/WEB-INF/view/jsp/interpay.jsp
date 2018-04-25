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
		<strong><span class="icon-pencil-square-o"></span>inter数据管理</strong>
	</div>
	<div class="panel-body">
		<form class="layui-form" action="" style="margin-top: 10px;">
			<div class="layui-form-item">
				<label class="layui-form-label">案号</label>
				<div class="layui-input-inline">
					<input type="text" name="caseNumber" autocomplete="off" placeholder="案号" class="layui-input">
				</div>
				<label class="layui-form-label">交易时间</label>
				<div class="layui-input-inline">
					<input type="text" name="startDateStr" class="layui-input" id="start" placeholder="开始时间">
				</div>
				<label class="layui-form-label">-</label>
				<div class="layui-input-inline">
					<input type="text" name="endDateStr" class="layui-input" id="end" placeholder="结束时间">
				</div>
				<div class="layui-input-inline">
					<button type="submit" lay-submit="" class="button bg-mix" lay-filter="query">提交查询</button>
					<a class="button bg-main" href="javascript:loader.load('#admin_container','jump/interpay')">刷新</a>
				</div>
			</div>
		</form>
	</div>
	<div class="panel-head">
		<a class="button bg-main" href="javascript:loader.load('#admin_container','jump/interpay/add')">新增网银数据</a>
		<button class="button bg-main" id="alert_import">导入</button>
	</div>
</div>
<table class="layui-hide" id="list" lay-filter="interpay"></table>
<script type="text/html" id='handle'>
  <button class="border-blue button button-little" lay-event="edit">编辑</button>
  <!-- <button class="border-red button button-little" lay-event="del">删除</button> -->
</script>
<script type="text/javascript">
	layui.use(['form', 'table', 'laydate'], function() {
		var form = layui.form,
			table = layui.table,
			laydate = layui.laydate;
		form.render();
		loader.send_data = 1;
		// 置空临时参数
		loader.temp_data = "";
		
		var config = {
			elem:'#list',
			where:{userToken:loader.getCookie('token')},
			url:loader.baseurl+'/interPay/list',
			page: true,
			limit:10,
			cols:[[
				{templet:'#num_tpl', width:80,title:'序号'},
				{field:'voucherNo', width:250,title:'凭证号'},
				{field:'localNumber', width:160, sort:true,title:'本方账号'},
				{field:'partyNumber',width:200,title:'对方账号'},
				{field:'transactionTime',width:100,title:'交易时间'},
				{field:'borrowOrLend',width:100,title:'借/贷'},
				{field:'debitPrice',width:100,title:'借方发生额  '},
				{field:'creditPrice',width:100,title:'贷方发生额'},
				{field:'partyLineNumber',width:100,title:'对方行号'},
				{field:'abstractInfo',width:100,title:'摘要'},
				{field:'purpose',width:100,title:'用途'},
				{field:'corporateName',width:100,title:'对方单位名称'},
				{field:'balance',width:100,title:'余额'},
				{field:'individuation',width:100,title:'个性化信息'},
				{field:'caseNumber',width:100,title:'案号 '},
				{field:'subjectName',width:100,title:'科目名称'},
				{field:'departmentName',width:100,title:'部门名称'},
				{field:'undertaker',width:100,title:'承办人名'},
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

		table.on('tool(interpay)',function(obj){

	/* 			if(obj.event === 'del'){  //删除	
			 layer.load();
			 fun.intert(loader.baseurl+'/interPay/del',{id:obj.data.id},function(res){
			 done(res,'删除成功','删除失败');
			 });
			 } */
			if (obj.event === 'edit') { //编辑
				loader.temp_data = obj.data;
				loader.load('#admin_container', 'jump/interpay/edit');
			}
		})

		/**
		 * 请求成功的处理函数
		 * @param res 结果集
		 * @param success 成功提示
		 * @param fail 失败提示
		 * **/
		var done = function(res, success, fail) {
			layer.closeAll('loading');
			var msg = res.msg || (res.code == 1 ? success : fail);
			layer.alert(msg, {
				icon : res.code
			}, function(i) {
				layer.close(i);
				if (res.code == 1)
					loader.load('#admin_container', 'jump/interpay'); //刷新当前页面
			});
		};
		
		
		$('#alert_import').click(function(){
			layer.open({
				  type: 2, 
				  title: '导入inter数据文件',
				  content: 'http://localhost:8080/bankcourt/jump/upload_inter'
				});    
		});
	});
		
</script>
</html>