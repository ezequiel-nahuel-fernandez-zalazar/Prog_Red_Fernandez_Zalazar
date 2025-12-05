package Comun;

public class Celda {
    private TipoCelda tipo;
    private boolean disparada;
    private String nombreBarco;

    public Celda() {
        this.tipo = TipoCelda.AGUA;
        this.disparada = false;
        this.nombreBarco = null;
    }

    public TipoCelda getTipo() {
        return tipo;
    }
    
    public boolean isDisparada() {
        return disparada;
    }
    
    public String getNombreBarco() {
        return nombreBarco;
    }
    
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
