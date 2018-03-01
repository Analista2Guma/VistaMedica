package com.guma.desarrollo.gvm.MODEL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.POJO.MvtsArticulos;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan.espinoza on 28/02/2018.
 */

public class MvtsArticulos_model {
    private static final String TAG = "MesActualActivity";
    public static void Save(Context context, ArrayList<MvtsArticulos> ARTI){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();

            if (ARTI.size()>0){
                SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), context,"DELETE FROM MvtsTotales");
                SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), context,"DELETE FROM MvtsTotales");
                ContentValues cV = new ContentValues();
                cV.put("mRut" , ARTI.get(0).getmRut1());
                cV.put("mVnt" , ARTI.get(0).getMventa());
                cV.put("mMts" , ARTI.get(0).getmMeta());
                myDataBase.insert("MvtsTotales", null, cV );
                for(int i=1;i<ARTI.size();i++){
                    MvtsArticulos a = ARTI.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("mRut" , a.getmRut2());
                    contentValues.put("mArt" , a.getmArt());
                    contentValues.put("mDic" , a.getmDic());
                    contentValues.put("mClf" , a.getmClf());
                    contentValues.put("mCnt" , a.getmCnt());
                    contentValues.put("mVnt" , a.getmVnt());
                    contentValues.put("mHts" , a.getmHts());
                    myDataBase.insert("MvtsArticulos", null, contentValues );
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
    public static List<MvtsArticulos> get_ventas(String basedir, Context context) {
        List<MvtsArticulos> lista = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.query(true, "MvtsArticulos", null, null, null, null, null, null, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    MvtsArticulos tmp = new MvtsArticulos();
                    tmp.setmRut2(cursor.getString(cursor.getColumnIndex("mRut")));
                    tmp.setmArt(cursor.getString(cursor.getColumnIndex("mArt")));
                    tmp.setmDic(cursor.getString(cursor.getColumnIndex("mDic")));
                    tmp.setmClf(cursor.getString(cursor.getColumnIndex("mClf")));
                    tmp.setmCnt(cursor.getString(cursor.getColumnIndex("mCnt")));
                    tmp.setmVnt(cursor.getString(cursor.getColumnIndex("mVnt")));
                    tmp.setmHts(cursor.getInt(cursor.getColumnIndex("mHts")));
                    lista.add(tmp);
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
        return lista;
    }
    public static List<MvtsArticulos> get_ventas_metas(String basedir, Context context) {
        List<MvtsArticulos> lista = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.query(true, "MvtsTotales", null, null, null, null, null, null, null);

            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    MvtsArticulos tmp = new MvtsArticulos();

                    tmp.setMventa(cursor.getString(cursor.getColumnIndex("mVnt")));
                    tmp.setmMeta(cursor.getString(cursor.getColumnIndex("mMts")));
                    lista.add(tmp);
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
        return lista;
    }
}
