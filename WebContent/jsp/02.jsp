<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<title>文件批量上传</title>
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

	});
</script>

</head>

<body>
	<h2>文件批量上传</h2>
	<form action="smartUploadServlet.do" method="post"
		enctype="multipart/form-data">
		上传文件1:<input type="file" name="myfile1" /><br> 上传文件2:<input
			type="file" name="myfile2" /><br> 上传文件3:<input type="file"
			name="myfile3" /><br> <input type="submit" value="提交" /><br>${result }
	</form>
	<hr/>
	<!-- 下载:<a href="smartDownloadServlet.do?fileName=img2-lg.jpg">img2-lg.jpg</a> -->
	<h2>批量下载</h2>
	 <form method="post" action="batchSmartDownload.do">
	 	<input type="checkbox" name="fileName" value="img2-lg.jpg">img2
	 	<input type="checkbox" name="fileName" value="img3-lg.jpg">img3
	 	<input type="checkbox" name="fileName" value="img4-lg.jpg">img4
	 	<input type="submit" value="下载">
	 </form>
	 
	 <hr/>
	<p>
		<img id="largeImg" src="images/img1-lg.jpg" alt="Large Image" />
	</p>
	<p class="thumbs">
		<a href="images/img2-lg.jpg" title="Image2"> <img
			src="images/img2-thumb.jpg">
		</a> <a href="images/img3-lg.jpg" title="Image3"> <img
			src="images/img3-thumb.jpg">
		</a> <a href="images/img4-lg.jpg" title="Image4"> <img
			src="images/img4-thumb.jpg">
		</a> <a href="images/img5-lg.jpg" title="Image5"> <img
			src="images/img5-thumb.jpg">
		</a> <a href="images/img6-lg.jpg" title="Image6"> <img
			src="images/img6-thumb.jpg">
		</a>
	</p>
</body>
</html>
