<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<style type="text/css">
	.layui-form-label {width: auto;}
	span#total{float: right;padding:0px 5px;line-height: 18px;margin-top: 10px;}
	.layui-this{background: #1184c3 !important;}
</style>
<div class="panel admin-panel" >
	<div class="panel-head">
		<strong><span class="icon-pencil-square-o"></span>编辑用户</strong>
	</div>
	<div class="panel-body" id="edit_ctrl">
		<form class="layui-form" action="" >
			<div class="layui-form-item">
				<label class="layui-form-label">登录名</label>
				<div class="layui-input-block">
					<input type="text" name="loginName" readonly="readonly" disabled="disabled" v-model="user.loginName" lay-verify="loginName|require" autocomplete="off" placeholder="请输入登录名" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">真实姓名</label>
				<div class="layui-input-block">
					<input type="text" name="realName" v-model="user.realName" lay-verify="realName|require" autocomplete="off" placeholder="请输入真实姓名" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">手机号</label>
				<div class="layui-input-block">
					<input type="text" name="phone" v-model="user.phone" lay-verify="phone|require" autocomplete="off" placeholder="请输入手机号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">邮箱</label>
				<div class="layui-input-block">
					<input type="text" name="email" v-model="user.email" lay-verify="email|require" autocomplete="off" placeholder="请输入邮箱" class="layui-input">
				</div>
			</div>
			<!--<div class="layui-form-item">
				<label class="layui-form-label">启用状态</label>
				<div class="layui-input-block">
					<input type="radio" name="statu" v-model="user.enableStatus" value="1" title="启用" checked="">
   				   	<input type="radio" name="statu" v-model="user.enableStatus" value="0" title="禁用">
				</div>
			</div>-->
			<div class="layui-form-item" >
    			<label class="layui-form-label">选择角色</label>
    			<div class="layui-input-block">
      				<select name="roles" lay-filter="role" v-model="user.roles[0].id">
				        <option value=""></option>
				        <option v-for='role in roles' :value="role.id" :code="role.roleCode">{{role.roleName}}</option>
      				</select>
    			</div>
  			</div>
			<div class="layui-form-item">
    			<div class="layui-input-block">
      				<button class="layui-btn" lay-submit="" lay-filter="edit">确认修改</button>
      				<a class="layui-btn layui-btn-primary" href="javascript:loader.load('#admin_container','jump/user')">返回</a>
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
			if((edit_ctrl.role instanceof Array)&&edit_ctrl.role.length){
				data.roles = edit_ctrl.role;
			}else{
				data.roles = [{id:data.roles}];
			}
			data.id = edit_ctrl.user.id;
			layer.load();
			fun.post(loader.baseurl+'/user/update',data,function(res){
				layer.closeAll('loading');
				if(typeof(res)=='object'){
					if(res.code==1){
						layer.alert('编辑用户成功！',{icon:1},function(i){
							$(obj.elem).next().get(0).click();
							layer.close(i);
						});
					}else{
						layer.alert('编辑用户失败！',{icon:2},function(i){
							layer.close(i);
						});
					}
				}
			})
			return false;
		});
		
		form.on('select(role)',function(obj){
			var id = obj.value;
			var _option = $(obj.elem).find('option:selected');
			edit_ctrl.role = [{id:id,roleName:_option.text(),roleCode:_option.attr('code')}]
		});
		
		form.verify({
			role:function(val,el){
				if($.trim(val)==null||$.trim(val)==''||$.trim(val)==undefined)
				return '请选择角色';
			}
		});
		
		edit_ctrl = new Vue({
			el:"#edit_ctrl",
			data:{
				roles:{},
				role:'',
				user:loader.temp_data
			},
			methods:{
				
			},
			created:function(){
				var that = this;
				fun.get(loader.baseurl+'/role/enableRoles',{userToken:loader.getCookie('token')},function(rs){
					if(rs) that.roles = rs.data;
				});	
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
</html>