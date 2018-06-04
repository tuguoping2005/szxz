package com.tu.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取文件的下载路径
		String path = getServletContext().getRealPath("/")+"images/";
		String fileName = request.getParameter("fileName");
		File file = new File(path+fileName);
		if(file.exists()){
			//设置相应的类型application/x-msdownload
			response.setContentType("application/octet-stream");
			//设置头信息
			response.setHeader("Content-Disposition", "attachment;fileName=\""+fileName+"\"");
			InputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			
			int len = 0;
			byte b[] = new byte[1024];
			while((len = in.read(b))!=-1){
				out.write(b, 0, len);
			}
			
			//关闭流
			out.close();
			in.close();
		}else{
			request.setAttribute("errorResult","文件不存在下载失败");
			request.getRequestDispatcher("jsp/01.jsp").forward(request, response); 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
