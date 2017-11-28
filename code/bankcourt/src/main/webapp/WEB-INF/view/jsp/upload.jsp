
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
</head>
<body>
	<input type="file" id="upload">
	<button>上传</button>
</body>
<script src="<%=path%>/resources/js/base.js">
	
</script>
<script src="<%=path%>/resources/common/jquery.min.js">
	
</script>
<script>
	$(document).ready(function() {

		$("button").click(function() {
			// 获取cookie中的值
			var userToken = getUserToken();
			if (userToken == "") {
				return;
			}
			var formData = new FormData();
			formData.append("file", $("#upload")[0].files[0]);
			formData.append("userToken", userToken);
			formData.append("sys_click", "pos");

			var url = baseurl + "/data/import";

			$.ajax({
				type : "POST", //提交方式
				url : url,//路径
				data : formData,
				// 告诉jQuery不要去处理发送的数据
				processData : false,
				// 告诉jQuery不要去设置Content-Type请求头
				contentType : false,
				success : function(data) {//返回数据根据结果进行相应的处理
					var result = data.result;
					if (result == 1) {
						window.location.href = baseurl + "/index";
					} else {
						$('#msg').html(data.msg);
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
