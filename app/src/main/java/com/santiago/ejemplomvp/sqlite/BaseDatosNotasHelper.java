package com.santiago.ejemplomvp.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Proyecto EjemploMVP
 * Paquete com.santiago.ejemplomvp.sqlite
 * <p>
 * Descripición del clase
 *
 * @author santiago
 * @version 1.0
 *          Fecha modificación 7/10/16
 */
public class BaseDatosNotasHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DATOS = "notas";

    private static final int VERSION_ACTUAL = 1;

    private final Context context;

    public   interface TablaNota {

        String NOMBRE_TABLA = "notas";
        String NOTA_ID = "nota_id";
        String TITULO = "titulo";
        String TEXTO = "texto";
        String FECHA_CREACION = "fecha_creaciòn";

    }


    /**
     * Constructor principal de la clase
     * Que nos permitira tener una instacia  a la DB de notas
     * @param context
     */
    public BaseDatosNotasHelper(Context context) {
        super(context, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);

        this.context = context;

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // ---------------------------------------------------------------
        // Crear tabla Notas
        // ---------------------------------------------------------------
        sqLiteDatabase.execSQL(String.format("CREATE TABLE %s (%s  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "%s text NOT NULL, " +
                        "%s varchar(60) NOT NULL, " +
                        "%s timestamp NOT NULL )",
                TablaNota.NOMBRE_TABLA, TablaNota.NOTA_ID,
                TablaNota.TEXTO,
                TablaNota.TITULO,
                TablaNota.FECHA_CREACION));
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
