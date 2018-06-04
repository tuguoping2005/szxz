package com.tu.action;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport{
	private List<File> upload;
	private String uploadContentType;
	private String[] uploadFileName;
	
	private String result;
	

	

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
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
		
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		
		for(int i = 0;i<upload.size();i++){
			FileUtils.copyFile(upload.get(i), new File(file,uploadFileName[i]));
		}
		result = "上传成功";
		
		return SUCCESS;
	}

}
