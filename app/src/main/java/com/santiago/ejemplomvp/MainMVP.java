package com.santiago.ejemplomvp;

import com.santiago.ejemplomvp.modelo.Nota;

/**
 * Agregados de todas las operaciones de comunicación entre MVP capa de patrón:
 * Modelo, Vista y Presentador
 */
public interface MainMVP {


    /**
     * Ver métodos obligatorios. Disponible a Presentador
     *     Prensantador -> Vista
     */
    interface RequiredViewOps{

        void showToast(String msg);

        void showAlert(String msg);
    }


    /**
     * Las operaciones que se ofrecen desde Presentador para la vista
     *      Vista -> Prensentador
     */
    interface PresenterOps{

        void onConfigurationChanged(RequiredViewOps view);

        void onDestroy(boolean isChangingConfig);

        void newNota(String textoNota , String textTitulo);

        void deletaNota(Nota nota);

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
