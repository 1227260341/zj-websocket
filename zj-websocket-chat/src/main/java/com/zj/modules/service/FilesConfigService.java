package com.zj.modules.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface FilesConfigService {
	public Integer upload(MultipartFile file, String foldName, HttpServletRequest request);
	
	public String getFullPath(String path);
	
	public String getFullPathById(Integer id);
	
	public void setInvalid(Integer id);
	
	public String downEncryptFileUrl(String path);
	
	public String downEncryptFileUrlById(Integer id);
	
	public String getFileNameById(Integer id);
	
	public String uploadBase64Pic(String base64Data, String foldName) throws Exception ;
}
