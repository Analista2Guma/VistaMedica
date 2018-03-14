package com.guma.desarrollo.gvm.POJO;

/**
 * Created by maryan.espinoza on 13/03/2018.
 */

public class Facturas_puntos {
    String mFch,mClt,mFct,mPnt,mRmT;

    public Facturas_puntos(String mFch, String mClt, String mFct, String mPnt, String mRmT) {
        this.mFch = mFch;
        this.mClt = mClt;
        this.mFct = mFct;
        this.mPnt = mPnt;
        this.mRmT = mRmT;
    }
    public Facturas_puntos(){}

    public String getmFch() {
        return mFch;
    }

    public void setmFch(String mFch) {
        this.mFch = mFch;
    }

    public String getmClt() {
        return mClt;
    }

    public void setmClt(String mClt) {
        this.mClt = mClt;
    }

    public String getmFct() {
        return mFct;
    }

    public void setmFct(String mFct) {
        this.mFct = mFct;
    }

    public String getmPnt() {
        return mPnt;
    }

    public void setmPnt(String mPnt) {
        this.mPnt = mPnt;
    }

    public String getmRmT() {
        return mRmT;
    }

    public void setmRmT(String mRmT) {
        this.mRmT = mRmT;
    }
}
