package com.guma.desarrollo.gvm.MODEL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.POJO.Articulo;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Articulos_model {

    private static final String TAG = "ArticulosActivity";
    public static void Save(Context context, ArrayList<Articulo> ARTI){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();
            if (ARTI.size()>0){
                SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), context,"DELETE FROM Articulos");
                for(int i=0;i<ARTI.size();i++){
                    Articulo a = ARTI.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("mCod" , a.getmCod());
                    contentValues.put("mNam" , a.getmNam());
                    contentValues.put("mExi" , a.getmExi());
                    contentValues.put("mLab" , a.getmLab());
                    contentValues.put("mUnd" , a.getmUnd());
                    contentValues.put("mPts" , a.getmPts());
                    contentValues.put("mRgl" , a.getmRgl());
                    myDataBase.insert("Articulos", null, contentValues );
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
    public static List<Articulo> get(String basedir, Context context) {
        List<Articulo> lst = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.query(true, "Articulos", null, null, null, null, null, null, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    Articulo tmp = new Articulo();
                    tmp.setmCod(cursor.getString(cursor.getColumnIndex("mCod")));
                    tmp.setmNam(cursor.getString(cursor.getColumnIndex("mNam")));
                    tmp.setmExi(cursor.getString(cursor.getColumnIndex("mExi")));
                    tmp.setmLab(cursor.getString(cursor.getColumnIndex("mLab")));
                    tmp.setmUnd(cursor.getString(cursor.getColumnIndex("mUnd")));
                    tmp.setmPts(cursor.getString(cursor.getColumnIndex("mPts")));
                    tmp.setmRgl(cursor.getString(cursor.getColumnIndex("mRgl")));
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
