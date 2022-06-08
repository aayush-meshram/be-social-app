package com.geniobits.besocialapp.customVariable;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfo implements Parcelable {

    private int userID;
    private String userName;
    private String emailID;
    private String gender;
    private String status;

    public UserInfo(int userID, String userName, String emailID, String gender, String status)  {
        this.setUserName(userName);
        this.setUserID(userID);
        this.setEmailID(emailID);
        this.setGender(gender);
        this.setStatus(status);
    }

    protected UserInfo(Parcel in) {
        userID = in.readInt();
        userName = in.readString();
        emailID = in.readString();
        gender = in.readString();
        status = in.readString();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userID);
        parcel.writeString(userName);
        parcel.writeString(emailID);
        parcel.writeString(gender);
        parcel.writeString(status);
    }
}
