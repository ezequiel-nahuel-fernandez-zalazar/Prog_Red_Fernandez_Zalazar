package Comun;

/**
 * Representa una celda individual del tablero
 */
public class Celda {
    // 1. Atributos - ¿Qué necesito saber de una celda?
    private TipoCelda tipo;
    private boolean disparada;
    private String nombreBarco;
    
    /**
     * Constructor: inicializa una celda vacía con agua
     */
    public Celda() {
        this.tipo = TipoCelda.AGUA;
        this.disparada = false;
        this.nombreBarco = null;
    }
    
    // Getters Para leer los datos
    public TipoCelda getTipo() {
        return tipo;
    }
    
    public boolean isDisparada() {
        return disparada;
    }
    
    public String getNombreBarco() {
        return nombreBarco;
    }
    
    // Setters Para modificar los datos
    public void setTipo(TipoCelda tipo) {
        this.tipo = tipo;
    }
    
    public void setDisparada(boolean disparada) {
        this.disparada = disparada;
    }
    
    public void setNombreBarco(String nombreBarco) {
        this.nombreBarco = nombreBarco;
    }

}
