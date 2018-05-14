package com.guma.desarrollo.gvm.POJO;

/**
 * Created by maryan.espinoza on 05/03/2018.
 */

public class Usuario {
    String mUser,mPass,mNamv,mUID,mRutas;


    public Usuario(){}

    public Usuario(String mUser, String mPass, String mNamv, String mUID, String mRutas) {
        this.mUser = mUser;
        this.mPass = mPass;
        this.mNamv = mNamv;
        this.mUID = mUID;
        this.mRutas = mRutas;
    }

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

    public String getmUID() {
        return mUID;
    }

    public void setmUID(String mUID) {
        this.mUID = mUID;
    }

    public String getmRutas() {
        return mRutas;
    }

    public void setmRutas(String mRutas) {
        this.mRutas = mRutas;
    }
}
