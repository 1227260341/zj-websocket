package com.zj.modules.domain;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 */
public class FilesConfig {

	private static final long serialVersionUID = 1L;

	private java.lang.Integer id;
	
	private java.lang.String name;
	
	private java.lang.String type;

	private java.lang.String path;

	private java.lang.String size;

	private java.lang.String validFlag;
	/**
	 * 备注，注释
	 */
	private java.lang.String comment;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date makeTime;
	
	private java.lang.String makeUser;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date modifyTime;
	
	private java.lang.String modifyUser;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getType() {
		return type;
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}
	public java.lang.String getPath() {
		return path;
	}
	public void setPath(java.lang.String path) {
		this.path = path;
	}
	public java.lang.String getSize() {
		return size;
	}
	public void setSize(java.lang.String size) {
		this.size = size;
	}
	public java.lang.String getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(java.lang.String validFlag) {
		this.validFlag = validFlag;
	}
	public java.lang.String getComment() {
		return comment;
	}
	public void setComment(java.lang.String comment) {
		this.comment = comment;
	}
	public java.util.Date getMakeTime() {
		return makeTime;
	}
	public void setMakeTime(java.util.Date makeTime) {
		this.makeTime = makeTime;
	}
	public java.lang.String getMakeUser() {
		return makeUser;
	}
	public void setMakeUser(java.lang.String makeUser) {
		this.makeUser = makeUser;
	}
	public java.util.Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public java.lang.String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(java.lang.String modifyUser) {
		this.modifyUser = modifyUser;
	}
	
	

}