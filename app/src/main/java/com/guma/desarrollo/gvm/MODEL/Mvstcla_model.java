package com.guma.desarrollo.gvm.MODEL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.POJO.MvstCLA;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Mvstcla_model {
    private static final String TAG = "MesActualActivity";
    public static void Save(Context context, ArrayList<MvstCLA> ARTI){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();


            if (ARTI.size()>0){

                SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), context,"DELETE FROM MvstCLA");
                for(int i=0;i<ARTI.size();i++){
                    MvstCLA a = ARTI.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("mRut" , a.getmRut());
                    contentValues.put("mCcl" , a.getmCcl());
                    contentValues.put("mNcl" , a.getmNcl());
                    contentValues.put("mArt" , a.getmArt());
                    contentValues.put("mDec" , a.getmDec());
                    contentValues.put("mCnt" , a.getmCnt());
                    contentValues.put("mClf" , a.getmClf());
                    contentValues.put("mVnt" , a.getmVnt());
                    contentValues.put("mDia" , a.getmDia());
                    myDataBase.insert("MvstCLA", null, contentValues );
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if(myDataBase != null) { myDataBase.close(); }
            if(myDbHelper != null) { myDbHelper.close(); }
        }
    }
    public static List<MvstCLA>get(String basedir, Context context) {
        List<MvstCLA> lst = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.query(true, "MvstCLA", null, null, null, null, null, null, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    MvstCLA tmp = new MvstCLA();
                    tmp.setmRut(cursor.getString(cursor.getColumnIndex("mRut")));
                    tmp.setmCcl(cursor.getString(cursor.getColumnIndex("mCcl")));
                    tmp.setmNcl(cursor.getString(cursor.getColumnIndex("mNcl")));
                    tmp.setmArt(cursor.getString(cursor.getColumnIndex("mArt")));
                    tmp.setmDec(cursor.getString(cursor.getColumnIndex("mDec")));
                    tmp.setmCnt(cursor.getString(cursor.getColumnIndex("mCnt")));
                    tmp.setmClf(cursor.getString(cursor.getColumnIndex("mClf")));
                    tmp.setmVnt(cursor.getString(cursor.getColumnIndex("mVnt")));
                    tmp.setmDia(cursor.getString(cursor.getColumnIndex("mDia")));
                    lst.add(tmp);
                    cursor.moveToNext();
                }
            }
        }
        catch (Exception e) { e.printStackTrace(); }
        finally
        {
            if(myDataBase != null) { myDataBase.close(); }
            if(myDbHelper != null) { myDbHelper.close(); }
        }
        return lst;
    }
}
