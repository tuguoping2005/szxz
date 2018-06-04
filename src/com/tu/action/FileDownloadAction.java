package com.tu.action;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileDownloadAction extends ActionSupport{
	
	private String inputPath;
	private String  fileName;
	
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public InputStream getInputStream() throws IOException {
//		InputStream in = ServletActionContext.getServletContext().getResourceAsStream(inputPath);
		String path = ServletActionContext.getServletContext().getRealPath("/images");
		String downloadPath=path+"\\"+fileName;
		File file = new File(downloadPath);
		InputStream in = FileUtils.openInputStream(file);
		return  in;
	}
	
	public String getDownloadFileName(){
		String name = "";
		try {
			name = URLEncoder.encode("ÎÄ¼þÏÂÔØ", "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return name;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

}
