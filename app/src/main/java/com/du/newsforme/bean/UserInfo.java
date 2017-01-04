package com.du.newsforme.bean;

/**
 * Created by long on 16-12-30.
 */

public class UserInfo {
   private String token;
   private String userId ;
   private String userName;
   private String userIcon;
   private String userGender;

    @Override
    public String toString() {
        return "UserInfo{" +
                "token='" + token + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", userGender='" + userGender + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
}
