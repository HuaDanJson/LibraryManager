package com.example.jason.examination.data;

import cn.bmob.v3.BmobUser;

public class CurrentUser extends BmobUser {

    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "userType='" + userType + '\'' +
                '}';
    }
}
