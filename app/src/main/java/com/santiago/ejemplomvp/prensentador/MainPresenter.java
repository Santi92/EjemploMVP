package com.santiago.ejemplomvp.prensentador;

import android.content.Context;

import com.santiago.ejemplomvp.MainMVP;
import com.santiago.ejemplomvp.modelo.MainModel;
import com.santiago.ejemplomvp.modelo.Nota;
import com.santiago.ejemplomvp.sqlite.ServiciosNota;
import com.santiago.ejemplomvp.vista.MainActivity;

import java.lang.ref.WeakReference;

/**
 * Created by santiago on 6/10/16.
 */

public class MainPresenter
        implements MainMVP.RequiredPresenterOps, MainMVP.PresenterOps {


    /**
     * Ver capa de referencia
     */
    private WeakReference<MainMVP.RequiredViewOps> mView;

    // Referencia capa del Modelo
    private MainMVP.ModelOps mModel;

    // estado de cambio de configuración
    private boolean mIsChangingConfig;


    public MainPresenter(MainMVP.RequiredViewOps mView){

        this.mView = new WeakReference<>(mView);
        this.mModel = new MainModel(this,new ServiciosNota((Context) mView));
    }

    /**
     * Enviado desde Actividad después de los cambios de configuración
     * @param view  View reference
     */
    @Override
    public void onConfigurationChanged(MainMVP.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    /**
     * Recibe {@link MainActivity#onDestroy()} eventos
     * @param isChangingConfig  estado de cambio de config
     */
    @Override
    public void onDestroy(boolean isChangingConfig) {
        mView = null;
        mIsChangingConfig = isChangingConfig;
        if ( !isChangingConfig ) {
            mModel.onDestroy();
        }
    }

    /**
     * Llamado por la interacción del usuario a partir {@link MainActivity}
     * crea una nueva nota
     */
    @Override
    public void newNota(String textoNota,String textTitulo) {



        Nota note = new Nota();
        note.setTexto(textoNota);
        note.setTexto(textTitulo);
        note.setFecha(getDate());


        mModel.insertNota(note);
    }


    /**
     * Llamado desde {@link MainActivity},
     * Elimina una nota
     */
    @Override
    public void deletaNota(Nota nota) {

        mModel.removeNota(nota);
    }

    /**
     * Llamado desde {@link com.santiago.ejemplomvp.modelo.MainModel}
     * cuando se inserta una Nota con éxito
     */
    @Override
    public void onNotaInserida(Nota newNota) {
        mView.get().showToast("Nuevo registro añadió a " + newNota.getFecha());
    }

    /**
     * Recibe la llamada de  {@link com.santiago.ejemplomvp.modelo.MainModel}
     * Cuando se retira la nota
     */
    @Override
    public void onNotaRemovida(Nota notaRemovida) {
        mView.get().showToast("Nota removida");
    }

    /**
     * Recibe errors
     */
    @Override
    public void onError(String errorMsg) {
        mView.get().showAlert(errorMsg);
    }

    public String getDate(){
        return "2016-10-09";
    }
}
