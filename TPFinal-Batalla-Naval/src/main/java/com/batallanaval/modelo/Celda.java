package com.batallanaval.modelo;

import java.io.Serializable;

public class Celda implements Serializable {
    private static final long serialVersionUID = 1L; 

    private TipoCelda tipo;
    private Barco barco;

    public Celda() {
        this.tipo = TipoCelda.AGUA;
        this.barco = null;
    }

    public TipoCelda getTipo() {
        return tipo;
    }

    public void setTipo(TipoCelda tipo) {
        this.tipo = tipo;
    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
        if (barco != null) {
            this.tipo = TipoCelda.BARCO;
        }
    }
}