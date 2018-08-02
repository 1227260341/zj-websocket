package com.zj.modules.domain;

import java.io.Serializable;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 用户好友或群表
 * </p>
 *
 * @author zhouzhenjang123
 * @since 2018-07-31
 */
public class FriendUser {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer userId;
    /**
     * 对象id
     */
    private Integer objectId;
    /**
     * 1 好友，2 群
     */
    private Integer type;
    private String validFlag;
    private Date makeTime;
    private String makeUser;
    private Date modifyTime;
    private String modifyUser;


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

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
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
        return "FriendUser{" +
        "id=" + id +
        ", userId=" + userId +
        ", objectId=" + objectId +
        ", type=" + type +
        ", validFlag=" + validFlag +
        ", makeTime=" + makeTime +
        ", makeUser=" + makeUser +
        ", modifyTime=" + modifyTime +
        ", modifyUser=" + modifyUser +
        "}";
    }
}
