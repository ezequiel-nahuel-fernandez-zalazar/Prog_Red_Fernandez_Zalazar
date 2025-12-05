package Comun;

import java.util.ArrayList;

public class Tablero {
    private ArrayList<ArrayList<Celda>> tablero;
    private ArrayList<Barco> barcos;
    private static final int TAMANIO = 10;
    
    public Tablero() {
        this.barcos = new ArrayList<>();
        inicializarTablero();
    }
    
    private void inicializarTablero() {
        tablero = new ArrayList<>();
        for (int i = 0; i < TAMANIO; i++) {
            ArrayList<Celda> fila = new ArrayList<>();
            for (int j = 0; j < TAMANIO; j++) {
                fila.add(new Celda());
            }
            tablero.add(fila);
        }
    }

    public boolean colocarBarco(String nombre, int fila, int col, int longitud, boolean horizontal) {
        if (fila < 0 || fila >= TAMANIO || col < 0 || col >= TAMANIO) {
            return false;
        }
        
        if (horizontal) {
            if (col + longitud > TAMANIO) {
                return false;
            }
        } else {
            if (fila + longitud > TAMANIO) {
                return false;
            }
        }
        
        for (int i = 0; i < longitud; i++) {
            int filaActual = horizontal ? fila : fila + i;
            int colActual = horizontal ? col + i : col;
            
            if (tablero.get(filaActual).get(colActual).getTipo() == TipoCelda.BARCO) {
                return false;
            }
        }
        
        Barco nuevoBarco = new Barco(nombre, longitud);
        barcos.add(nuevoBarco);
        
        for (int i = 0; i < longitud; i++) {
            int filaActual = horizontal ? fila : fila + i;
            int colActual = horizontal ? col + i : col;
            
            Celda celda = tablero.get(filaActual).get(colActual);
            celda.setTipo(TipoCelda.BARCO);
            celda.setNombreBarco(nombre);
        }
        
        return true;
    }
    
    public String disparar(int fila, int col) {
        if (fila < 0 || fila >= TAMANIO || col < 0 || col >= TAMANIO) {
            return "Posición inválida";
        }
        
        Celda celda = tablero.get(fila).get(col);
        
        if (celda.isDisparada()) {
            return "Ya disparaste en esta posición";
        }
        
        celda.setDisparada(true);
        
        if (celda.getTipo() == TipoCelda.AGUA) {
            celda.setTipo(TipoCelda.FALLO);
            return "Agua";
        } else if (celda.getTipo() == TipoCelda.BARCO) {
            celda.setTipo(TipoCelda.IMPACTO);
            
            String nombreBarco = celda.getNombreBarco();
            Barco barcoImpactado = null;
            
            for (Barco b : barcos) {
                if (b.getNombre().equals(nombreBarco)) {
                    barcoImpactado = b;
                    b.recibirImpacto();
                    break;
                }
            }
            
            if (barcoImpactado != null && barcoImpactado.estaHundido()) {
                return "¡HUNDIDO! Has hundido el " + nombreBarco;
            } else {
                return "¡IMPACTO!";
            }
        }
        
        return "Error desconocido";
    }
 
    public void mostrarTablero(boolean ocultarBarcos) {
        System.out.println("\n   0 1 2 3 4 5 6 7 8 9");
        System.out.println("  ---------------------");
        
        for (int i = 0; i < TAMANIO; i++) {
            System.out.print(i + " |");
            
            for (int j = 0; j < TAMANIO; j++) {
                Celda celda = tablero.get(i).get(j);
                char simbolo;
                
                switch (celda.getTipo()) {
                    case AGUA:
                        simbolo = '~';
                        break;
                    case BARCO:
                        simbolo = ocultarBarcos ? '~' : 'B';
                        break;
                    case IMPACTO:
                        simbolo = 'X';
                        break;
                    case FALLO:
                        simbolo = 'O'; 
                        break;
                    default:
                        simbolo = '?';
                }
                
                System.out.print(simbolo + " ");
            }
            
            System.out.println("|");
        }
        
        System.out.println("  ---------------------");
    }

    public void mostrarBarcos() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║      ESTADO DE TUS BARCOS            ║");
        System.out.println("╚══════════════════════════════════════╝");
        
        if (barcos.isEmpty()) {
            System.out.println("  (No hay barcos posicionados)");
            return;
        }
        
        for (Barco b : barcos) {
            String estado = b.estaHundido() ? "HUNDIDO 💀" : "ACTIVO ⚓";
            String barra = crearBarraProgreso(b.getImpactos(), b.getLongitud());
            
            System.out.printf("  • %s (L:%d)\n", b.getNombre(), b.getLongitud());
            System.out.printf("    %s [%d/%d impactos] - %s\n", 
                barra, b.getImpactos(), b.getLongitud(), estado);
        }
        System.out.println();
    }
  
    private String crearBarraProgreso(int impactos, int longitud) {
        StringBuilder barra = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            if (i < impactos) {
                barra.append("X");
            } else {
                barra.append("▪"); 
            }
        }
        return barra.toString();
    }

    public boolean todosLosBarcosCaidos() {
        if (barcos.isEmpty()) {
            return false;
        }
        
        for (Barco b : barcos) {
            if (!b.estaHundido()) {
                return false; 
            }
        }
        
        return true; 
    }
    
    public void verificarVictoria() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║   ¡VICTORIA! ¡HAS GANADO!      ║");
        System.out.println("║ Hundiste todos los barcos      ║");
        System.out.println("║        enemigos                ║");
        System.out.println("╚════════════════════════════════╝");
    }
    
    public void verificarDerrota() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║        HAS PERDIDO             ║");
        System.out.println("║  Todos tus barcos fueron       ║");
        System.out.println("║         hundidos               ║");
        System.out.println("╚════════════════════════════════╝");
    }
    
    public ArrayList<ArrayList<Celda>> getTablero() {
        return tablero;
    }
    
    public ArrayList<Barco> getBarcos() {
        return barcos;
    }
    
    public static int getTAMANIO() {
        return TAMANIO;
    }
}