package com.guma.desarrollo.gvm.MODEL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.POJO.Cliente;
import com.guma.desarrollo.gvm.POJO.HstItemFacturados;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class HstItemFacturado_model {

    private static final String TAG = "";
    public static void Save(Context context, ArrayList<HstItemFacturados> ARTI){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();
            if (ARTI.size()>0){
                SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), context,"DELETE FROM HstItemFacturados");
                for(int i=0;i<ARTI.size();i++){
                    HstItemFacturados a = ARTI.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("mCcl" , a.getmCcl());
                    contentValues.put("mArt" , a.getmArt());
                    contentValues.put("mDes" , a.getmDes());
                    contentValues.put("mCan" , a.getmCan());
                    contentValues.put("mVnt" , a.getmVnt());
                    myDataBase.insert("HstItemFacturados", null, contentValues );
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
    public static List<HstItemFacturados> get(String basedir, Context context,String Cls) {
        List<HstItemFacturados> lst = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            //Cursor cursor = myDataBase.query(true, "HstItemFacturados", null, null, null, null, null, null, null);
            Cursor cursor = myDataBase.query(true, "HstItemFacturados", null, "mCcl"+ "=?", new String[] { Cls }, null, null, null, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    HstItemFacturados tmp = new HstItemFacturados();
                    tmp.setmArt(cursor.getString(cursor.getColumnIndex("mArt")));
                    tmp.setmDes(cursor.getString(cursor.getColumnIndex("mDes")));
                    tmp.setmCan(cursor.getString(cursor.getColumnIndex("mCan")));
                    tmp.setmVnt(cursor.getString(cursor.getColumnIndex("mVnt")));
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
