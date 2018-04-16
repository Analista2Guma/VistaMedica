package com.guma.desarrollo.gvm.MODEL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.POJO.Farmacias;
import com.guma.desarrollo.gvm.POJO.Medicos;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Medicos_model {

    private static final String TAG = "Medicos_model_log";
    public static void Save(Context context, ArrayList<Medicos> ARTI,String Type){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();
            if (ARTI.size()>0){
                if (Type.equals("All")){SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), context,"DELETE FROM IdMedico");}
                Log.i("Medicos_model_log", "Correcto" );
                for(int i=0;i<ARTI.size();i++){
                    Medicos a = ARTI.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("IdMedico" , a.getmUID());
                    contentValues.put("cmp1" , a.getM01());
                    contentValues.put("cmp2" , a.getM02());
                    contentValues.put("cmp3" , a.getM03());
                    contentValues.put("cmp4" , a.getM04());
                    contentValues.put("cmp5" , a.getM05());
                    contentValues.put("cmp6" , a.getM06());
                    contentValues.put("cmp7" , a.getM07());
                    contentValues.put("cmp8" , a.getM08());
                    contentValues.put("cmp9" , a.getM09());
                    contentValues.put("cmp10" , a.getM010());
                    contentValues.put("cmp11" , a.getM011());
                    contentValues.put("cmp12" , a.getM012());
                    contentValues.put("cmp13" , a.getM013());
                    contentValues.put("cmp14" , a.getM014());

                    contentValues.put("cmp16" , a.getM016());
                    contentValues.put("cmp17" , a.getM017());
                    contentValues.put("cmp18" , a.getM018());
                    contentValues.put("cmp19" , a.getM019());
                    contentValues.put("cmp20" , a.getM020());
                    contentValues.put("cmp21" , a.getM21());
                    contentValues.put("cmp22" , a.getM22());
                    contentValues.put("cmp23" , a.getM23());
                    contentValues.put("cmp24" , a.getM24());
                    contentValues.put("cmp25" , a.getM25());
                    contentValues.put("cmp26" , a.getM26());
                    contentValues.put("cmp27" , a.getM27());
                    contentValues.put("cmp28" , a.getM28());
                    contentValues.put("cmp29" , a.getM29());
                    contentValues.put("cmp30" , a.getM30());
                    contentValues.put("cmp31" , a.getM31());
                    contentValues.put("cmp32" , a.getM32());
                    contentValues.put("Ruta" , a.getmRuta());
                    myDataBase.insert("Medicos", null, contentValues );
                }
            }
        }
        catch (Exception e) {
            Log.i(TAG, "Save: " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            if(myDataBase != null) { myDataBase.close(); }
            if(myDbHelper != null) { myDbHelper.close(); }
        }
    }
    public static void Delete(String basedir, Context context, String Row) {
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            SQLiteHelper.ExecuteSQL(basedir,context, "DELETE FROM Medicos WHERE IdMedico= '"+Row+"'");

        }
        catch (Exception e) { e.printStackTrace(); }
        finally
        {
            if(myDataBase != null) { myDataBase.close(); }
            if(myDbHelper != null) { myDbHelper.close(); }
        }
    }
    public static void Update(Context context, ArrayList<Medicos> ARTI){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();
            if (ARTI.size()>0){
                for(int i=0;i<ARTI.size();i++){
                    Medicos a = ARTI.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("IdMedico" , a.getmUID());
                    contentValues.put("cmp1" , a.getM01());
                    contentValues.put("cmp2" , a.getM02());
                    contentValues.put("cmp3" , a.getM03());
                    contentValues.put("cmp4" , a.getM04());
                    contentValues.put("cmp5" , a.getM05());
                    contentValues.put("cmp6" , a.getM06());
                    contentValues.put("cmp7" , a.getM07());
                    contentValues.put("cmp8" , a.getM08());
                    contentValues.put("cmp9" , a.getM09());
                    contentValues.put("cmp10" , a.getM010());
                    contentValues.put("cmp11" , a.getM011());
                    contentValues.put("cmp12" , a.getM012());
                    contentValues.put("cmp13" , a.getM013());
                    contentValues.put("cmp14" , a.getM014());

                    contentValues.put("cmp16" , a.getM016());
                    contentValues.put("cmp17" , a.getM017());
                    contentValues.put("cmp18" , a.getM018());
                    contentValues.put("cmp19" , a.getM019());
                    contentValues.put("cmp20" , a.getM020());
                    contentValues.put("cmp21" , a.getM21());
                    contentValues.put("cmp22" , a.getM22());
                    contentValues.put("cmp23" , a.getM23());
                    contentValues.put("cmp24" , a.getM24());
                    contentValues.put("cmp25" , a.getM25());
                    contentValues.put("cmp26" , a.getM26());
                    contentValues.put("cmp27" , a.getM27());
                    contentValues.put("cmp28" , a.getM28());
                    contentValues.put("cmp29" , a.getM29());
                    contentValues.put("cmp30" , a.getM30());
                    contentValues.put("cmp31" , a.getM31());
                    contentValues.put("cmp32" , a.getM32());

                    myDataBase.update("Medicos", contentValues, "IdMedico='"  + a.getmUID()+"'", null);
                   // myDataBase.update("Farmacias", contentValues, "idFarmacia=" ,new String[] { a.getmUID() });
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
    public static List<Medicos> get(String basedir, Context context, String Row) {
        List<Medicos> lst = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = null;

            if (Row.equals("")){
                cursor = myDataBase.query(true, "Medicos", null, null, null, null, null, null, null);
            }else{
                cursor = myDataBase.query(true, "Medicos", null, "IdMedico"+ "=?", new String[] { Row }, null, null, null, null);
            }
            if(cursor.getCount() > 0) {

                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    Medicos tmp = new Medicos();
                    tmp.setmUID(cursor.getString(cursor.getColumnIndex("IdMedico")));
                    tmp.setM01(cursor.getString(cursor.getColumnIndex("cmp1")));
                    tmp.setM02(cursor.getString(cursor.getColumnIndex("cmp2")));
                    tmp.setM03(cursor.getString(cursor.getColumnIndex("cmp3")));
                    tmp.setM04(cursor.getString(cursor.getColumnIndex("cmp4")));
                    tmp.setM05(cursor.getString(cursor.getColumnIndex("cmp5")));
                    tmp.setM06(cursor.getString(cursor.getColumnIndex("cmp6")));
                    tmp.setM07(cursor.getString(cursor.getColumnIndex("cmp7")));
                    tmp.setM08(cursor.getString(cursor.getColumnIndex("cmp8")));
                    tmp.setM09(cursor.getString(cursor.getColumnIndex("cmp9")));
                    tmp.setM010(cursor.getString(cursor.getColumnIndex("cmp10")));
                    tmp.setM011(cursor.getString(cursor.getColumnIndex("cmp11")));
                    tmp.setM012(cursor.getString(cursor.getColumnIndex("cmp12")));
                    tmp.setM013(cursor.getString(cursor.getColumnIndex("cmp13")));
                    tmp.setM014(cursor.getString(cursor.getColumnIndex("cmp14")));

                    tmp.setM016(cursor.getString(cursor.getColumnIndex("cmp16")));
                    tmp.setM017(cursor.getString(cursor.getColumnIndex("cmp17")));
                    tmp.setM018(cursor.getString(cursor.getColumnIndex("cmp18")));
                    tmp.setM019(cursor.getString(cursor.getColumnIndex("cmp19")));
                    tmp.setM020(cursor.getString(cursor.getColumnIndex("cmp20")));

                    tmp.setM21(cursor.getString(cursor.getColumnIndex("cmp21")));
                    tmp.setM22(cursor.getString(cursor.getColumnIndex("cmp22")));
                    tmp.setM23(cursor.getString(cursor.getColumnIndex("cmp23")));
                    tmp.setM24(cursor.getString(cursor.getColumnIndex("cmp24")));
                    tmp.setM25(cursor.getString(cursor.getColumnIndex("cmp25")));
                    tmp.setM26(cursor.getString(cursor.getColumnIndex("cmp26")));
                    tmp.setM27(cursor.getString(cursor.getColumnIndex("cmp27")));
                    tmp.setM28(cursor.getString(cursor.getColumnIndex("cmp28")));
                    tmp.setM29(cursor.getString(cursor.getColumnIndex("cmp29")));
                    tmp.setM30(cursor.getString(cursor.getColumnIndex("cmp30")));

                    tmp.setM31(cursor.getInt(cursor.getColumnIndex("cmp31")));
                    tmp.setM32(cursor.getInt(cursor.getColumnIndex("cmp32")));

                    tmp.setmRuta(cursor.getString(cursor.getColumnIndex("Ruta")));

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
