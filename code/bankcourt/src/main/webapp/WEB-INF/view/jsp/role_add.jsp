<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>角色新增</title>
</head>
<style type="text/css">
	.layui-form-label {width: auto;}
	span#total{float: right;padding:0px 5px;line-height: 18px;margin-top: 10px;}
	.layui-this{background: #1184c3 !important;}
</style>
<div class="panel admin-panel">
	<div class="panel-head">
		<strong><span class="icon-pencil-square-o"></span>新增用户</strong>
	</div>
	<div class="panel-body">
		<form class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">角色名称</label>
				<div class="layui-input-block">
					<input type="text" name="post_number" lay-verify="post_number" autocomplete="off" placeholder="请输入角色名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">角色编码</label>
				<div class="layui-input-block">
					<input type="text" name="number" lay-verify="number" autocomplete="off" placeholder="请输入角色编码" class="layui-input">
				</div>
			</div>
			<!-- 		<div class="layui-form-item">
    			<label class="layui-form-label">启用状态</label>
    			<div class="layui-input-block">
      				<input type="radio" name="active" value="1" title="启用" checked="">
    	  			<input type="radio" name="active" value="0" title="禁用">
    			</div>
  			</div> -->
			<div class="layui-form-item">
    			<div class="layui-input-block">
      				<button class="layui-btn" lay-submit="" lay-filter="demo">确认提交</button>
      				<a class="layui-btn layui-btn-primary" href="javascript:loader.load('#admin_container','ability_role_list.html')">返回</a>
    			</div>
  			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	layui.use(['form','laydate'], function() {
		var form = layui.form,laydate = layui.laydate;
		
		form.render();

	});
</script>
</html>