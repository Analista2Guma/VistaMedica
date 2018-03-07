package com.guma.desarrollo.gvm.POJO;

/**
 * Created by maryan.espinoza on 07/03/2018.
 */

public class HstItemFacturados {
    String mCcl,mArt,mDes,mCan,mVnt;

    public HstItemFacturados(String mCcl, String mArt, String mDes, String mCan, String mVnt) {
        this.mCcl = mCcl;
        this.mArt = mArt;
        this.mDes = mDes;
        this.mCan = mCan;
        this.mVnt = mVnt;
    }
    public HstItemFacturados(){}

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

    public String getmDes() {
        return mDes;
    }

    public void setmDes(String mDes) {
        this.mDes = mDes;
    }

    public String getmCan() {
        return mCan;
    }

    public void setmCan(String mCan) {
        this.mCan = mCan;
    }

    public String getmVnt() {
        return mVnt;
    }

    public void setmVnt(String mVnt) {
        this.mVnt = mVnt;
    }
}
