package com.tu.smartupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class BatchSmartDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;fileName=test.zip");
		
		String path = request.getServletContext().getRealPath("/")+"images/";
		String[] fileNames = request.getParameterValues("fileName");
		ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
		String str="";
		String rt="\r\t";
		for(String fileName:fileNames){
			str+=fileName+rt;
			File file = new File(path+fileName);
			//设置下一个要压缩的条目
			zos.putNextEntry(new ZipEntry(fileName));
			FileInputStream fis = new FileInputStream(file);
			
			byte[] b = new byte[1024];
			int len = -1;
			while((len=fis.read(b))!=-1){
				zos.write(b, 0, len);
			}
			zos.flush();
			fis.close();
		}
		zos.setComment("download success:"+rt+str);	
		zos.flush();
		zos.close();
	}

}
