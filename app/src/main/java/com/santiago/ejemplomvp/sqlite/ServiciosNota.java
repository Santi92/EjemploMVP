package com.santiago.ejemplomvp.sqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.santiago.ejemplomvp.modelo.Nota;

/**
 * Proyecto EjemploMVP
 * Paquete com.santiago.ejemplomvp.sqlite
 * <p>
 * Descripición del clase
 *
 * Se encargar de todos los servicos de nota respecto  a la base de datos SQLITE
 *
 * @author santiago
 * @version 1.0
 *          Fecha modificación 7/10/16
 */
public class ServiciosNota {

    private BaseDatosNotasHelper baseDatosNotasHelper;

    public  ServiciosNota (Context context ){

        this.baseDatosNotasHelper = new BaseDatosNotasHelper(context);

    }

    /**
     * Inserta nota nueva la base de datos
     * @param notaNueva      nota ha insertar
     * @throws SQLException  error en la base de datos
     */
    public void insertarNota(Nota notaNueva) throws SQLException{

        // Obtener base de datos
        final SQLiteDatabase sqLiteDatabase = this.baseDatosNotasHelper.getReadableDatabase();

        Log.d("Log id",""+notaNueva.getTexto());
        /**
         * Armar query de inserciòn
         */
        String insert = "INSERT  INTO "+BaseDatosNotasHelper.TablaNota.NOMBRE_TABLA+" ("+BaseDatosNotasHelper.TablaNota.TITULO+", " +
                BaseDatosNotasHelper.TablaNota.TEXTO+", "+
                ""+BaseDatosNotasHelper.TablaNota.FECHA_CREACION+")" +
                " VALUES ('"+notaNueva.getTexto()+"'," +
                " '"+notaNueva.getTitulo() + "',"+
                "'" + notaNueva.getFecha()+"');";

        sqLiteDatabase.execSQL(insert);

    }

}
