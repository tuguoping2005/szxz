<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%= basePath%>">
<title>My JSP page</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script stype="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
	$(function() {
		$(".thumbs a").click(function() {
			var largePath = $(this).attr("href");
			var largeAlt = $(this).attr("title");
			$("#largeImg").attr({
				src : largePath,
				alt : largeAlt
			});
			return false;
		});
		
		$("#myfile").change(function() {
			$("#previewImg").attr("src","file:///" + $("#myfile").val());
		});
		
		var la = $("#large");
		la.hide();
		
		$("#previewImg").mousemove(function(e){
			la.css({
				top : e.pageY,
				left : e.pageX
			}).html('<img src = "' + this.src + '" />').show();
		}).mouseout(function(){
			la.hide();
		});
		
	});
	
	
</script>

</head>

<body>

	<hr>
	<div id="large"></div>
	<h2>图片预览</h2>
	<img id="previewImg" src="images/preview.jpg" width="80" height="80" />
	 <form action="uploadServlet.do" method="post" enctype="multipart/form-data" >
  		请选择图片：<input id="myfile" name="myfile" type="file" />
  		<input type="submit" value="提交"  />
  	</form>
	<p>
		<img id="largeImg" src="images/img1-lg.jpg" alt="Large Image" />
	</p>
	<p class="thumbs">
		<a href="images/img2-lg.jpg" title="Image2">
			<img src="images/img2-thumb.jpg">
		</a> 
		<a href="images/img3-lg.jpg" title="Image3">
			<img src="images/img3-thumb.jpg">
		</a> 
		<a href="images/img4-lg.jpg" title="Image4">
			<img src="images/img4-thumb.jpg">
		</a> 
		<a href="images/img5-lg.jpg" title="Image5">
			<img src="images/img5-thumb.jpg">
		</a> 
		<a href="images/img6-lg.jpg" title="Image6">
			<img src="images/img6-thumb.jpg">
		</a>
	</p>
</body>
</html>
