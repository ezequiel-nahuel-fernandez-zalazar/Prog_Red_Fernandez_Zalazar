package com.batallanaval.modelo;

import java.io.Serializable;

public class Barco implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String nombre;
    private final int longitud;
    private int impactosRecibidos;

    public Barco(String nombre, int longitud) {
        this.nombre = nombre;
        this.longitud = longitud;
        this.impactosRecibidos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getLongitud() {
        return longitud;
    }

    public int getImpactosRecibidos() {
        return impactosRecibidos;
    }

    public void recibirImpacto() {
        this.impactosRecibidos++;
    }

    public boolean estaHundido() {
        return impactosRecibidos >= longitud;
    }
}