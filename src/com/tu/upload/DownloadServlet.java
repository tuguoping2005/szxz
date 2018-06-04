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
		//��ȡ�ļ�������·��
		String path = getServletContext().getRealPath("/")+"images/";
		String fileName = request.getParameter("fileName");
		File file = new File(path+fileName);
		if(file.exists()){
			//������Ӧ������application/x-msdownload
			response.setContentType("application/octet-stream");
			//����ͷ��Ϣ
			response.setHeader("Content-Disposition", "attachment;fileName=\""+fileName+"\"");
			InputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			
			int len = 0;
			byte b[] = new byte[1024];
			while((len = in.read(b))!=-1){
				out.write(b, 0, len);
			}
			
			//�ر���
			out.close();
			in.close();
		}else{
			request.setAttribute("errorResult","�ļ�����������ʧ��");
			request.getRequestDispatcher("jsp/01.jsp").forward(request, response); 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
