package mx.edu.ittepic.tpdm_u2_practica2_eduardosilva;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class Poliza {
    int idcoche,year;
    String modelo, marca, tipopoliza, iddueno, fechaInicio;
    float precio;
    BDSeguros base;

    public Poliza(int id, String mdl, String mrc, int yr, String fecha, float p,String tipo, String idd){
        idcoche = id;
        modelo = mdl;
        marca = mrc;
        year = yr;
        fechaInicio = fecha;
        precio = p;
        tipopoliza = tipo;
        iddueno = idd;
    }
    public Poliza() {
    }

    public Poliza(Activity activity){
        base = new BDSeguros(activity,"SEGUROS",null,1);
    }

    public boolean insertar(Poliza poliza){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("MODELO",poliza.modelo);
            data.put("MARCA",poliza.marca);
            data.put("AÑO",poliza.year);
            data.put("FECHAINICIO",poliza.fechaInicio);
            data.put("PRECIO",poliza.precio);
            data.put("TIPOPOLIZA",poliza.tipopoliza);
            data.put("IDDUEÑO",poliza.iddueno);
            long res = tabla.insert("POLIZA","IDCOCHE",data);
            tabla.close();
            if(res<0){
                return false;
            }
        }catch (SQLiteException e){
            Log.e("ERROR: ",e.getMessage());
            return false;
        }
        return true;
    }

    public Boolean consultar(String idDueno){
        Boolean pl=false;
        try{
            SQLiteDatabase tabla = base.getReadableDatabase();
            String SQL = "SELECT * FROM POLIZA WHERE IDDUEÑO=?";
            String claves[] = {idDueno};
            Cursor c = tabla.rawQuery(SQL,claves);
            if(c.moveToFirst()){
                pl = true;
            }
            c.close();
            tabla.close();
        }catch (SQLiteException e){
            e.printStackTrace();
        }
        return pl;
    }

    public Poliza[] consulta(){
        Poliza[] resultado=null;
        try{
            SQLiteDatabase tabla = base.getReadableDatabase();
            String SQL = "SELECT * FROM POLIZA";

            Cursor c = tabla.rawQuery(SQL,null);
            if(c.moveToFirst()){
                resultado = new Poliza[c.getCount()];
                int i=0;
                do {
                    resultado[i++] = new Poliza(c.getInt(0),c.getString(1),c.getString(2)
                            ,c.getInt(3),c.getString(4),c.getFloat(5),c.getString(6)
                            ,c.getString(7));
                }while(c.moveToNext());
            }
            c.close();
            tabla.close();
        }catch (SQLiteException e){
            return null;
        }
        return resultado;
    }

    public boolean eliminar(Poliza poliza){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            String[] data = {""+poliza.idcoche};
            long res = tabla.delete("POLIZA","IDCOCHE=?",data);
            tabla.close();
            if(res==0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }

    public boolean actualizar(Poliza poliza){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("MODELO",poliza.modelo);
            data.put("MARCA",poliza.marca);
            data.put("AÑO",poliza.year);
            data.put("FECHAINICIO",poliza.fechaInicio);
            data.put("PRECIO",poliza.precio);
            data.put("TIPOPOLIZA",poliza.tipopoliza);
            data.put("IDDUEÑO",poliza.iddueno);
            String[] clave = {""+poliza.idcoche};
            long res = tabla.update("POLIZA",data,"IDCOCHE = ?",clave);
            tabla.close();
            if(res<0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }
}
