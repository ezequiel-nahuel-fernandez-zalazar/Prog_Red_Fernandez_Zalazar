package Comun.mensajes;

public class MensajeResultadoDisparo extends Mensaje {
    private static final long serialVersionUID = 1L;
    
    public enum ResultadoDisparo {
        AGUA,
        IMPACTO,
        HUNDIDO
    }
    
    private int fila;
    private int columna;
    private ResultadoDisparo resultado;
    private String nombreBarcoHundido; 
    private boolean disparoPropio; 
    
    public MensajeResultadoDisparo(int fila, int columna, ResultadoDisparo resultado, 
                                    boolean disparoPropio) {
        super(TipoMensaje.RESULTADO_DISPARO);
        this.fila = fila;
        this.columna = columna;
        this.resultado = resultado;
        this.disparoPropio = disparoPropio;
    }
    
    public MensajeResultadoDisparo(int fila, int columna, ResultadoDisparo resultado, 
                                    String nombreBarcoHundido, boolean disparoPropio) {
        this(fila, columna, resultado, disparoPropio);
        this.nombreBarcoHundido = nombreBarcoHundido;
    }
    
    public int getFila() {
        return fila;
    }
    
    public int getColumna() {
        return columna;
    }
    
    public ResultadoDisparo getResultado() {
        return resultado;
    }
    
    public String getNombreBarcoHundido() {
        return nombreBarcoHundido;
    }
    
    public boolean isDisparoPropio() {
        return disparoPropio;
    }
}