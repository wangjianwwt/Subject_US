<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<style type="text/css">
	.layui-form-label {width: auto;}
	span#total{float: right;padding:0px 5px;line-height: 18px;margin-top: 10px;}
	.layui-this{background: #1184c3 !important;}
	.layui-input-block {
	    margin-left: 180px;
	    min-height: 36px;
	}
	.layui-input{width:400px;}
</style>
<div class="panel admin-panel" >
	<div class="panel-head">
		<strong><span class="icon-pencil-square-o"></span>新增支付单</strong>
	</div>
	<div class="panel-body" id="add_ctrl">
		<form class="layui-form" action="" >
			<div class="layui-form-item">
				<label class="layui-form-label">案号</label>
				<div class="layui-input-block">
					<input type="text" name="caseNumber" lay-verify="" autocomplete="off" placeholder="请输入案号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">币种</label>
				<div class="layui-input-block">
					<input type="text" name="currency" lay-verify="" value="RMB" autocomplete="off" placeholder="请输入币种" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">日期</label>
				<div class="layui-input-block">
					<input type="text" name="date" lay-verify="" autocomplete="off" id="make_date" placeholder="请输入日期" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">明细标志</label>
				<div class="layui-input-block">
					<input type="text" name="detailTitle" lay-verify="" autocomplete="off" placeholder="请输入明细标志" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">付款账号开户行</label>
				<div class="layui-input-block">
					<input type="text" name="paymentDepositBank" lay-verify="" autocomplete="off" placeholder="请输入付款账号开户行" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" >
    			<label class="layui-form-label">付款账号/卡号</label>
      			<div class="layui-input-block">
					<input type="text" name="paymentNumber" lay-verify="" autocomplete="off" placeholder="请输入付款账号/卡号" class="layui-input">
				</div>
  			</div>
  			<div class="layui-form-item">
				<label class="layui-form-label">付款账号名称/卡名称</label>
				<div class="layui-input-block">
					<input type="text" name="paymentName" lay-verify="" autocomplete="off" placeholder="请输入付款账号名称/卡名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收款账号开户行</label>
				<div class="layui-input-block">
					<input type="text" name="receivablesDepositBank" lay-verify="" autocomplete="off" placeholder="请输入收款账号开户行" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收款账号省份</label>
				<div class="layui-input-block">
					<input type="text" name="receivablesProvince" lay-verify="" autocomplete="off" placeholder="请输入收款账号省份" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收款账号地市</label>
				<div class="layui-input-block">
					<input type="text" name="receivablesCity" lay-verify="" autocomplete="off" placeholder="请输入收款账号地市" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收款账号地区码</label>
				<div class="layui-input-block">
					<input type="text" name="receivablesAreaCode" lay-verify="" autocomplete="off" placeholder="请输入收款账号地区码" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收款账号</label>
				<div class="layui-input-block">
					<input type="text" name="receivablesNumber" lay-verify="" autocomplete="off" placeholder="请输入收款账号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收款账号名称</label>
				<div class="layui-input-block">
					<input type="text" name="receivablesAccountName" lay-verify="" autocomplete="off" placeholder="请输入收款账号名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">金额</label>
				<div class="layui-input-block">
					<input type="text" name="amount" lay-verify="number" autocomplete="off" placeholder="请输入金额" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">汇款用途</label>
				<div class="layui-input-block">
					<input type="text" name="remittancePurpose" lay-verify="" autocomplete="off" placeholder="请输入汇款用途" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">备注信息</label>
				<div class="layui-input-block">
					<input type="text" name="remarks" lay-verify="" autocomplete="off" placeholder="请输入备注信息" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收款账户短信通知手机号码</label>
				<div class="layui-input-block">
					<input type="text" name="receivablesPhone" lay-verify="phone|require" autocomplete="off" placeholder="请输入收款账户短信通知手机号码" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
    			<div class="layui-input-block">
      				<button class="layui-btn" lay-submit="" lay-filter="add">确认提交</button>
      				<a class="layui-btn layui-btn-primary" href="javascript:loader.load('#admin_container','jump/courtpayorder')">返回</a>
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
			fun.post(loader.baseurl+'/courtPayOrder/add',data,function(res){
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