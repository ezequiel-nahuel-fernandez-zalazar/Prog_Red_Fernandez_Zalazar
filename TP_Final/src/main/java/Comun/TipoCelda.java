package Comun;

/**
 * Enum que representa los posibles estados de una celda en el tablero
// Pregúntate: ¿Qué estados puede tener una celda?
// Respuesta: Agua, Barco, Impacto (tocado), Fallo (agua disparada)
 */
public enum TipoCelda {
    AGUA,      // Celda sin barco
    BARCO,     // Celda con parte de un barco
    IMPACTO,   // Celda con barco que fue disparada
    FALLO      // Celda de agua que fue disparada

}
