package com.santiago.ejemplomvp.modelo.adaptador;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.santiago.ejemplomvp.R;
import com.santiago.ejemplomvp.modelo.Nota;

/**
 * Proyecto EjemploMVP
 * Paquete com.santiago.ejemplomvp.modelo.adaptador
 * <p>
 * Descripición del clase
 *
 * Se encargar de adapter los datos del cursor de notas a un lista de interfaces
 * @author santiago
 * @version 1.0
 *          Fecha modificación 8/10/16
 */

public class ListarNotasAdapter extends RecyclerView.Adapter<ListarNotasAdapter.ViewHolder> {


    /**
     * Contexto de donde se debe listasr
     */
    private final Context contexto;

    /**
     * Cursor de notas de Sqlite
     */
    private Cursor cursorNotas;

    private OnItemClickListener onItemClickListener;


    /**
     * Para obtener la nota selecionada por el usuario
     */
    public interface OnItemClickListener {
        void onClick(ViewHolder holder, Nota idAlquiler);
    }

    
    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        // Referencias UI
        public TextView viewTitulo;
        public TextView viewFecha;
        public TextView viewRuta;



        public ViewHolder(View v) {
            super(v);
            viewTitulo = (TextView) v.findViewById(R.id.txtTitulo);
            viewFecha = (TextView) v.findViewById(R.id.txtFecha);


            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onClick(this, obtenerIdCliente(getAdapterPosition()));
        }
    }

    private Nota obtenerIdCliente(int posicion) {
        Nota detalleNota;
        if (cursorNotas != null) {
            if (cursorNotas.moveToPosition(posicion)) {
                detalleNota =
                        new Nota(cursorNotas.getString(0),cursorNotas.getString(1),cursorNotas.getString(2));

                return detalleNota;
            }
        }

        return null;
    }


    public ListarNotasAdapter(Context contexto, OnItemClickListener onItemClickListener) {
        this.contexto = contexto;
        this.onItemClickListener = onItemClickListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notas, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursorNotas.moveToPosition(position);

        holder.viewTitulo.setText(cursorNotas.getString(0));

        holder.viewFecha.setText("Texto: "+ cursorNotas.getString(1));


        holder.viewRuta.setText("Fecha: "+ cursorNotas.getString(2));


    }

    @Override
    public int getItemCount() {
        if (cursorNotas != null)
            return cursorNotas.getCount();
        return 0;
    }


}
