package com.guma.desarrollo.gvm.POJO;

/**
 * Created by maryan.espinoza on 13/03/2018.
 */

public class Lotes {
    String mArt,mLot,mFvc,mCds;

    public Lotes(String mArt, String mLot, String mFvc, String mCds) {
        this.mArt = mArt;
        this.mLot = mLot;
        this.mFvc = mFvc;
        this.mCds = mCds;
    }
    public Lotes(){}

    public String getmArt() {
        return mArt;
    }

    public void setmArt(String mArt) {
        this.mArt = mArt;
    }

    public String getmLot() {
        return mLot;
    }

    public void setmLot(String mLot) {
        this.mLot = mLot;
    }

    public String getmFvc() {
        return mFvc;
    }

    public void setmFvc(String mFvc) {
        this.mFvc = mFvc;
    }

    public String getmCds() {
        return mCds;
    }

    public void setmCds(String mCds) {
        this.mCds = mCds;
    }
}
