<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>个案修改</title>
</head>
<body>
<style type="text/css">
	.layui-form-label {width: auto;}
	span#total{float: right;padding:0px 5px;line-height: 18px;margin-top: 10px;}
	.layui-this{background: #1184c3 !important;}
</style>
<div class="panel admin-panel">
	<div class="panel-head">
		<strong><span class="icon-pencil-square-o"></span>编辑支付单</strong>
	</div>
	<div class="panel-body" id="edit_ctrl">
		<form class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">案号</label>
				<div class="layui-input-block">
					<input type="text" name="caseNumber" v-model="orderdata.caseNumber" lay-verify="" autocomplete="off" placeholder="请输入案号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">币种</label>
				<div class="layui-input-block">
					<input type="text" name="currency" v-model="orderdata.currency" lay-verify="" value="RMB" autocomplete="off" placeholder="请输入币种" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">日期</label>
				<div class="layui-input-block">
					<input type="text" name="date" v-model="orderdata.date" lay-verify="" autocomplete="off" placeholder="请输入日期" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">明细标志</label>
				<div class="layui-input-block">
					<input type="text" name="detailTitle" v-model="orderdata.detailTitle" lay-verify="" autocomplete="off" placeholder="请输入明细标志" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">付款账号开户行</label>
				<div class="layui-input-block">
					<input type="text" name="paymentDepositBank" v-model="orderdata.paymentDepositBank" lay-verify="" autocomplete="off" placeholder="请输入付款账号开户行" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" >
    			<label class="layui-form-label">付款账号/卡号</label>
    			<div class="layui-input-block">
      				<div class="layui-input-block">
					<input type="text" name="paymentNumber" v-model="orderdata.paymentNumber" lay-verify="" autocomplete="off" placeholder="请输入付款账号/卡号" class="layui-input">
				</div>
    			</div>
  			</div>
  			<div class="layui-form-item">
				<label class="layui-form-label">付款账号名称/卡名称</label>
				<div class="layui-input-block">
					<input type="text" name="paymentName" v-model="orderdata.paymentName" lay-verify="" autocomplete="off" placeholder="请输入付款账号名称/卡名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收款账号开户行</label>
				<div class="layui-input-block">
					<input type="text" name="receivablesDepositBank" v-model="orderdata.receivablesDepositBank" lay-verify="" autocomplete="off" placeholder="请输入收款账号开户行" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收款账号省份</label>
				<div class="layui-input-block">
					<input type="text" name="receivablesProvince" v-model="orderdata.receivablesProvince" lay-verify="" autocomplete="off" placeholder="请输入收款账号省份" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收款账号地市</label>
				<div class="layui-input-block">
					<input type="text" name="receivablesCity" v-model="orderdata.receivablesCity" lay-verify="" autocomplete="off" placeholder="请输入收款账号地市" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收款账号地区码</label>
				<div class="layui-input-block">
					<input type="text" name="receivablesAreaCode" v-model="orderdata.receivablesAreaCode" lay-verify="" autocomplete="off" placeholder="请输入收款账号地区码" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收款账号</label>
				<div class="layui-input-block">
					<input type="text" name="receivablesNumber" v-model="orderdata.receivablesNumber" lay-verify="" autocomplete="off" placeholder="请输入收款账号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收款账号名称</label>
				<div class="layui-input-block">
					<input type="text" name="receivablesAccountName" v-model="orderdata.receivablesAccountName" lay-verify="" autocomplete="off" placeholder="请输入收款账号名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">金额</label>
				<div class="layui-input-block">
					<input type="text" name="amount" v-model="orderdata.amount" lay-verify="number" autocomplete="off" placeholder="请输入金额" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">汇款用途</label>
				<div class="layui-input-block">
					<input type="text" name="remittancePurpose" v-model="orderdata.remittancePurpose" lay-verify="" autocomplete="off" placeholder="请输入汇款用途" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">备注信息</label>
				<div class="layui-input-block">
					<input type="text" name="remarks" v-model="orderdata.remarks" lay-verify="" autocomplete="off" placeholder="请输入备注信息" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收款账户短信通知手机号码</label>
				<div class="layui-input-block">
					<input type="text" name="receivablesPhone" v-model="orderdata.receivablesPhone" lay-verify="phone|require" autocomplete="off" placeholder="请输入收款账户短信通知手机号码" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
    			<div class="layui-input-block">
      				<button class="layui-btn" lay-submit="" lay-filter="edit">确认提交</button>
      				<a class="layui-btn layui-btn-primary" href="javascript:loader.load('#admin_container','jump/courtpayorder')">返回</a>
    			</div>
  			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
layui.use(['form','laydate'], function() {
	var form = layui.form,laydate = layui.laydate;
	
	form.on('submit(edit)',function(obj){
		var data = obj.field;
		data.userToken = loader.getCookie('token');
		data.id = edit_ctrl.orderdata.id;
		layer.load();
		fun.post(loader.baseurl+'/courtPayOrder/edit',data,function(res){
			layer.closeAll('loading');
			if(typeof(res)=='object'){
				if(res.code==1){
					layer.alert('编辑支付单成功！',{icon:1},function(i){
						$(obj.elem).next().get(0).click();
						layer.close(i);
					});
				}else{
					layer.alert('编辑支付单失败！',{icon:2},function(i){
						obj.form.reset();
						layer.close(i);
					});
				}
			}
		})
		return false;
	});
	
	form.on('select(processType)',function(obj){
		var id = obj.value;
		var _option = $(obj.elem).find('option:selected');
		edit_ctrl.processTypeName = _option.text();
	});
	
	form.verify({
		role:function(val,el){
			if($.trim(val)==null||$.trim(val)==''||$.trim(val)==undefined)
			return '请选择';
		}
	});
	
	edit_ctrl = new Vue({
		el:"#edit_ctrl",
		data:{
			orderdata:loader.temp_data
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