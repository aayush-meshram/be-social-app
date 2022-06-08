package com.geniobits.besocialapp.customVariable;

public class UserInfo {
    public String userName;
    public int userID;
    public String emailID;
    public String gender;
    public String status;

    public UserInfo(int userID, String userName, String emailID, String gender, String status)  {
        this.userName = userName;
        this.userID = userID;
        this.emailID = emailID;
        this.gender = gender;
        this.status = status;
    }

}
