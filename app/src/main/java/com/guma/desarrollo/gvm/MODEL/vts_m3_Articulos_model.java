package com.guma.desarrollo.gvm.MODEL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.POJO.MvtsArticulos;
import com.guma.desarrollo.gvm.POJO.vts_3m_Articulos;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan.espinoza on 28/02/2018.
 */

public class vts_m3_Articulos_model {
    private static final String TAG = "UltimosMesesActivity";
    public static void Save(Context context, ArrayList<vts_3m_Articulos> ARTI){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();

            if (ARTI.size()>0){
                SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), context,"DELETE FROM vts_m3_Articulos");
                for(int i=0;i<ARTI.size();i++){
                    vts_3m_Articulos a = ARTI.get(i);

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("mRut" , a.getmRut2());
                    contentValues.put("mArt" , a.getmArt());
                    contentValues.put("mDec" , a.getmDec());
                    contentValues.put("mClf" , a.getmClf());
                    contentValues.put("mCnt" , a.getmCnt());
                    contentValues.put("mVnt" , a.getmVnt());
                    contentValues.put("mHts" , a.getmHts());
                    myDataBase.insert("vts_m3_Articulos", null, contentValues );
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
    public static List<vts_3m_Articulos> get_ventas(String basedir, Context context, String CLS) {
        List<vts_3m_Articulos> lista = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        Cursor cursor;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();

            // TODO: 02/03/2018 pendiente en filtrar por cliente

            cursor = myDataBase.query(true, "vts_m3_Articulos", null, null, null, null, null, null, null);
           /* if (CLS.equals("")){
                cursor = myDataBase.query(true, "vts_m3_Articulos", null, null, null, null, null, null, null);
            }else{
                //cursor = myDataBase.query(true, "vts_m3_Articulos", null, null, null, null, null, null, null);
                cursor = myDataBase.query(true, "vts_m3_Articulos", null, "mCcl"+ "=?", new String[] { CLS }, null, null, null, null);
            }*/
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    vts_3m_Articulos tmp = new vts_3m_Articulos();

                    tmp.setmRut2(cursor.getString(cursor.getColumnIndex("mRut")));
                    tmp.setmArt(cursor.getString(cursor.getColumnIndex("mArt")));
                    tmp.setmDec(cursor.getString(cursor.getColumnIndex("mDec")));
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
    public static List<vts_3m_Articulos> get_hstItemFacturados(String basedir, Context context, String CLS) {
        List<vts_3m_Articulos> lista = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        Cursor cursor;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();

            // TODO: 02/03/2018 pendiente en filtrar por cliente

            cursor = myDataBase.query(true, "vts_m3_Articulos", null, null, null, null, null, null, null);
           /* if (CLS.equals("")){
                cursor = myDataBase.query(true, "vts_m3_Articulos", null, null, null, null, null, null, null);
            }else{
                //cursor = myDataBase.query(true, "vts_m3_Articulos", null, null, null, null, null, null, null);
                cursor = myDataBase.query(true, "vts_m3_Articulos", null, "mCcl"+ "=?", new String[] { CLS }, null, null, null, null);
            }*/
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    vts_3m_Articulos tmp = new vts_3m_Articulos();

                    tmp.setmRut2(cursor.getString(cursor.getColumnIndex("mRut")));
                    tmp.setmArt(cursor.getString(cursor.getColumnIndex("mArt")));
                    tmp.setmDec(cursor.getString(cursor.getColumnIndex("mDec")));
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

}
