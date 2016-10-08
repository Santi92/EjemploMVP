package com.santiago.ejemplomvp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by santiago on 6/10/16.
 */

public class StateMaintainer {

    protected final String TAG = getClass().getSimpleName();

    private final String mStateMaintenerTag;
    private final WeakReference<FragmentManager> mFragmentManager;
    private StateMngFragment mStateMaintainerFrag;



    /**
     * Constructor
     * @param fragmentManager       referencia FragmentManager
     * @param stateMaintainerTAG    la etiqueta utilizada para insertar el fragmento de mantenedor de estado
     */
    public StateMaintainer(FragmentManager fragmentManager, String stateMaintainerTAG) {
        mFragmentManager = new WeakReference<>(fragmentManager);
        mStateMaintenerTag = stateMaintainerTAG;
    }

    /**
     * Crear el fragmento mantenedor de estado
     * @return  true: la bandera fue creada por primera vez
     *          false: Recuperar el objeto
     */
    public boolean firstTimeIn() {
        try {
            // La recuperación de la referencia
            mStateMaintainerFrag = (StateMngFragment)
                    mFragmentManager.get().findFragmentByTag(mStateMaintenerTag);

            // Creación de un nuevo fragmento retenido
            if (mStateMaintainerFrag == null) {
                Log.d(TAG, "Creating a new RetainedFragment " + mStateMaintenerTag);
                mStateMaintainerFrag = new StateMngFragment();
                mFragmentManager.get().beginTransaction()
                        .add(mStateMaintainerFrag, mStateMaintenerTag).commit();
                return true;
            } else {
                Log.d(TAG, "Returns a existent retained fragment existente " + mStateMaintenerTag);
                return false;
            }
        } catch (NullPointerException e) {
            Log.w(TAG, "Error firstTimeIn()");
            return false;
        }
    }

    /**
     * Insertar objeto que se conserva durante el cambio de configuración
     * @param key  TAG referencia de objeto
     * @param obj  Objeto de mantener
     */
    public void put(String key, Object obj) {
        mStateMaintainerFrag.put(key, obj);
    }

    /**
     * Insertar objeto que se conserva durante el cambio de configuración
     * Se usa el nombre de clase del objeto como referencia TAG
     * Debe ser utilizado sólo una vez por clase Tipo
     * @param obj  Objeto manetner
     */
    public void put(Object obj) {
        put(obj.getClass().getName(), obj);
    }

    /**
     * Recuperar objeto guardado
     * @param key   TAG referenciaa
     * @param <T>   Tipo de clase
     * @return      objeto
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key)  {
        return mStateMaintainerFrag.get(key);

    }

    /**
     * Verificar si el objeto existe
     * @param key   Obj TAG
     */
    public boolean hasKey(String key) {
        return mStateMaintainerFrag.get(key) != null;
    }


    /**
     * Guardar y gestiona objetos que muestran ser preservado
     * durante los cambios de configuración.
     */
    public static class StateMngFragment extends Fragment {

        private HashMap<String, Object> mData = new HashMap<>();

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Las subvenciones que se preservará la bandera
            setRetainInstance(true);
        }

        /**
         * Inserta un objeto
         * @param key   Referencia  TAG
         * @param obj  objeto aguardar
         */
        public void put(String key, Object obj) {
            mData.put(key, obj);
        }

        /**
         * Inserte obj usando nombre de clase como TAG
         * @param object    obj to save
         */
        public void put(Object object) {
            put(object.getClass().getName(), object);
        }

        /**
         * Recuperar obj
         * @param key   referencia TAG
         * @param <T>   Clase
         * @return      Obj salvar
         */
        @SuppressWarnings("unchecked")
        public <T> T get(String key) {
            return (T) mData.get(key);
        }
    }


}
