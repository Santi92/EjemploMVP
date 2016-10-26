package com.santiago.ejemplomvp;

import android.database.Cursor;

import com.santiago.ejemplomvp.modelo.Nota;

/**
 * Proyecto EjemploMVP
 * Paquete com.santiago.ejemplomvp
 * <p>
 * Descripición del clase
 *
 * @author santiago
 * @version 1.0
 *          Fecha modificación 10/10/16
 */

public interface ListaMVP {

    /**
     * Ver métodos obligatorios. Disponible a Presentador
     *     Prensantador -> Vista
     */
    interface RequiredViewOps{

        void setNotasCursor(Cursor notasCursor);

        void eliminarNota(Integer idNota);

        void showMensajeError(String mgs);

        void finalizarProgresDialog();

    }


    /**
     * Las operaciones que se ofrecen desde Presentador para la vista
     *      Vista -> Prensentador
     */
    interface PresenterOps{

        void onConfigurationChanged(ListaMVP.RequiredViewOps view);

        void onDestroy(boolean isChangingConfig);

        void actualizarAdapter(Cursor cursorNotas);

        void deletaNota(Integer nota);

        // Otras operaciones  para  las apliaciones que se desembuelsa  con la vista
    }

    /**
     * Las operaciones que se ofrecen desde Presentador al Modelo
     *      Modelo -> Prensentador
     */
    interface RequiredPresenterOps {

        void onNotaInserida(Nota novaNota);
        void onNotaRemovida(Nota notaRemovida);
        void onError(String errorMsg);
        void onProgressDialog(String titulo,String ms);
        void onFinishProgressDialog();

        // Cualquier otra operación de devolución Modelo -> Presentador
    }


    /**
     * operaciones con modelos ofrecen a los Presentador
     *      Presentador -> Modelo
     */
    interface ModelOps{

        void insertNota(Nota nota);

        void removeNota(Nota nota);

        void onDestroy();

        // Any other data operation
    }


}
