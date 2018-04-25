fun = (function(n){
	/**
	* jQuery ajax 底层封装,禁止直接调用
	* @author Mr.Ding
	* **/
	var ajax = function(a,b,c,d){
		n.ajax({
			url:b,
			dataType:'json',
			method:a,
			data:JSON.stringify(c),
			contentType: 'application/json',
			success:function(res){
				if(d=='undefined') return;
				if(typeof(d)!='function') return console.error('callback parameter error!');
				d(res);
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
				ajax('post',url,data,callback);
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
				ajax('get',url,data,callback);
			},
			writable:false,
			configurable:false
		}
	});
})(jQuery)
