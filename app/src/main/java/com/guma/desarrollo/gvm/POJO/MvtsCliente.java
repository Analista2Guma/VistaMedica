package com.guma.desarrollo.gvm.POJO;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class MvtsCliente {
    String mRut,mCcl,mNam,mRuc,mVnt;
    int mHts;

    public MvtsCliente(String mRut, String mCcl, String mNam, String mRuc, String mVnt, int mHts) {
        this.mRut = mRut;
        this.mCcl = mCcl;
        this.mNam = mNam;
        this.mRuc = mRuc;
        this.mVnt = mVnt;
        this.mHts = mHts;
    }
    public MvtsCliente(){}

    public String getmRut() {
        return mRut;
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

    public String getmNam() {
        return mNam;
    }

    public void setmNam(String mNam) {
        this.mNam = mNam;
    }

    public String getmRuc() {
        return mRuc;
    }

    public void setmRuc(String mRuc) {
        this.mRuc = mRuc;
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
