package com.guma.desarrollo.gvm.POJO;

/**
 * Created by maryan.espinoza on 05/03/2018.
 */

public class Usuario {
    String mUser,mPass,mNamv;


    public Usuario(String mUser, String mPass, String mNamv) {
        this.mUser = mUser;
        this.mPass = mPass;
        this.mNamv = mNamv;
    }

    public Usuario(){}

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }

    public String getmPass() {
        return mPass;
    }

    public void setmPass(String mPass) {
        this.mPass = mPass;
    }

    public String getmNamv() {
        return mNamv;
    }

    public void setmNamv(String mNamv) {
        this.mNamv = mNamv;
    }
}
