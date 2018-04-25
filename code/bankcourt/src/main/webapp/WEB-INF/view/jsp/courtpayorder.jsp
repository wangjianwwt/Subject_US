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
		<strong><span class="icon-pencil-square-o"></span>支付单管理</strong>
	</div>
	<div class="panel-body">
		<form class="layui-form" action="" style="margin-top: 10px;">
			<div class="layui-form-item">
				<label class="layui-form-label">案号</label>
				<div class="layui-input-inline">
					<input type="text" name="caseNumber" autocomplete="off" placeholder="案号" class="layui-input">
				</div>
				<label class="layui-form-label">创建时间</label>
				<div class="layui-input-inline">
					<input type="text" name="startDateStr" class="layui-input" id="start" placeholder="开始时间">
				</div>
				<label class="layui-form-label">-</label>
				<div class="layui-input-inline">
					<input type="text" name="endDateStr" class="layui-input" id="end" placeholder="结束时间">
				</div>
				<div class="layui-input-inline">
					<button type="submit" lay-submit="" class="button bg-mix" lay-filter="query">提交查询</button>
					<a class="button bg-main" href="javascript:loader.load('#admin_container','jump/courtpayorder')">刷新</a>
				</div>
			</div>
		</form>
	</div>
	<div class="panel-head">
		<a class="button bg-main" href="javascript:loader.load('#admin_container','jump/courtpayorder/add')">新增支付单</a>
		<a class="button bg-main" target="_blank" href="http://localhost:8080/bankcourt/courtPayOrder/export" id="export">导出</a>
	</div>
</div>
<table class="layui-hide" id="list" lay-filter="courtpayorder"></table>
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
		// 置空临时参数
		loader.temp_data = "";
		
		var config = {
			elem:'#list',
			where:{userToken:loader.getCookie('token')},
			url:loader.baseurl+'/courtPayOrder/list',
			page: true,
			limit:10,
			cols:[[
				{templet:'#num_tpl', width:80,title:'序号'},
				{field:'caseNumber', width:250,title:'案号'},
				{field:'currency', width:160, sort:true,title:'币种'},
				{field:'date',width:200,title:'日期'},
				{field:'detailTitle',width:100,title:'明细标志'},
				{field:'paymentDepositBank',width:100,title:'付款账号开户行'},
				{field:'paymentNumber',width:100,title:'付款账号/卡号'},
				{field:'paymentName',width:100,title:'付款账号名称/卡名称'},
				{field:'receivablesDepositBank',width:100,title:'收款账号开户行'},
				{field:'receivablesProvince',width:100,title:'收款账号省份'},
				{field:'receivablesCity',width:100,title:'收款账号地市'},
				{field:'receivablesAreaCode',width:100,title:'收款账号地市'},
				{field:'receivablesNumber',width:100,title:'收款账号'},
				{field:'receivablesAccountName',width:100,title:'收款账号'},
				{field:'amount',width:100,title:'金额'},
				{field:'remittancePurpose',width:100,title:'汇款用途'},
				{field:'remarks',width:100,title:'备注信息'},
				{field:'receivablesPhone',width:100,title:'收款账户短信通知手机号码'},
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
			loader.temp_data = obj.field;
			return false;
		});

		laydate.render({elem: '#start'});
		laydate.render({elem: '#end'});
		
//		var param = {userToken:loader.getCookie('token')};
//		fun.get(loader.baseurl+'/user/list',param,function(rs){
//			console.log(rs);
//		});

		table.on('tool(courtpayorder)',function(obj){
			if(obj.event === 'del'){  //删除	
				layer.load();
				fun.post(loader.baseurl+'/courtPayOrder/delete',{id:obj.data.id},function(res){
					done(res,'删除成功','删除失败');
				});
			}
			if(obj.event === 'edit'){  //编辑
				loader.temp_data = obj.data;
				loader.load('#admin_container','jump/courtpayorder/edit');
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
				if(res.code==1) loader.load('#admin_container','jump/courtpayorder');  //刷新当前页面
			});
		}
		
		$('#export').click(function(){
			
			fun.get(loader.baseurl+'/courtPayOrder/export',loader.temp_data,function(res){
				console.log(res);
			});
		});
		
		
	});
</script>
</html>