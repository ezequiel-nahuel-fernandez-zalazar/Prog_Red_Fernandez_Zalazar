package Comun;

import java.util.ArrayList;

/**
 * Representa el tablero de juego de un jugador
 */
public class Tablero {
    private ArrayList<ArrayList<Celda>> tablero;
    private ArrayList<Barco> barcos;
    private static final int TAMANIO = 10;
    
    /**
     * Constructor: inicializa el tablero vacío
     */
    public Tablero() {
        this.barcos = new ArrayList<>();
        inicializarTablero();
    }
    
    /**
     * Crea el tablero 10x10 lleno de celdas con agua
     */
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
    
    /**
     * Coloca un barco en el tablero
     * @param nombre Nombre del barco
     * @param fila Fila inicial (0-9)
     * @param col Columna inicial (0-9)
     * @param longitud Longitud del barco
     * @param horizontal true si es horizontal, false si es vertical
     * @return true si se colocó exitosamente, false si hay error
     */
    public boolean colocarBarco(String nombre, int fila, int col, int longitud, boolean horizontal) {
        // Validar que la posición inicial esté dentro del tablero
        if (fila < 0 || fila >= TAMANIO || col < 0 || col >= TAMANIO) {
            return false;
        }
        
        // Validar que el barco quepa en el tablero
        if (horizontal) {
            if (col + longitud > TAMANIO) {
                return false;
            }
        } else {
            if (fila + longitud > TAMANIO) {
                return false;
            }
        }
        
        // Verificar que no haya colisiones con otros barcos
        for (int i = 0; i < longitud; i++) {
            int filaActual = horizontal ? fila : fila + i;
            int colActual = horizontal ? col + i : col;
            
            if (tablero.get(filaActual).get(colActual).getTipo() == TipoCelda.BARCO) {
                return false; // Ya hay un barco aquí
            }
        }
        
        // Colocar el barco
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
    
    /**
     * Realiza un disparo en el tablero
     * @param fila Fila del disparo (0-9)
     * @param col Columna del disparo (0-9)
     * @return Mensaje con el resultado del disparo
     */
    public String disparar(int fila, int col) {
        // Validar posición
        if (fila < 0 || fila >= TAMANIO || col < 0 || col >= TAMANIO) {
            return "Posición inválida";
        }
        
        Celda celda = tablero.get(fila).get(col);
        
        // Verificar si ya se disparó aquí
        if (celda.isDisparada()) {
            return "Ya disparaste en esta posición";
        }
        
        celda.setDisparada(true);
        
        // Verificar qué había en la celda
        if (celda.getTipo() == TipoCelda.AGUA) {
            celda.setTipo(TipoCelda.FALLO);
            return "Agua";
        } else if (celda.getTipo() == TipoCelda.BARCO) {
            celda.setTipo(TipoCelda.IMPACTO);
            
            // Registrar el impacto en el barco correspondiente
            String nombreBarco = celda.getNombreBarco();
            Barco barcoImpactado = null;
            
            for (Barco b : barcos) {
                if (b.getNombre().equals(nombreBarco)) {
                    barcoImpactado = b;
                    b.recibirImpacto();
                    break;
                }
            }
            
            // Verificar si el barco fue hundido
            if (barcoImpactado != null && barcoImpactado.estaHundido()) {
                return "¡HUNDIDO! Has hundido el " + nombreBarco;
            } else {
                return "¡IMPACTO!";
            }
        }
        
        return "Error desconocido";
    }
    
    /**
     * Visualización - Muestra el tablero en consola con formato de grilla
     * @param ocultarBarcos true para ocultar barcos intactos (tablero enemigo), 
     *                      false para mostrar todo (tablero propio)
     * Usa símbolos: ~ (agua), B (barco), X (impacto), O (fallo)
     */
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
                        simbolo = '~';  // Agua
                        break;
                    case BARCO:
                        // Si ocultarBarcos es true, no muestra barcos intactos (para el oponente)
                        // Si es false, muestra todo (tablero propio)
                        simbolo = ocultarBarcos ? '~' : 'B';  // Barco
                        break;
                    case IMPACTO:
                        simbolo = 'X';  // Impacto
                        break;
                    case FALLO:
                        simbolo = 'O';  // Fallo
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
    
    /**
     * Lista todos los barcos con su estado actual
     * Muestra: nombre, longitud, impactos recibidos, estado (ACTIVO/HUNDIDO)
     */
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
    
    /**
     * Crea una barra de progreso visual para los impactos del barco
     */
    private String crearBarraProgreso(int impactos, int longitud) {
        StringBuilder barra = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            if (i < impactos) {
                barra.append("X");  // Impactado
            } else {
                barra.append("▪");  // Intacto
            }
        }
        return barra.toString();
    }
    
    /**
     * Condición de Victoria/Derrota
     * Verifica si todos los barcos están hundidos
     * @return true si todos los barcos están hundidos, false si al menos un barco está activo
     */
    public boolean todosLosBarcosCaidos() {
        // Si no hay barcos, retornar false (no se puede ganar sin barcos)
        if (barcos.isEmpty()) {
            return false;
        }
        
        // Verificar cada barco
        for (Barco b : barcos) {
            if (!b.estaHundido()) {
                return false;  // Al menos un barco está activo
            }
        }
        
        return true;  // Todos los barcos están hundidos
    }
    
    /**
     * Muestra mensaje de victoria
     */
    public void verificarVictoria() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║   ¡VICTORIA! ¡HAS GANADO!      ║");
        System.out.println("║ Hundiste todos los barcos      ║");
        System.out.println("║        enemigos                ║");
        System.out.println("╚════════════════════════════════╝");
    }
    
    /**
     * Muestra mensaje de derrota
     */
    public void verificarDerrota() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║        HAS PERDIDO             ║");
        System.out.println("║  Todos tus barcos fueron       ║");
        System.out.println("║         hundidos               ║");
        System.out.println("╚════════════════════════════════╝");
    }
    
    // Getters
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