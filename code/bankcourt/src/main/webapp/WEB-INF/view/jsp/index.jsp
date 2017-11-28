<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
</head>
<body>
<P>Hello World!!!</P>
</body>

<link rel="stylesheet" type="text/css" href="<%=path %>/resources/common/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/common/themes/icon.css">
<script src="<%=path %>/resources/js/base.js"> </script>
<script src="<%=path %>/resources/common/jquery.min.js"> </script>
<script src="<%=path %>/resources/common/jquery.easyui.min.js"></script>


</html>
