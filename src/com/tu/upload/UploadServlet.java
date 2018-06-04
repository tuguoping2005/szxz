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
		//��ȡrequest�е�������
		InputStream fileResource = request.getInputStream();
		//������Ϣ���浽��ʱ�ļ���
		String tempFileName = "d:/tempfile";
		File tempFile = new File(tempFileName);
		//�������ָ����ʱ�ļ�
		FileOutputStream fos = new FileOutputStream(tempFile);
		
		byte[] b = new byte[1024];
		int n = 0;
		while((n = fileResource.read(b))!=-1){
			fos.write(b, 0, n);
		}
		//�ر������  ������
		fos.close();
		fileResource.close();
		
		//��ȡ�ļ�������
		RandomAccessFile randomFile = new RandomAccessFile(tempFile, "r");
		randomFile.readLine();
		String str = randomFile.readLine();
		int beginIndex = str.lastIndexOf("\\")+1;
		int endIndex = str.lastIndexOf("\"");
		String fileName = str.substring(beginIndex, endIndex);
		
		//���¶�λ�ļ�ָ�뵽�ļ�ͷ
		randomFile.seek(0);
		long startPosition = 0;
		int i = 1;//�ӵ�һ�п�ʼ��
		//��ȡ�ļ����ݵĿ�ʼλ��
		while((n = randomFile.readByte())!= -1 && i<=4){
			if(n=='\n'){
				startPosition = randomFile.getFilePointer();
				i++;
			}
		}
		startPosition = randomFile.getFilePointer() -1;
	
		//��ȡ�ļ�����  ����λ��
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
		
		//���ñ����ϴ��ļ���·��
		String realPath = getServletContext().getRealPath("/")+"images";
		File fileUpload = new File(realPath);
		if(!fileUpload.exists()){
			fileUpload.mkdirs();
		}
		
		File saveFile = new File(realPath,fileName);
		RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile, "rw");
		//����ʱ�ļ��ж�ȡ�ļ�����(������ֹλ�û�)
		randomFile.seek(startPosition);
		while(startPosition < endPosition){
			randomAccessFile.write(randomFile.readByte());
			startPosition = randomFile.getFilePointer();
		}
		
		//�ر��� ɾ����ʱ�ļ�
		randomAccessFile.close();
		randomFile.close();
		tempFile.delete();
		
		request.setAttribute("result", "�ϴ��ɹ�"); 
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/01.jsp");
		requestDispatcher.forward(request, response);
		
		
		
	}

}
