package com.santiago.ejemplomvp.vista;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.santiago.ejemplomvp.MainMVP;
import com.santiago.ejemplomvp.R;
import com.santiago.ejemplomvp.StateMaintainer;
import com.santiago.ejemplomvp.prensentador.MainPresenter;

public class MainActivity extends AppCompatActivity
                                implements MainMVP.RequiredViewOps {


    /**
     * Controles de la vista
     */
    private EditText editTextNota;

    private EditText editTextTitulo;


    protected final String TAG = getClass().getSimpleName();

    // Responsable de mantener el estado del objeto
    //durante la configuración cambiante
    private final StateMaintainer mStateMaintainer =
            new StateMaintainer( this.getFragmentManager(), TAG );

    // operaciones presentador
    private MainMVP.PresenterOps mPresenter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startMVPOps();
        setContentView(R.layout.activity_main);


        editTextNota = (EditText) findViewById(R.id.editTextNota);

        editTextTitulo = (EditText) findViewById(R.id.editTextTitulo);

    }


    /**
     * Inicializar y reiniciar el presentador.
     * Este método debe ser llamado después de {@link android.app.Activity#onCreate(Bundle)}
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void startMVPOps() {
        try {
            if ( mStateMaintainer.firstTimeIn() ) {
                Log.d(TAG, "onCreate() se llama por primera vez");
                initialize(this);
            } else {
                Log.d(TAG, "onCreate() llamado más de una vez");
                reinitialize(this);
            }
        } catch ( InstantiationException | IllegalAccessException e ) {
            Log.d(TAG, "onCreate() " + e );
            throw new RuntimeException( e );
        }
    }


    /**
     * Inicializar objetos MVP pertinentes.
     * Crea una instancia de presentador, salva a la presentadora en {@link StateMaintainer}
     */
    private void initialize( MainMVP.RequiredViewOps view )
            throws InstantiationException, IllegalAccessException{
        mPresenter = new MainPresenter(view);
        mStateMaintainer.put(MainMVP.PresenterOps.class.getSimpleName(), mPresenter);
    }


    /**
     * Se recupera Presentador Presentador e informa que se ha producido un cambio de configuración.
     * Si se ha perdido Presentador, crea una instancia
     */
    private void reinitialize( MainMVP.RequiredViewOps view)
            throws InstantiationException, IllegalAccessException {
        mPresenter = mStateMaintainer.get( MainMVP.PresenterOps.class.getSimpleName() );

        if ( mPresenter == null ) {
            Log.w(TAG, "recreando Presentador");
            initialize( view );
        } else {
            mPresenter.onConfigurationChanged( view );
        }
    }


    /**
     * Método para guardar la nota
     * @param view del R.id.buttonGuardarNota
     */
    public void guardarNota(View view)
    {
        mPresenter.newNota(editTextNota.getText().toString(),editTextTitulo.getText().toString());
    }

    // Muestra AlertDialog
    @Override
    public void showToast(String msg) {
        // Muestre alert Box
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    //Mostrarn alerDialog
    @Override
    public void showAlert(String msg) {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this); // create an alert box
        alertDialog.setTitle("Error"); // set titulo
        alertDialog.setMessage(msg); // set mensaje
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() { // definir el botón de confirmar
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
        });

        alertDialog.show(); // show the alert box
    }

}
