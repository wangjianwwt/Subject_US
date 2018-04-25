<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>pos数据新增</title>
</head>
<body>
<style type="text/css">
	.layui-form-label {width: auto;}
	span#total{float: right;padding:0px 5px;line-height: 18px;margin-top: 10px;}
	.layui-this{background: #1184c3 !important;}
	.layui-input-block {
	    margin-left: 150px;
	    min-height: 36px;
	}
	.layui-input{width: 400px;}
</style>
<div class="panel admin-panel">
	<div class="panel-head">
		<strong><span class="icon-pencil-square-o"></span>新增pos数据</strong>
	</div>
	<div class="panel-body" id="add_ctrl">
		<form class="layui-form" action="" style="margin-top: 10px;">
			<div class="layui-form-item">
				<label class="layui-form-label">终端编号</label>
				<div class="layui-input-block">
					<input type="text" name="terminalNumber" lay-verify="" autocomplete="off" placeholder="请输入终端编号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">交易时间</label>
				<div class="layui-input-block">
					<input type="text" name="transactionTime" lay-verify="" autocomplete="off" placeholder="请输入交易时间" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">银行卡号</label>
				<div class="layui-input-block">
					<input type="text" name="bankNumber" lay-verify="" autocomplete="off" placeholder="请输入银行卡号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">交易金额</label>
				<div class="layui-input-block">
					<input type="text" name="transationPrice" lay-verify="number" autocomplete="off" placeholder="请输入交易金额" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">红利收入</label>
				<div class="layui-input-block">
					<input type="text" name="dividendIncome" lay-verify="number" autocomplete="off" placeholder="请输入红利收入" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">红利支出</label>
				<div class="layui-input-block">
					<input type="text" name="dividendExpenditure" lay-verify="number" autocomplete="off" placeholder="请输入红利支出" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">小费</label>
				<div class="layui-input-block">
					<input type="text" name="dumbo" lay-verify="number" autocomplete="off" placeholder="请输入小费" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">手续费</label>
				<div class="layui-input-block">
					<input type="text" name="serviceCharge" lay-verify="number" autocomplete="off" placeholder="请输入手续费" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">分期付款手续费</label>
				<div class="layui-input-block">
					<input type="text" name="installmentFee" lay-verify="number" autocomplete="off" placeholder="请输入分期付款手续费" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">净收金额</label>
				<div class="layui-input-block">
					<input type="text" name="netProceeds" lay-verify="number" autocomplete="off" placeholder="请输入净收金额" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">交易检索号</label>
				<div class="layui-input-block">
					<input type="text" name="transactionSearcheNumber" lay-verify="" autocomplete="off" placeholder="请输入交易检索号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">交易类型</label>
				<div class="layui-input-block">
					<input type="text" name="transactionType" lay-verify="" autocomplete="off" placeholder="请输入交易类型" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">交易日期</label>
				<div class="layui-input-block">
					<input type="text" name="transactionDate" lay-verify="" autocomplete="off" placeholder="请输入交易日期" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">款项性质</label>
				<div class="layui-input-block">
					<input type="text" name="moneyNature" lay-verify="" autocomplete="off" placeholder="请输入款项性质" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收款账号</label>
				<div class="layui-input-block">
					<input type="text" name="receivablesNumber" lay-verify="" autocomplete="off" placeholder="请输入收款账号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">案号</label>
				<div class="layui-input-block">
					<input type="text" name="caseNumber" lay-verify="" autocomplete="off" placeholder="请输入案号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">持卡人</label>
				<div class="layui-input-block">
					<input type="text" name="chikar" lay-verify="" autocomplete="off" placeholder="请输入持卡人" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">科目名称</label>
				<div class="layui-input-block">
					<input type="text" name="subjectName" lay-verify="" autocomplete="off" placeholder="请输入科目名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">部门名称</label>
				<div class="layui-input-block">
					<input type="text" name="departmentName" lay-verify="" autocomplete="off" placeholder="请输入部门名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">承办人名</label>
				<div class="layui-input-block">
					<input type="text" name="undertaker" lay-verify="" autocomplete="off" placeholder="请输入承办人名" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">摘要</label>
				<div class="layui-input-block">
					<input type="text" name="abstractInfo" lay-verify="" autocomplete="off" placeholder="请输入摘要" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
    			<div class="layui-input-block">
      				<button class="layui-btn" lay-submit="" lay-filter="addpos">确认提交</button>
      				<a class="layui-btn layui-btn-primary" href="javascript:loader.load('#admin_container','jump/pospay')">返回</a>
    			</div>
  			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
layui.use(['form','laydate'], function() {
	var form = layui.form,laydate = layui.laydate;
	
	form.on('submit(addpos)',function(obj){
		var data = obj.field;
		data.userToken = loader.getCookie('token');
		
		layer.load();
		fun.post(loader.baseurl+'/posPay/add',data,function(res){
			layer.closeAll('loading');
			if(typeof(res)=='object'){
				if(res.code==1){
					layer.alert('新增pos数据成功！',{icon:1},function(i){
						$(obj.elem).next().get(0).click();
						layer.close(i);
					});
				}else{
					layer.alert('新增pos数据失败！',{icon:2},function(i){
						obj.form.reset();
						layer.close(i);
					});
				}
			}
		})
		return false;
	});
	

	
	add_ctrl = new Vue({
		el:"#add_ctrl",
		data:{
		},
		methods:{
			
		},
		created:function(){
		},
		updated:function(){
			form.render('select');
		}
	});
	
	form.render();
	
	laydate.render({elem:'#time'});
	laydate.render({elem:'#make_date'});

});
</script>
</body>
</html>