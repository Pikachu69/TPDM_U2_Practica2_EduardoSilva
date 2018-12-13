package mx.edu.ittepic.tpdm_u2_practica2_eduardosilva;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDSeguros extends SQLiteOpenHelper {

    public BDSeguros(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("CREATE TABLE DUEÑO(ID VARCHAR(20) PRIMARY KEY NOT NULL, " +
                "NOMBRE VARCHAR(500),DOMICILIO VARCHAR(500), TELEFONO VARCHAR(500))");

        bd.execSQL("CREATE TABLE POLIZA(IDCOCHE INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "MODELO VARCHAR(60),MARCA VARCHAR(200),AÑO INTEGER,FECHAINICIO DATE,PRECIO FLOAT, " +
                "TIPOPOLIZA VARCHAR(200), IDDUEÑO VARCHAR(20), FOREIGN KEY(IDDUEÑO) REFERENCES DUEÑO(ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
