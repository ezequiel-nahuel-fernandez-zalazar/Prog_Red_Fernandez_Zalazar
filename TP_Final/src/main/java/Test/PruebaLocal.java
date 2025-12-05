package Test;

import Comun.*;

/**
 * Clase de prueba para verificar el funcionamiento del tablero
 * SIN red, solo para testear las clases básicas
 */
public class PruebaLocal {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA LOCAL DE BATALLA NAVAL ===\n");
        
        // Crear tablero
        Tablero tablero = new Tablero();
        
        // Colocar barcos según la consigna:
        // - 1 Portaaviones (5 casillas)
        // - 1 Acorazado (4 casillas)
        // - 1 Crucero (3 casillas)
        // - 1 Submarino (3 casillas)
        // - 1 Destructor (2 casillas)
        
        System.out.println("Colocando barcos...");
        tablero.colocarBarco("Portaaviones", 0, 0, 5, true);  // Horizontal
        tablero.colocarBarco("Acorazado", 2, 1, 4, false);    // Vertical
        tablero.colocarBarco("Crucero", 5, 5, 3, true);       // Horizontal
        tablero.colocarBarco("Submarino", 7, 0, 3, true);     // Horizontal
        tablero.colocarBarco("Destructor", 9, 8, 2, false);   // Vertical
        
        // Mostrar tablero propio (se ven los barcos)
        System.out.println("\nTABLERO PROPIO:");
        tablero.mostrarTablero(false);
        
        // Mostrar estado de barcos
        tablero.mostrarBarcos();
        
        // Simular algunos disparos
        System.out.println("\n\n=== SIMULANDO DISPAROS ===");
        
        System.out.println("\nDisparo en (0,0):");
        System.out.println(tablero.disparar(0, 0));
        
        System.out.println("\nDisparo en (0,1):");
        System.out.println(tablero.disparar(0, 1));
        
        System.out.println("\nDisparo en (3,3) - agua:");
        System.out.println(tablero.disparar(3, 3));
        
        System.out.println("\nDisparo en (0,2):");
        System.out.println(tablero.disparar(0, 2));
        
        System.out.println("\nDisparo en (0,3):");
        System.out.println(tablero.disparar(0, 3));
        
        System.out.println("\nDisparo en (0,4) - debería hundir el Portaaviones:");
        System.out.println(tablero.disparar(0, 4));
        
        // Mostrar tablero después de disparos
        System.out.println("\n\nTABLERO DESPUÉS DE DISPAROS:");
        tablero.mostrarTablero(false);
        
        // Mostrar estado actualizado de barcos
        tablero.mostrarBarcos();
        
        // Mostrar cómo se vería el tablero del enemigo (barcos ocultos)
        System.out.println("\n\nTABLERO ENEMIGO (barcos ocultos):");
        tablero.mostrarTablero(true);
        
        // Verificar condición de victoria
        System.out.println("\n¿Todos los barcos caídos? " + tablero.todosLosBarcosCaidos());
    }
}