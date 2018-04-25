fun = (function(n){
	/**
	* jQuery ajax 底层封装,禁止直接调用
	* @author Mr.Ding
	* **/
	var ajax = function(a,b,c,d){
		return n.ajax({
			url:b,
			dataType:'json',
			method:a,
			timeout:5000,  //超时时间,单位毫秒
			data:c,
			contentType: 'application/json',
			success:function(res){ 
				if(d=='undefined') return;
				if(typeof(d)!='function') return console.error('callback parameter error!');
				if(res.code==3) return layer.alert('您没有权限查看该页面或执行此操作，请与管理员联系！',{icon:4,end:function(){
					layer.closeAll('loading');
				}});
				if(res.code==4){
					loader.clearCookie();
					layer.alert(res.msg,{icon:res.code,end:function(){
						loader.UnloadConfirm.clear();
						location.href = 'login.html';
					}});
					return;
				}
				d(res);
			},
			error:function(xhr,status){
				if(status=='timeout'){
					xhr.abort();  //中断请求
					return layer.alert('请求超时，请稍后重试！',{icon:2,end:function(){
						layer.closeAll('loading');
					}});
				}
			}
		});
	}
	return Object.defineProperties({},{
		/**
		 * 用ajax发送post请求
		 * @param url 请求地址
		 * @param data 发送的数据
		 * @param callback 请求成功时的回调函数,参数为请求响应数据
		 * @author Mr.Ding
		 * **/
		post:{
			value:function(url,data,callback){
				return ajax('post',url,JSON.stringify(data),callback);
			},
			writable:false,
			configurable:false
		},
		/**
		 * 用ajax发送post请求
		 * @param url 请求地址
		 * @param data 发送的数据
		 * @param callback 请求成功时的回调函数，参数为请求响应数据
		 * @author Mr.Ding
		 * **/
		get:{
			value:function(url,data,callback){
				return ajax('get',url,data,callback);
			},
			writable:false,
			configurable:false
		}
	});
})(jQuery)
