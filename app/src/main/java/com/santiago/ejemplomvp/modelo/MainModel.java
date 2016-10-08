package com.santiago.ejemplomvp.modelo;

import android.database.SQLException;
import android.os.AsyncTask;

import com.santiago.ejemplomvp.MainMVP;
import com.santiago.ejemplomvp.sqlite.ServiciosNota;

/**
 * Created by santiago on 6/10/16.
 */
public class MainModel
                implements MainMVP.ModelOps{

    // Presenter reference
    private MainMVP.RequiredPresenterOps mPresenter;

    //Servicios de nota con SQlite
    private ServiciosNota  serviciosNota;

    public MainModel(MainMVP.RequiredPresenterOps mPresenter, ServiciosNota serviciosNota) {
        this.mPresenter =  mPresenter;
        this.serviciosNota= serviciosNota;
    }


    // Insertar nota en la DB
    @Override
    public void insertNota(Nota nota) {
        // La lógica de negocios de datps
        try {


            new AsyncTask<Nota, Void, Nota>() {

                @Override
                protected void onPreExecute() {
                    mPresenter.onProgressDialog("Guardano nota","Espere por favor ...");
                    super.onPreExecute();
                }


                @Override
                protected Nota doInBackground( final Nota... notas) {

                    try {
                        Thread.sleep(2000);
                    }catch(Exception e){
                        return null;
                    }
                    serviciosNota.insertarNota(notas[0]);

                    return notas[0];
                }


                @Override
                protected void onPostExecute(Nota nota) {

                    mPresenter.onFinishProgressDialog();
                    mPresenter.onNotaInserida(nota);

                }

            }.execute(nota);





        }catch(SQLException e){
            mPresenter.onFinishProgressDialog();
            mPresenter.onError( e.getMessage() );
        }


    }

    // Remueve una nota del la DB
    @Override
    public void removeNota(Nota nota) {
        // La lógica de negocio de los datos
        // ...
        mPresenter.onNotaRemovida(nota);
    }

    /**
     * Enviado desde {@link com.santiago.ejemplomvp.prensentador.MainPresenter#onDestroy(boolean)}
     * En caso de stop/kill operaciones que puedan estar en funcionamiento matar
     * y ya no es necesarios
     */
    @Override
    public void onDestroy() {
        // destruir la acciones
    }
}
