package com.tu.smartupload;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

public class SmartUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 获取上传文件的路径
		String uploadPath = request.getServletContext().getRealPath("/")
				+ "images";
		System.out.println(uploadPath);
		File file = new File(uploadPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String result = "上传成功!";
		try {
			// 初始化SmartUpload
			SmartUpload su = new SmartUpload();
			su.initialize(getServletConfig(), request, response);
			su.setMaxFileSize(1024*1024*10);
			su.setTotalMaxFileSize(1024 * 1024 * 100);
			su.setAllowedFilesList("txt,jpg,png,gif");
//			su.setDeniedFilesList("jar,zip,rar");

			
			
			// 上传文件
			su.upload();
			int count = su.save(uploadPath);
			//获取其他属性
			for(int i = 0;i<su.getFiles().getCount();i++){
				com.jspsmart.upload.File tempFile = su.getFiles().getFile(i);
				System.out.println("文件表单名称:"+tempFile.getFieldName());
				System.out.println("文件文件名称:"+tempFile.getFileName());
				System.out.println("文件文件扩展名名称:"+tempFile.getFileExt());
				System.out.println("文件大小:"+tempFile.getSize());
				String ss = new String(tempFile.getFilePathName().getBytes("gbk"),"utf-8");
				System.out.println("文件路径名:"+ss);
			}
			
			System.out.println("上传成功了：" + count + "个文件");
		} catch (Exception e) {
			if (e.getMessage().indexOf("1015") != -1) {
				result = "上传失败:上传文件类型不正确";
			} else if (e.getMessage().indexOf("1010") != -1) {
				result = "上传失败:上传文件类型不正确";
			} else if (e.getMessage().indexOf("1105") != -1) {
				result = "上传失败:上传文件的大小大于允许上传的文件大小";
			} else if (e.getMessage().indexOf("1110") != -1) {
				result = "上传失败:上传总文件大小大于允许上传的总文件大小";
			}
			e.printStackTrace();

		}
		
	
		
		request.setAttribute("result", result);
		request.getRequestDispatcher("jsp/02.jsp").forward(request, response);

	}

}
