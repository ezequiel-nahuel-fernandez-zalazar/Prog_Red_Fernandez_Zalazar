package Comun.mensajes;

/**
 * Mensaje para enviar la posición de un barco al servidor
 */
public class MensajePosicionBarco extends Mensaje {
    private static final long serialVersionUID = 1L;
    
    private String nombreBarco;
    private int fila;
    private int columna;
    private int longitud;
    private boolean horizontal;
    
    public MensajePosicionBarco(String nombreBarco, int fila, int columna, 
                                 int longitud, boolean horizontal) {
        super(TipoMensaje.POSICION_BARCO);
        this.nombreBarco = nombreBarco;
        this.fila = fila;
        this.columna = columna;
        this.longitud = longitud;
        this.horizontal = horizontal;
    }
    
    // Getters
    public String getNombreBarco() {
        return nombreBarco;
    }
    
    public int getFila() {
        return fila;
    }
    
    public int getColumna() {
        return columna;
    }
    
    public int getLongitud() {
        return longitud;
    }
    
    public boolean isHorizontal() {
        return horizontal;
    }
}