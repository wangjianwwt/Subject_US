<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>pos数据修改</title>
</head>
<body>
<style type="text/css">
	.layui-form-label {width: auto;}
	span#total{float: right;padding:0px 5px;line-height: 18px;margin-top: 10px;}
	.layui-this{background: #1184c3 !important;}
	.layui-input{width: 400px;}
</style>
<div class="panel admin-panel">
	<div class="panel-head">
		<strong><span class="icon-pencil-square-o"></span>编辑网银数据</strong>
	</div>
	<div class="panel-body" id="edit_ctrl">
		<form class="layui-form" action="" style="margin-top: 10px;">
			<div class="layui-form-item">
				<label class="layui-form-label">凭证号 </label>
				<div class="layui-input-block">
					<input type="text" name="voucherNo" v-model="interdata.voucherNo" lay-verify="" autocomplete="off" placeholder="请输入凭证号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">本方账号</label>
				<div class="layui-input-block">
					<input type="text" name="localNumber" v-model="interdata.localNumber" lay-verify="" autocomplete="off" placeholder="请输入本方账号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">对方账号</label>
				<div class="layui-input-block">
					<input type="text" name="partyNumber" v-model="interdata.partyNumber" lay-verify="" autocomplete="off" placeholder="请输入对方账号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">交易时间</label>
				<div class="layui-input-block">
					<input type="text" name="transationPrice" v-model="interdata.transationPrice" lay-verify="" autocomplete="off" placeholder="请输入交易时间" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">借/贷</label>
				<div class="layui-input-block">
					<input type="text" name="borrowOrLend" v-model="interdata.borrowOrLend" lay-verify="" autocomplete="off" placeholder="请输入借/贷" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">借方发生额</label>
				<div class="layui-input-block">
					<input type="text" name="debitPrice" v-model="interdata.debitPrice" lay-verify="" autocomplete="off" placeholder="请输入借方发生额" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">贷方发生额</label>
				<div class="layui-input-block">
					<input type="text" name="creditPrice" v-model="interdata.creditPrice" lay-verify="" autocomplete="off" placeholder="请输入贷方发生额" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">对方行号</label>
				<div class="layui-input-block">
					<input type="text" name="partyLineNumber" v-model="interdata.partyLineNumber" lay-verify="" autocomplete="off" placeholder="请输入对方行号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">摘要</label>
				<div class="layui-input-block">
					<input type="text" name="abstractInfo" v-model="interdata.abstractInfo" lay-verify="" autocomplete="off" placeholder="请输入摘要" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">用途</label>
				<div class="layui-input-block">
					<input type="text" name="purpose" v-model="interdata.purpose" lay-verify="" autocomplete="off" placeholder="请输入用途" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">对方单位名称 </label>
				<div class="layui-input-block">
					<input type="text" name="corporateName" v-model="interdata.corporateName" lay-verify="" autocomplete="off" placeholder="请输入对方单位名称 " class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">余额</label>
				<div class="layui-input-block">
					<input type="text" name="balance" v-model="interdata.balance" lay-verify="" autocomplete="off" placeholder="请输入余额" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">个性化信息</label>
				<div class="layui-input-block">
					<input type="text" name="individuation" v-model="interdata.individuation" lay-verify="" autocomplete="off" placeholder="请输入个性化信息" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">案号</label>
				<div class="layui-input-block">
					<input type="text" name="caseNumber" v-model="interdata.caseNumber" lay-verify="" autocomplete="off" placeholder="请输入案号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">科目名称</label>
				<div class="layui-input-block">
					<input type="text" name="subjectName" v-model="interdata.subjectName" lay-verify="" autocomplete="off" placeholder="请输入科目名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">部门名称</label>
				<div class="layui-input-block">
					<input type="text" name="departmentName" v-model="interdata.departmentName" lay-verify="" autocomplete="off" placeholder="请输入部门名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">承办人名 </label>
				<div class="layui-input-block">
					<input type="text" name="undertaker" v-model="interdata.undertaker" lay-verify="" autocomplete="off" placeholder="请输入承办人名 " class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
    			<div class="layui-input-block">
      				<button class="layui-btn" lay-submit="" lay-filter="editinter">确认提交</button>
      				<a class="layui-btn layui-btn-primary" href="javascript:loader.load('#admin_container','jump/interpay')">返回</a>
    			</div>
  			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
layui.use(['form','laydate'], function() {
	var form = layui.form,laydate = layui.laydate;
	
	form.on('submit(editinter)',function(obj){
		var data = obj.field;
		data.userToken = loader.getCookie('token');
		data.id = edit_ctrl.interdata.id;
		layer.load();
		fun.post(loader.baseurl+'/interPay/edit',data,function(res){
			layer.closeAll('loading');
			if(typeof(res)=='object'){
				if(res.code==1){
					layer.alert('编辑pos数据成功！',{icon:1},function(i){
						$(obj.elem).next().get(0).click();
						layer.close(i);
					});
				}else{
					layer.alert('编辑pos数据失败！',{icon:2},function(i){
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
			interdata:loader.temp_data
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