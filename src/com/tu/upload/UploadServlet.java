package com.tu.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取request中的输入流
		InputStream fileResource = request.getInputStream();
		//将流信息保存到临时文件中
		String tempFileName = "d:/tempfile";
		File tempFile = new File(tempFileName);
		//将输出流指向临时文件
		FileOutputStream fos = new FileOutputStream(tempFile);
		
		byte[] b = new byte[1024];
		int n = 0;
		while((n = fileResource.read(b))!=-1){
			fos.write(b, 0, n);
		}
		//关闭输出流  输入流
		fos.close();
		fileResource.close();
		
		//读取文件的名字
		RandomAccessFile randomFile = new RandomAccessFile(tempFile, "r");
		randomFile.readLine();
		String str = randomFile.readLine();
		int beginIndex = str.lastIndexOf("\\")+1;
		int endIndex = str.lastIndexOf("\"");
		String fileName = str.substring(beginIndex, endIndex);
		
		//重新定位文件指针到文件头
		randomFile.seek(0);
		long startPosition = 0;
		int i = 1;//从第一行开始读
		//获取文件内容的开始位置
		while((n = randomFile.readByte())!= -1 && i<=4){
			if(n=='\n'){
				startPosition = randomFile.getFilePointer();
				i++;
			}
		}
		startPosition = randomFile.getFilePointer() -1;
	
		//获取文件内容  结束位置
		randomFile.seek(randomFile.length());
		long endPosition = randomFile.getFilePointer();
		int j = 1;
		while(endPosition >=0 && j<=2){
			endPosition--;
			randomFile.seek(endPosition);
			if(randomFile.readByte()=='\n'){
				j++;
			}
		}
		endPosition = endPosition-1;
		
		//设置保存上传文件的路径
		String realPath = getServletContext().getRealPath("/")+"images";
		File fileUpload = new File(realPath);
		if(!fileUpload.exists()){
			fileUpload.mkdirs();
		}
		
		File saveFile = new File(realPath,fileName);
		RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile, "rw");
		//从临时文件中读取文件内容(根据起止位置获)
		randomFile.seek(startPosition);
		while(startPosition < endPosition){
			randomAccessFile.write(randomFile.readByte());
			startPosition = randomFile.getFilePointer();
		}
		
		//关闭流 删除临时文件
		randomAccessFile.close();
		randomFile.close();
		tempFile.delete();
		
		request.setAttribute("result", "上传成功"); 
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/01.jsp");
		requestDispatcher.forward(request, response);
		
		
		
	}

}
