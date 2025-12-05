package Comun.mensajes;

/**
 * Mensaje para enviar un disparo del cliente al servidor
 */
public class MensajeDisparo extends Mensaje {
    private static final long serialVersionUID = 1L;
    
    private int fila;
    private int columna;
    
    public MensajeDisparo(int fila, int columna) {
        super(TipoMensaje.DISPARO);
        this.fila = fila;
        this.columna = columna;
    }
    
    public int getFila() {
        return fila;
    }
    
    public int getColumna() {
        return columna;
    }
}