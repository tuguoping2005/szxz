<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%= basePath%>">
<title>错误页面</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script stype="text/javascript" src="js/jquery-1.11.1.js"></script>

</head>

<body>
	<h2>上传失败</h2>
	<s:fielderror></s:fielderror>
</body>
</html>
