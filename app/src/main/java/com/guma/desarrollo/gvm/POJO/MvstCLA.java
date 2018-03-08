package com.guma.desarrollo.gvm.POJO;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class MvstCLA {
    String mRut,mCcl,mArt,mDec,mCnt,mClf,mVnt,mDia,mNcl;

    public MvstCLA(String mRut, String mCcl, String mArt, String mDec, String mCnt, String mClf, String mVnt, String mDia,String mNcl) {
        this.mRut = mRut;
        this.mCcl = mCcl;
        this.mArt = mArt;
        this.mDec = mDec;
        this.mCnt = mCnt;
        this.mClf = mClf;
        this.mVnt = mVnt;
        this.mDia = mDia;
        this.mNcl = mNcl;
    }

    public MvstCLA(){}

    public String getmNcl() {
        return mNcl;
    }

    public void setmNcl(String mNcl) {
        this.mNcl = mNcl;
    }

    public String getmRut() {
        return mRut;
    }

    public String getmDia() {
        return mDia;
    }

    public void setmDia(String mDia) {
        this.mDia = mDia;
    }

    public void setmRut(String mRut) {
        this.mRut = mRut;
    }

    public String getmCcl() {
        return mCcl;
    }

    public void setmCcl(String mCcl) {
        this.mCcl = mCcl;
    }

    public String getmArt() {
        return mArt;
    }

    public void setmArt(String mArt) {
        this.mArt = mArt;
    }

    public String getmDec() {
        return mDec;
    }

    public void setmDec(String mDec) {
        this.mDec = mDec;
    }

    public String getmCnt() {
        return mCnt;
    }

    public void setmCnt(String mCnt) {
        this.mCnt = mCnt;
    }

    public String getmClf() {
        return mClf;
    }

    public void setmClf(String mClf) {
        this.mClf = mClf;
    }

    public String getmVnt() {
        return mVnt;
    }

    public void setmVnt(String mVnt) {
        this.mVnt = mVnt;
    }
}
