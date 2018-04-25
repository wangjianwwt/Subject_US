	'use strict'
	layui.use(['layer', 'element'], function() {
	    var Loader = function() {
//			this.baseurl = 'http://47.93.233.214:18081/bankcourt'; // 服务器环境配置
	        this.baseurl = 'http://localhost:28080/bankcourt'; // 前端开发配置
	        //this.baseurl = 'http://localhost:38080/bankcourt'; // 后端开发配置
	        this.UNLOAD_MSG = '您是否确定离开?';
	        this.send_data = {};
	        this.resp_data = {};
	        this.temp_data = {};
	        this.UnloadConfirm = {
	            set: function(msg) { //启用监听浏览器刷新、关闭的方法
	                window.onbeforeunload = function(event) {
	                    event = event || window.event;
	                    event.returnValue = msg;
	                }
	            },
	            clear: function() { //关闭监听浏览器刷新、关闭的方法
	                window.onbeforeunload = function() {};
	            }
	        };
	        this.config = {
	            load: 'loading',
	            msg: '缺少必要参数',
	            empty: 'undefined',
	            fun: 'function'
	        }
	    };
	    /**
	     *页面初始化
	     * @author Mr.Ding
	     * **/
	    Loader.prototype.init = function() {
	            if (loader.getCookie('token') != null){
	            	this.UnloadConfirm.set(this.MSG_UNLOAD);
	            	this.load('#admin_container', 'home.html');
	            }else{
		            location.href = 'login.html';
	            }
	        }
	        /**
	         * 获取登录cookie token
	         * @author 王坚
	         * **/
	    Loader.prototype.getUserToken = function() {
	            if (document.cookie.length > 0) {
	                var c_name = "userInfo";
	                var c_key = "userToken";

	                var c_index = document.cookie.indexOf(c_name + "=")
	                if (c_index != -1) {
	                    // 开始位置
	                    var c_start = c_index + (c_name + "=").length;
	                    // 结束位置
	                    var c_end = document.cookie.length;
	                    if (c_end == -1) c_end = document.cookie.length;
	                    // 截取字符串
	                    var cookie = document.cookie.substring(c_start, c_end);
	                    // 转JSON对象
	                    var jsonObj = JSON.parse(JSON.parse(cookie));
	                    // 返回token
	                    return jsonObj.userToken;
	                }
	            }
	            return "";
	        }
	        /**
	         * 保存数据到cookie
	         * @param name 存放数据的键
	         * @param 数据具体值
	         * @authro NetWork
	         * **/
	    Loader.prototype.setCookie = function(name, value) {
	        var Days = 1;
	        var exp = new Date();
	        exp.setTime(exp.getTime() + Days * 24 * 3600 * 1000);
	        document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
	    }

	    /**
	     * 从cookie中读取数据
	     * @param name 读取数据的键
	     * @author NetWork
	     * **/
	    Loader.prototype.getCookie = function(name) {
	        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	        if (arr = document.cookie.match(reg))
	            return unescape(arr[2]);
	        else
	            return null;
	    }

	    /**
	     * 删除cookie
	     * @param name 删除数据的键
	     * @author NetWork
	     * **/
	    Loader.prototype.delCookie = function(name) {
	            var exp = new Date();
	            exp.setTime(exp.getTime() - 1);
	            var cval = getCookie(name);
	            if (cval != null)
	                document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
	        }
	        /**
	         * 清除所有cookie
	         * @author NetWork
	         * **/
	    Loader.prototype.clearCookie = function() {
	        var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
	        if (keys) {
	            for (var i = keys.length; i--;)
	                document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString();
	        }
	    }

	    /**
	     * 页面加载器，所有页面跳转均通过这个方法
	     * @param elem dom选择器，形如.tmp、#tmp(必要)
	     * @param url 页面地址(必要)
	     * @param data 要传递到后端的额外参数
	     * @callback 请求成功回调函数，参数为后端返回的结果集
	     * @author Mr.Ding
	     * **/
	    Loader.prototype.load = function(elem, url, data, callback) {
	        if (loader.getCookie('token') == null) {
	            loader.UnloadConfirm.clear();
	            location.href = 'login.html';
	            return;
	        }
	        var con = this.config;
	        layer.load();
	        if (elem === con.empty || url === con.empty) return layer.msg(con.msg);
	        this.send_data = typeof(data) === con.empty ? '' : { data: data };
	        $(elem).load(url, this.send_data, function(res) {
	            if (res) {
	                layer.closeAll(con.load);
	                this.resp_data = res;
	                if (callback != con.empty && callback == con.fun) callback(res);
	            }
	        });
	    }

	    /**
	     * 获取form表单值
	     * @param el : form表单id
	     * @author Mr.Ding
	     * @return 表单值json对象
	     * **/
	    Loader.prototype.serialize = function(el) {
	        var form = $(el);
	        var valueGroup = decodeURIComponent(form.serialize()).split('&');
	        var data = '{';
	        for (var i = 0; i < valueGroup.length; i++) {
	            var temp = valueGroup[i].split('=');
	            temp[1] = '"' + temp[1] + '"' || "''";
	            data += ('"' + temp[0] + '"' + ':' + temp[1]);
	            if (valueGroup.length - 1 != i) data += ',';
	            else data += '}';
	        }
	        return JSON.parse(data);
	    }
	    
	    /**
	     * 会话过期或无权限的跳转
	     * @param res 请求返回的结果集
	     * @author Mr.Ding
	     * **/
	    Loader.prototype.fail = function(res){
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
	    }

	    window.loader = new Loader();

	    $(".leftnav h2").click(function() {
	        $(this).next().slideToggle(200);
	        $(this).toggleClass("on");
	    })
	    $(".leftnav ul li a").click(function() {
	        $("#a_leader_txt").text($(this).text());
	        $(".leftnav ul li a").removeClass("on");
	        $(this).addClass("on");
	    })

	});