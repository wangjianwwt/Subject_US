<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<html>
<head>
<title>Title</title>
</head>
<link rel="stylesheet" href="<%=path%>/resources/layui/css/layui.css" />
<link rel="stylesheet" href="<%=path%>/resources/css/pintuer.css" />
<link rel="stylesheet" href="<%=path%>/resources/css/common.css" />
<script type="text/javascript" src="<%=path%>/resources/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/resources/layui/layui.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/common.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/function.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/vuejs/vue.min.js"></script>
<body>
	<input type="file" id="upload">
	<button id="posupload">上传</button>
</body>
<script type="text/javascript">
	layui.use([ 'form', 'laydate' ], function() {
		$("#posupload").click(function() {
			// 获取cookie中的值
			var userToken = loader.getCookie('token');
			if (userToken == "") {
				return;
			}
			var formData = new FormData();
			formData.append("file", $("#upload")[0].files[0]);
			formData.append("userToken", userToken);
			formData.append("sys_click", "pos");

			var url = loader.baseurl+"/data/import";
			$.ajax({
				type : "POST", //提交方式
				url : url,//路径
				data : formData,
				// 告诉jQuery不要去处理发送的数据
				processData : false,
				// 告诉jQuery不要去设置Content-Type请求头
				contentType : false,
				success : function(data) {//返回数据根据结果进行相应的处理
					var result = data.code;
					if (result == 1) {
						alert(data.msg);
					} else {
						alert(data.msg);
					}
				},
				error : function() {
					alert("请求异常!");
				}
			});
		});

	});
</script>
</html>
