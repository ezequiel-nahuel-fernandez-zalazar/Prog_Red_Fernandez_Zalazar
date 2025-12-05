package Comun;

/**
 * Representa un barco en el juego
 */
public class Barco {
    // ¿Qué necesito de un barco?
    private String nombre;
    private int longitud;
    private int impactos;
    
    /**
     * Constructor del barco - Crear el barco
     * @param nombre Nombre del barco (Portaaviones, Acorazado, etc.)
     * @param longitud Cantidad de casillas que ocupa
     */
    public Barco(String nombre, int longitud) {
        this.nombre = nombre;
        this.longitud = longitud;
        this.impactos = 0;
    }
    
    /**
     * Registra un impacto en el barco
     */
    public void recibirImpacto() {
        this.impactos++;
    }
    
    /**
     * Verifica si el barco está completamente hundido
     * @return true si los impactos >= longitud
     // ¿Está hundido? Sí cuando impactos >= longitud
     */
    public boolean estaHundido() {
        return impactos >= longitud;
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }
    
    public int getLongitud() {
        return longitud;
    }
    
    public int getImpactos() {
        return impactos;
    }

}
