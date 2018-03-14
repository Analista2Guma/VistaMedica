package com.guma.desarrollo.gvm.MODEL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.POJO.Articulo;
import com.guma.desarrollo.gvm.POJO.Lotes;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Lotes_model {

    private static final String TAG = "ArticulosActivity";
    public static void Save(Context context, ArrayList<Lotes> ARTI){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();
            if (ARTI.size()>0){
                SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), context,"DELETE FROM Lotes");
                for(int i=0;i<ARTI.size();i++){
                    Lotes a = ARTI.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("mArt" , a.getmArt());
                    contentValues.put("mLot" , a.getmLot());
                    contentValues.put("mFvc" , a.getmFvc());
                    contentValues.put("mCds" , a.getmCds());
                    myDataBase.insert("Lotes", null, contentValues );
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
    public static List<Lotes> get(String basedir, Context context,String Articulos) {
        List<Lotes> lst = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.query(true, "Lotes", null, "mArt"+ "=?", new String[] { Articulos }, null, null, null, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    Lotes tmp = new Lotes();
                    tmp.setmArt(cursor.getString(cursor.getColumnIndex("mArt")));
                    tmp.setmLot(cursor.getString(cursor.getColumnIndex("mLot")));
                    tmp.setmFvc(cursor.getString(cursor.getColumnIndex("mFvc")));
                    tmp.setmCds(cursor.getString(cursor.getColumnIndex("mCds")));

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
