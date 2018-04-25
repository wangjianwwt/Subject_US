<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<style type="text/css">
	.layui-form-label {width: auto;}
	span#total{float: right;padding:0px 5px;line-height: 18px;margin-top: 10px;}
	.layui-this{background: #1184c3 !important;}
</style>
<div class="panel admin-panel" >
	<div class="panel-head">
		<strong><span class="icon-pencil-square-o"></span>新增用户</strong>
	</div>
	<div class="panel-body" id="add_ctrl">
		<form class="layui-form" action="" >
			<div class="layui-form-item">
				<label class="layui-form-label">登录名</label>
				<div class="layui-input-block">
					<input type="text" name="loginName" lay-verify="loginName|require" autocomplete="off" placeholder="请输入登录名" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">真实姓名</label>
				<div class="layui-input-block">
					<input type="text" name="realName" lay-verify="realName|require" autocomplete="off" placeholder="请输入真实姓名" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">手机号</label>
				<div class="layui-input-block">
					<input type="text" name="phone" lay-verify="phone|require" autocomplete="off" placeholder="请输入手机号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">邮箱</label>
				<div class="layui-input-block">
					<input type="text" name="email" lay-verify="email|require" autocomplete="off" placeholder="请输入邮箱" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">启用状态</label>
				<div class="layui-input-block">
					<input type="radio" name="statu" value="1" title="启用" checked="">
   				   	<input type="radio" name="statu" value="0" title="禁用">
				</div>
			</div>
			<div class="layui-form-item" >
    			<label class="layui-form-label">选择角色</label>
    			<div class="layui-input-block">
      				<select name="roles" lay-filter="role">
				        <option value=""></option>
				        <option v-for='role in roles' :value="role.id" :code="role.roleCode">{{role.roleName}}</option>
      				</select>
    			</div>
  			</div>
			<div class="layui-form-item">
    			<div class="layui-input-block">
      				<button class="layui-btn" lay-submit="" lay-filter="add">确认提交</button>
      				<a class="layui-btn layui-btn-primary" href="javascript:loader.load('#admin_container','ability_user_list.html')">返回</a>
    			</div>
  			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	layui.use(['form','laydate'], function() {
		var form = layui.form,laydate = layui.laydate;
		
		form.on('submit(add)',function(obj){
			var data = obj.field;
			data.userToken = loader.getCookie('token');
			data.roles = add_ctrl.role;
			layer.load();
			fun.post(loader.baseurl+'/user/add',data,function(res){
				layer.closeAll('loading');
				if(typeof(res)=='object'){
					if(res.code==1){
						layer.alert('新增用户成功！',{icon:1},function(i){
							$(obj.elem).next().get(0).click();
							layer.close(i);
						});
					}else{
						layer.alert('新增用户失败！',{icon:2},function(i){
							obj.form.reset();
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
			add_ctrl.role = [{id:id,roleName:_option.text(),roleCode:_option.attr('code')}]
		});
		
		form.verify({
			role:function(val,el){
				if($.trim(val)==null||$.trim(val)==''||$.trim(val)==undefined)
				return '请选择角色';
			}
		});
		
		add_ctrl = new Vue({
			el:"#add_ctrl",
			data:{
				roles:{},
				role:[],
			},
			methods:{
				
			},
			created:function(){
				var that = this;
				fun.get(loader.baseurl+'/role/list',{userToken:loader.getCookie('token')},function(rs){
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