<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>个案新增</title>
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
		<strong><span class="icon-pencil-square-o"></span>新增个案</strong>
	</div>
	<div class="panel-body" id="add_ctrl" style="height: 600px;">
		<form class="layui-form" action="">
			<div class="layui-form-item" style="margin-top: 10px;">
				<label class="layui-form-label">案件编号</label>
				<div class="layui-input-block">
					<input type="text" name="caseNumber" lay-verify="" autocomplete="off" placeholder="请输入案件编号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">款项性质编码</label>
				<div class="layui-input-block">
					<input type="text" name="moneyNatureCode" lay-verify="" autocomplete="off" placeholder="请输入款项性质编码" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">款项性质名称</label>
				<div class="layui-input-block">
					<input type="text" name="moneyNatureName" lay-verify="" autocomplete="off" placeholder="请输入款项性质名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">应收金额</label>
				<div class="layui-input-block">
					<input type="text" name="receivableAmount" lay-verify="number" autocomplete="off" placeholder="请输入应收金额" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">差额</label>
				<div class="layui-input-block">
					<input type="text" name="diffAmount" lay-verify="number" autocomplete="off" placeholder="请输入差额" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">实收金额</label>
				<div class="layui-input-block">
					<input type="text" name="actualAmount" lay-verify="number" autocomplete="off" placeholder="请输入案件编号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" style="width: 500px;">
    			<label class="layui-form-label">选择处理状态</label>
    			<div class="layui-input-block">
      				<select name="processStatus">
				        <option value="1">催收</option>
				        <option value="2">正在收款</option>
				        <option value="3">移送执行</option>
				        <option value="4">坏账处理</option>
      				</select>
    			</div>
  			</div>
			<div class="layui-form-item">
    			<div class="layui-input-block">
      				<button class="layui-btn" lay-submit="" lay-filter="addcase">确认提交</button>
      				<a class="layui-btn layui-btn-primary" href="javascript:loader.load('#admin_container','jump/case')">返回</a>
    			</div>
  			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
layui.use(['form','laydate'], function() {
	var form = layui.form,laydate = layui.laydate;
	
	form.on('submit(addcase)',function(obj){
		var data = obj.field;
		data.userToken = loader.getCookie('token');
		data.processTypeName = add_ctrl.processTypeName;
		
		layer.load();
		fun.post(loader.baseurl+'/case/add',data,function(res){
			layer.closeAll('loading');
			if(typeof(res)=='object'){
				if(res.code==1){
					layer.alert('新增个案成功！',{icon:1},function(i){
						$(obj.elem).next().get(0).click();
						layer.close(i);
					});
				}else{
					layer.alert('新增个案失败！',{icon:2},function(i){
						obj.form.reset();
						layer.close(i);
					});
				}
			}
		})
		return false;
	});
	
	
	form.verify({
		role:function(val,el){
			if($.trim(val)==null||$.trim(val)==''||$.trim(val)==undefined)
			return '请选择';
		}
	});
	
	form.render();
	
	laydate.render({elem:'#time'});
	laydate.render({elem:'#make_date'});

});
</script>
</body>
</html>