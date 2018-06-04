package com.tu.action;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport{
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	
	private String result;
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String execute() throws Exception {
		String path = ServletActionContext.getServletContext().getRealPath("/images");
		System.out.println(path);
		System.out.println(uploadContentType);
		System.out.println(uploadFileName);
		
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		FileUtils.copyFile(upload, new File(file,uploadFileName));
		result = "上传成功";
		
		return SUCCESS;
	}

}
