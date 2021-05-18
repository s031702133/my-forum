package com.example.forum2.dto;

import java.util.Date;

public class UserDto {
    private Integer userId;

    private String userName;

    private String password;

    private String headPicture;

    private String bgPicture;

    private String phone;

    private String email;

    private Integer sex;

    private String signature;

    private Date createTime;

    private String spareOne;

    private String spareTwo;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getBgPicture() {
        return bgPicture;
    }

    public void setBgPicture(String bgPicture) {
        this.bgPicture = bgPicture;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSpareOne() {
        return spareOne;
    }

    public void setSpareOne(String spareOne) {
        this.spareOne = spareOne;
    }

    public String getSpareTwo() {
        return spareTwo;
    }

    public void setSpareTwo(String spareTwo) {
        this.spareTwo = spareTwo;
    }
}
