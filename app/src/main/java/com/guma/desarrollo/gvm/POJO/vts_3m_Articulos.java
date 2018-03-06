package com.guma.desarrollo.gvm.POJO;

/**
 * Created by maryan.espinoza on 02/03/2018.
 */

public class vts_3m_Articulos {
    String mRut2,mArt,mDec,mClf,mCnt,mVnt;
    int mHts;

    public vts_3m_Articulos(String mRut2, String mArt, String mDic, String mClf, String mCnt, String mVnt, int mHts) {
        this.mRut2 = mRut2;
        this.mArt = mArt;
        this.mDec = mDic;
        this.mClf = mClf;
        this.mCnt = mCnt;
        this.mVnt = mVnt;
        this.mHts = mHts;
    }
    public vts_3m_Articulos(){}

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

    public String getmDec() {
        return mDec;
    }

    public void setmDec(String mDic) {
        this.mDec = mDic;
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
