package com.guma.desarrollo.gvm.POJO;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class Cliente {

    String mCod,mNam,mDir,mRuc;

    public Cliente(String mCod, String mNam, String mDir, String mRuc) {
        this.mCod = mCod;
        this.mNam = mNam;
        this.mDir = mDir;
        this.mRuc = mRuc;
    }
    public Cliente(){}

    public String getmCod() {
        return mCod;
    }

    public void setmCod(String mCod) {
        this.mCod = mCod;
    }

    public String getmNam() {
        return mNam;
    }

    public void setmNam(String mNam) {
        this.mNam = mNam;
    }

    public String getmDir() {
        return mDir;
    }

    public void setmDir(String mDir) {
        this.mDir = mDir;
    }

    public String getmRuc() {
        return mRuc;
    }

    public void setmRuc(String mRuc) {
        this.mRuc = mRuc;
    }
}
