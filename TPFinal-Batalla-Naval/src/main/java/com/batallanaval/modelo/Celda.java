package com.batallanaval.modelo;

import java.io.Serializable;

public class Celda implements Serializable {
    private static final long serialVersionUID = 1L;
    private TipoCelda tipo;
    private boolean disparada;
    private String nombreBarco; 

    public Celda() {
        this.tipo = TipoCelda.AGUA;
        this.disparada = false;
        this.nombreBarco = null;
    }

    // Getters
    public TipoCelda getTipo() { return tipo; }
    public boolean isDisparada() { return disparada; }
    public String getNombreBarco() { return nombreBarco; }

    // Setters
    public void setTipo(TipoCelda tipo) { this.tipo = tipo; }
    public void setNombreBarco(String nombreBarco) { this.nombreBarco = nombreBarco; }
    public void setDisparada(boolean disparada) { this.disparada = disparada; }
}