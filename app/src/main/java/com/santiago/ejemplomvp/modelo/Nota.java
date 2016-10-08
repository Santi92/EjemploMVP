package com.santiago.ejemplomvp.modelo;

import android.database.Cursor;

/**
 * Created by santiago on 6/10/16.
 */

public class Nota {

    private String texto;

    private String fecha;

    private String titulo;


    public Nota(String texto, String fecha, String titulo) {

        this.texto = texto;
        this.fecha = fecha;
        this.titulo = titulo;

    }


    public Nota() {

    }


    public String getTexto() {

        return texto;
    }


    public String getFecha() {

        return fecha;
    }

    public String getTitulo() {

        return titulo;
    }

    public void setTexto(String texto) {

        this.texto = texto;
    }


    public void setFecha(String fecha) {

        this.fecha = fecha;
    }


    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    // Create a MunicipioGson object from a cursor
    public  Nota fromCursor(Cursor cursorNotas) {
        String titulo = cursorNotas.getString(0);
        String texto = cursorNotas.getString(1);
        String fecha = cursorNotas.getString(2);


        return new Nota(titulo,texto,fecha);
    }
}
