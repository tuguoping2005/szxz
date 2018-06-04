package com.tu.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡrequest�е�������
		InputStream fileResource = request.getInputStream();
		//������Ϣ���浽��ʱ�ļ���
		String tempFileName = "d:/tempfile";
		File tempFile = new File(tempFileName);
		//�������ָ����ʱ�ļ�
		FileOutputStream fos = new FileOutputStream(tempFile);
		
		byte[] b = new byte[1024];
		int len = 0;
		while((len = fileResource.read(b))!=-1){
			fos.write(b, 0, len);
		}
		fos.close();
		fileResource.close();
		
		//��ȡ�ļ�������
		RandomAccessFile accessFile = new RandomAccessFile(tempFile, "r");
		accessFile.readLine();
		String line = accessFile.readLine();
		int beginIndex = line.lastIndexOf("\\")+1;
		int endIndex = line.lastIndexOf("\"");
		String fileName = line.substring(beginIndex, endIndex);
		System.out.println(fileName); 
		
		//���¶�λ�ļ�ָ�뵽�ļ�ͷ
		accessFile.seek(0);
		//��ȡ�ļ����ݵĿ�ʼλ��
		long startPosition = 0;
		int i = 1;//�ӵ�һ�п�ʼ��
		while((len = fileResource.read(b))!= -1 && i<=4){
			if(len=='\n'){
				startPosition = accessFile.getFilePointer();
				i++;
			}
		}
		startPosition = startPosition -1;
	
		//��ȡ�ļ����ݵĽ���λ��
		
		int j = 1;
		accessFile.seek(tempFile.length());
		long endPosition = accessFile.getFilePointer();
		while(endPosition >=0 && j<=2){
			endPosition--;
			accessFile.seek(endPosition);
			if(accessFile.read(b)=='\n'){
				j++;
			}
		}
		endPosition = endPosition-1;
		
	}

}
