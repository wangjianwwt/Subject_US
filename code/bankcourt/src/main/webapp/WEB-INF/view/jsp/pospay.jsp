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
		<strong><span class="icon-pencil-square-o"></span>pos数据管理</strong>
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
					<a class="button bg-main" href="javascript:loader.load('#admin_container','jump/pospay')">刷新</a>
				</div>
			</div>
		</form>
	</div>
	<div class="panel-head">
		<a class="button bg-main" href="javascript:loader.load('#admin_container','jump/pospay/add')">新增pos数据</a>
		<button class="button bg-main" id="alert_import">导入</button>
	</div>
</div>
<table class="layui-hide" id="list" lay-filter="pospay"></table>
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
			url:loader.baseurl+'/posPay/list',
			page: true,
			limit:10,
			cols:[[
				{templet:'#num_tpl', width:80,title:'序号'},
				{field:'terminalNumber', width:250,title:'终端编号'},
				{field:'transactionTime', width:160, sort:true,title:'交易时间'},
				{field:'bankNumber',width:200,title:'银行卡号'},
				{field:'transationPrice',width:100,title:'交易金额'},
				{field:'dividendIncome',width:100,title:'红利收入'},
				{field:'dividendExpenditure',width:100,title:'红利支出 '},
				{field:'dumbo',width:100,title:'小费'},
				{field:'serviceCharge',width:100,title:'手续费'},
				{field:'installmentFee',width:100,title:'分期付款手续费 '},
				{field:'netProceeds',width:100,title:'净收金额'},
				{field:'transactionSearcheNumber',width:100,title:'交易检索号'},
				{field:'transactionType',width:100,title:'交易类型'},
				{field:'transactionDate',width:100,title:'交易日期'},
				{field:'moneyNature',width:100,title:'款项性质'},
				{field:'receivablesNumber',width:100,title:'收款账号'},
				{field:'caseNumber',width:100,title:'案号'},
				{field:'chikar',width:100,title:'持卡人'},
				{field:'subjectName',width:100,title:'科目名称'},
				{field:'departmentName',width:100,title:'部门名称'},
				{field:'undertaker',width:100,title:'承办人名 '},
				{field:'abstractInfo',width:100,title:'摘要'},
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

		table.on('tool(pospay)',function(obj){

	/* 			if(obj.event === 'del'){  //删除	
			 layer.load();
			 fun.post(loader.baseurl+'/posPay/del',{id:obj.data.id},function(res){
			 done(res,'删除成功','删除失败');
			 });
			 } */
			if (obj.event === 'edit') { //编辑
				loader.temp_data = obj.data;
				loader.load('#admin_container', 'jump/pospay/edit');
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
					loader.load('#admin_container', 'jump/pospay'); //刷新当前页面
			});
		};
		
		
		$('#alert_import').click(function(){
			layer.open({
				  type: 2, 
				  title: '导入pos数据文件',
				  content: 'http://localhost:8080/bankcourt/jump/upload/pos'
				});    
		});
		
		

		$('#_import').click(function(){
			alert("aa");
			var formData = new FormData();
			formData.append("file", $("#upload")[0].files[0]);
			formData.append("userToken", loader.getCookie('token'));
			formData.append("sys_click", "pos");

			var url = loader.baseurl+"/data/import_pos";

			$.ajax({
				type : "POST", //提交方式
				url : url,//路径
				data : formData,
				// 告诉jQuery不要去处理发送的数据
				processData : false,
				// 告诉jQuery不要去设置Content-Type请求头
				contentType : false,
				success : function(data) {//返回数据根据结果进行相应的处理
					var code = data.code;
					if (code == 1) {
					} else {
						$('#msg').html(data.msg);
					}
				},
				error : function() {
					alert("请求异常!");
				}
			});	
		});
	});
		
</script>
</html>