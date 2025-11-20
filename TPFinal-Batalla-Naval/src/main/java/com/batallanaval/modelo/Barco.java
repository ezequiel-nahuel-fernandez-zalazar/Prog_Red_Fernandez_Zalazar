package com.batallanaval.modelo;

import java.io.Serializable;

public class Barco implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String nombre;
    private final int longitud;
    private int impactos;

    public Barco(String nombre, int longitud) {
        this.nombre = nombre;
        this.longitud = longitud;
        this.impactos = 0;
    }

    public void recibirImpacto() {
        this.impactos++;
    }

    public boolean estaHundido() {
        return impactos >= longitud;
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getLongitud() { return longitud; }
    public int getImpactos() { return impactos; }
}