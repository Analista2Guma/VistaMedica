package com.guma.desarrollo.gvm.POJO;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class Articulo {

    String mCod,mNam,mExi,mLab,mUnd,mPts;

    public Articulo(String mCod, String mNam, String mExi, String mLab, String mUnd, String mPts) {
        this.mCod = mCod;
        this.mNam = mNam;
        this.mExi = mExi;
        this.mLab = mLab;
        this.mUnd = mUnd;
        this.mPts = mPts;
    }
    public Articulo(){}

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

    public String getmExi() {
        return mExi;
    }

    public void setmExi(String mExi) {
        this.mExi = mExi;
    }

    public String getmLab() {
        return mLab;
    }

    public void setmLab(String mLab) {
        this.mLab = mLab;
    }

    public String getmUnd() {
        return mUnd;
    }

    public void setmUnd(String mUnd) {
        this.mUnd = mUnd;
    }

    public String getmPts() {
        return mPts;
    }

    public void setmPts(String mPts) {
        this.mPts = mPts;
    }
}
