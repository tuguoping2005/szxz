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
		// ��ȡ�ϴ��ļ���·��
		String uploadPath = request.getServletContext().getRealPath("/")
				+ "images";
		System.out.println(uploadPath);
		File file = new File(uploadPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String result = "�ϴ��ɹ�!";
		try {
			// ��ʼ��SmartUpload
			SmartUpload su = new SmartUpload();
			su.initialize(getServletConfig(), request, response);
			su.setMaxFileSize(1024*1024*10);
			su.setTotalMaxFileSize(1024 * 1024 * 100);
			su.setAllowedFilesList("txt,jpg,png,gif");
//			su.setDeniedFilesList("jar,zip,rar");

			
			
			// �ϴ��ļ�
			su.upload();
			int count = su.save(uploadPath);
			//��ȡ��������
			for(int i = 0;i<su.getFiles().getCount();i++){
				com.jspsmart.upload.File tempFile = su.getFiles().getFile(i);
				System.out.println("�ļ�������:"+tempFile.getFieldName());
				System.out.println("�ļ��ļ�����:"+tempFile.getFileName());
				System.out.println("�ļ��ļ���չ������:"+tempFile.getFileExt());
				System.out.println("�ļ���С:"+tempFile.getSize());
				String ss = new String(tempFile.getFilePathName().getBytes("gbk"),"utf-8");
				System.out.println("�ļ�·����:"+ss);
			}
			
			System.out.println("�ϴ��ɹ��ˣ�" + count + "���ļ�");
		} catch (Exception e) {
			if (e.getMessage().indexOf("1015") != -1) {
				result = "�ϴ�ʧ��:�ϴ��ļ����Ͳ���ȷ";
			} else if (e.getMessage().indexOf("1010") != -1) {
				result = "�ϴ�ʧ��:�ϴ��ļ����Ͳ���ȷ";
			} else if (e.getMessage().indexOf("1105") != -1) {
				result = "�ϴ�ʧ��:�ϴ��ļ��Ĵ�С���������ϴ����ļ���С";
			} else if (e.getMessage().indexOf("1110") != -1) {
				result = "�ϴ�ʧ��:�ϴ����ļ���С���������ϴ������ļ���С";
			}
			e.printStackTrace();

		}
		
	
		
		request.setAttribute("result", result);
		request.getRequestDispatcher("jsp/02.jsp").forward(request, response);

	}

}
