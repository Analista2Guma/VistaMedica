package com.guma.desarrollo.gvm.MODEL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.POJO.Farmacias;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Farmacias_model {

    private static final String TAG = "Farmacias_model_log";
    public static void Save(Context context, ArrayList<Farmacias> ARTI,String Type){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();
            if (ARTI.size()>0){
                if (Type.equals("All")){SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), context,"DELETE FROM Farmacias");}

                for(int i=0;i<ARTI.size();i++){
                    Farmacias a = ARTI.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("idFarmacia" , a.getmUID());
                    contentValues.put("Nombre_Farmacia" , a.getmNFR());
                    contentValues.put("Nombre_Propietario" , a.getmNPR());
                    contentValues.put("Direccion" , a.getmDIR());
                    contentValues.put("Fecha_aniversario" , a.getmFAN());
                    contentValues.put("Telf_Farmacia" , a.getmTFR());
                    contentValues.put("Celular_duenno" , a.getmTFP());
                    contentValues.put("Horario_Atencio" , a.getmHAT());
                    contentValues.put("Resp_compra" , a.getmRCP());
                    contentValues.put("Cel_Resp_Compra" , a.getmTRC());
                    contentValues.put("Cantidad_dependiente" , a.getmCDP());
                    contentValues.put("Potencia_mensual" , a.getmPCP());
                    contentValues.put("Dias_pago" , a.getmDPF());
                    contentValues.put("Responsable_vencidos" , a.getmRVC());
                    contentValues.put("Responsable_canje" , a.getmRCJ());
                    contentValues.put("Dependientes_mostrardor" , a.getmNDM());
                    contentValues.put("CheckBox01" , a.getmPPP());
                    contentValues.put("CheckBox02" , a.getmEBD());
                    contentValues.put("CheckBox03" , a.getmPIP());
                    contentValues.put("CheckBox04" , a.getmCCO());
                    contentValues.put("Ruta" , a.getRuta());
                    myDataBase.insert("Farmacias", null, contentValues );
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
    public static List<Farmacias> Delete(String basedir, Context context, String Row) {
        List<Farmacias> lst = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            SQLiteHelper.ExecuteSQL(basedir,context, "DELETE FROM Farmacias WHERE idFarmacia= '"+Row+"'");

        }
        catch (Exception e) { e.printStackTrace(); }
        finally
        {
            if(myDataBase != null) { myDataBase.close(); }
            if(myDbHelper != null) { myDbHelper.close(); }
        }
        return lst;
    }
    public static void Update(Context context, ArrayList<Farmacias> ARTI){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();
            if (ARTI.size()>0){
                for(int i=0;i<ARTI.size();i++){
                    Farmacias a = ARTI.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("Nombre_Farmacia" , a.getmNFR());
                    contentValues.put("Nombre_Propietario" , a.getmNPR());
                    contentValues.put("Direccion" , a.getmDIR());
                    contentValues.put("Fecha_aniversario" , a.getmFAN());
                    contentValues.put("Telf_Farmacia" , a.getmTFR());
                    contentValues.put("Celular_duenno" , a.getmTFP());
                    contentValues.put("Horario_Atencio" , a.getmHAT());
                    contentValues.put("Resp_compra" , a.getmRCP());
                    contentValues.put("Cel_Resp_Compra" , a.getmTRC());
                    contentValues.put("Cantidad_dependiente" , a.getmCDP());
                    contentValues.put("Potencia_mensual" , a.getmPCP());
                    contentValues.put("Dias_pago" , a.getmDPF());
                    contentValues.put("Responsable_vencidos" , a.getmRVC());
                    contentValues.put("Responsable_canje" , a.getmRCJ());
                    contentValues.put("Dependientes_mostrardor" , a.getmNDM());
                    contentValues.put("CheckBox01" , a.getmPPP());
                    contentValues.put("CheckBox02" , a.getmEBD());
                    contentValues.put("CheckBox03" , a.getmPIP());
                    contentValues.put("CheckBox04" , a.getmCCO());

                    myDataBase.update("Farmacias", contentValues, "idFarmacia='"  + a.getmUID()+"'", null);
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
    public static List<Farmacias> get(String basedir, Context context, String Row) {
        List<Farmacias> lst = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = null;

            if (Row.equals("")){
                cursor = myDataBase.query(true, "Farmacias", null, null, null, null, null, null, null);
            }else{
                cursor = myDataBase.query(true, "Farmacias", null, "idFarmacia"+ "=?", new String[] { Row }, null, null, null, null);
            }
            if(cursor.getCount() > 0) {

                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    Farmacias tmp = new Farmacias();
                    tmp.setmUID(cursor.getString(cursor.getColumnIndex("idFarmacia")));
                    tmp.setmNFR(cursor.getString(cursor.getColumnIndex("Nombre_Farmacia")));
                    tmp.setmNPR(cursor.getString(cursor.getColumnIndex("Nombre_Propietario")));
                    tmp.setmDIR(cursor.getString(cursor.getColumnIndex("Direccion")));
                    tmp.setmFAN(cursor.getString(cursor.getColumnIndex("Fecha_aniversario")));
                    tmp.setmTFR(cursor.getString(cursor.getColumnIndex("Telf_Farmacia")));
                    tmp.setmTFP(cursor.getString(cursor.getColumnIndex("Celular_duenno")));
                    tmp.setmHAT(cursor.getString(cursor.getColumnIndex("Horario_Atencio")));
                    tmp.setmRCP(cursor.getString(cursor.getColumnIndex("Resp_compra")));
                    tmp.setmTRC(cursor.getString(cursor.getColumnIndex("Cel_Resp_Compra")));
                    tmp.setmCDP(cursor.getString(cursor.getColumnIndex("Cantidad_dependiente")));
                    tmp.setmPCP(cursor.getString(cursor.getColumnIndex("Potencia_mensual")));
                    tmp.setmDPF(cursor.getString(cursor.getColumnIndex("Dias_pago")));
                    tmp.setmRVC(cursor.getString(cursor.getColumnIndex("Responsable_vencidos")));
                    tmp.setmRCJ(cursor.getString(cursor.getColumnIndex("Responsable_canje")));
                    tmp.setmNDM(cursor.getString(cursor.getColumnIndex("Dependientes_mostrardor")));
                    tmp.setmPPP(cursor.getInt(cursor.getColumnIndex("CheckBox01")));
                    tmp.setmEBD(cursor.getInt(cursor.getColumnIndex("CheckBox02")));
                    tmp.setmPIP(cursor.getInt(cursor.getColumnIndex("CheckBox03")));
                    tmp.setmCCO(cursor.getInt(cursor.getColumnIndex("CheckBox04")));
                    tmp.setRuta(cursor.getString(cursor.getColumnIndex("Ruta")));

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
