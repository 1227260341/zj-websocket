package com.zj.modules.domain;

import java.io.Serializable;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhouzhenjang123
 * @since 2018-07-28
 */
public class User {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String userName;
    private String password;
    private String actualName;
    private String type;
    private String validFlag;
    private Date makeTime;
    private String makeUser;
    private Date modifyTime;
    private String modifyUser;
    private String head;
    private String headUrl;


    public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public Date getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Date makeTime) {
        this.makeTime = makeTime;
    }

    public String getMakeUser() {
        return makeUser;
    }

    public void setMakeUser(String makeUser) {
        this.makeUser = makeUser;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }


    @Override
    public String toString() {
        return "User{" +
        ", id=" + id +
        ", userName=" + userName +
        ", password=" + password +
        ", actualName=" + actualName +
        ", type=" + type +
        ", validFlag=" + validFlag +
        ", makeTime=" + makeTime +
        ", makeUser=" + makeUser +
        ", modifyTime=" + modifyTime +
        ", modifyUser=" + modifyUser +
        "}";
    }
}
