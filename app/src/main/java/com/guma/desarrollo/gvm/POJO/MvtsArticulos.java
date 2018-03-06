package com.guma.desarrollo.gvm.POJO;

/**
 * Created by maryan.espinoza on 28/02/2018.
 */

public class MvtsArticulos {
    String mRut1,mventa,mMeta,mV3m;
    String mRut2,mArt,mDic,mClf,mCnt,mVnt;
    int mHts;


    public MvtsArticulos(String mRut1, String mventa, String mMeta, String mV3m, String mRut2, String mArt, String mDic, String mClf, String mCnt, String mVnt, int mHts) {
        this.mRut1 = mRut1;
        this.mventa = mventa;
        this.mMeta = mMeta;
        this.mV3m = mV3m;
        this.mRut2 = mRut2;
        this.mArt = mArt;
        this.mDic = mDic;
        this.mClf = mClf;
        this.mCnt = mCnt;
        this.mVnt = mVnt;
        this.mHts = mHts;
    }

    public MvtsArticulos(){}

    public String getmV3m() {
        return mV3m;
    }

    public void setmV3m(String mV3m) {
        this.mV3m = mV3m;
    }

    public String getmRut1() {
        return mRut1;
    }

    public void setmRut1(String mRut1) {
        this.mRut1 = mRut1;
    }

    public String getMventa() {
        return mventa;
    }

    public void setMventa(String mventa) {
        this.mventa = mventa;
    }

    public String getmMeta() {
        return mMeta;
    }

    public void setmMeta(String mMeta) {
        this.mMeta = mMeta;
    }

    public String getmRut2() {
        return mRut2;
    }

    public void setmRut2(String mRut2) {
        this.mRut2 = mRut2;
    }

    public String getmArt() {
        return mArt;
    }

    public void setmArt(String mArt) {
        this.mArt = mArt;
    }

    public String getmDic() {
        return mDic;
    }

    public void setmDic(String mDic) {
        this.mDic = mDic;
    }

    public String getmClf() {
        return mClf;
    }

    public void setmClf(String mClf) {
        this.mClf = mClf;
    }

    public String getmCnt() {
        return mCnt;
    }

    public void setmCnt(String mCnt) {
        this.mCnt = mCnt;
    }

    public String getmVnt() {
        return mVnt;
    }

    public void setmVnt(String mVnt) {
        this.mVnt = mVnt;
    }

    public int getmHts() {
        return mHts;
    }

    public void setmHts(int mHts) {
        this.mHts = mHts;
    }
}
