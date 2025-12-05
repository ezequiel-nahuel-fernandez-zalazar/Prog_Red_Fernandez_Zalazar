package Comun.mensajes;

import java.io.Serializable;

public abstract class Mensaje implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public enum TipoMensaje {
        CONEXION,
        POSICION_BARCO,
        LISTO_PARA_JUGAR,
        DISPARO,
        RESULTADO_DISPARO,
        CAMBIO_TURNO,
        FIN_PARTIDA,
        ERROR
    }
    
    private TipoMensaje tipo;
    private String contenido;
    
    public Mensaje(TipoMensaje tipo) {
        this.tipo = tipo;
    }
    
    public Mensaje(TipoMensaje tipo, String contenido) {
        this.tipo = tipo;
        this.contenido = contenido;
    }
    
    public TipoMensaje getTipo() {
        return tipo;
    }
    
    public String getContenido() {
        return contenido;
    }
    
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}