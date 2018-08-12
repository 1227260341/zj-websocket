package com.zj.modules.domain;

import java.io.Serializable;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 聊天记录表
 * </p>
 *
 * @author zhouzhenjang123
 * @since 2018-08-11
 */
public class UserChat implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer userId;
    private String userName;
    private Integer objectId;
    private String message;
    private Integer type;
    private String validFlag;
    private Date makeTime;
    private String makeUser;
    private Date modifyTime;
    private String modifyUser;


    public UserChat() {
		super();
	}

	public UserChat(Integer id, Integer userId, String userName, Integer objectId, String message, Integer type,
			String validFlag, Date makeTime, String makeUser, Date modifyTime, String modifyUser) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.objectId = objectId;
		this.message = message;
		this.type = type;
		this.validFlag = validFlag;
		this.makeTime = makeTime;
		this.makeUser = makeUser;
		this.modifyTime = modifyTime;
		this.modifyUser = modifyUser;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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
        return "UserChat{" +
        ", id=" + id +
        ", userId=" + userId +
        ", userName=" + userName +
        ", objectId=" + objectId +
        ", message=" + message +
        ", type=" + type +
        ", validFlag=" + validFlag +
        ", makeTime=" + makeTime +
        ", makeUser=" + makeUser +
        ", modifyTime=" + modifyTime +
        ", modifyUser=" + modifyUser +
        "}";
    }
}
