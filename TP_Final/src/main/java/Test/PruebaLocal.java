package Test;

import Comun.*;

public class PruebaLocal {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA LOCAL DE BATALLA NAVAL ===\n");

        Tablero tablero = new Tablero();

        System.out.println("Colocando barcos...");
        tablero.colocarBarco("Portaaviones", 0, 0, 5, true);  
        tablero.colocarBarco("Acorazado", 2, 1, 4, false);    
        tablero.colocarBarco("Crucero", 5, 5, 3, true);       
        tablero.colocarBarco("Submarino", 7, 0, 3, true);     
        tablero.colocarBarco("Destructor", 9, 8, 2, false);   

        System.out.println("\nTABLERO PROPIO:");
        tablero.mostrarTablero(false);

        tablero.mostrarBarcos();

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

        System.out.println("\n\nTABLERO DESPUÉS DE DISPAROS:");
        tablero.mostrarTablero(false);

        tablero.mostrarBarcos();

        System.out.println("\n\nTABLERO ENEMIGO (barcos ocultos):");
        tablero.mostrarTablero(true);

        System.out.println("\n¿Todos los barcos caídos? " + tablero.todosLosBarcosCaidos());
    }
}