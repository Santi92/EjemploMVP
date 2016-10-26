package com.santiago.ejemplomvp.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
        /**
         * Cerramos DB
         */
        sqLiteDatabase.close();
    }


    /**
     * Obtener todas la notas registradas en la DB local
     * @return  cursor de notas
     */
    public Cursor obtenerNotasLocales(){

        /**
         * @var  Cursor de notas
         */
        Cursor cursorNotas;


        // Obtener base de datos
        final SQLiteDatabase sqLiteDatabase = this.baseDatosNotasHelper.getReadableDatabase();

        /**
         * Construimos la sentencia para selecionar todas las notas
         * insertadas
         */

        String sqlConsulta = "SELECT * FROM "+BaseDatosNotasHelper.TablaNota.NOMBRE_TABLA+" ";

        cursorNotas = sqLiteDatabase.rawQuery(sqlConsulta,null);

        /**
         * Cerramos la DB
         */
        sqLiteDatabase.close();


        return cursorNotas;
    }
}
