package com.hex.code.user.model;

import java.util.Date;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-09.23:12
 */
public class User {

    private long userId;
    private String name;
    private String phoneNumber;
    private String password;
    private Date creatTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}
