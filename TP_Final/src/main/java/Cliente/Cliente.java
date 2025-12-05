package Cliente;

import java.io.*;
import java.net.*;
import Comun.*;
import Comun.mensajes.*;


public class Cliente {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private BufferedReader reader;  
    
    private String nombreJugador;
    private Tablero miTablero;
    private Tablero tableroEnemigo; // Para mostrar mis disparos
    
    private boolean miTurno;
    private boolean juegoActivo;
    
    public Cliente() {
        miTablero = new Tablero();
        tableroEnemigo = new Tablero(); // Solo para visualización
        reader = new BufferedReader(new InputStreamReader(System.in));
        juegoActivo = true;
    }
    
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.iniciar();
    }
    
    /**
     * Inicia el cliente y se conecta al servidor
     */
    public void iniciar() {
        try {
            System.out.println("=== BATALLA NAVAL - CLIENTE ===\n");
            
            // Solicitar datos de conexión
            System.out.print("Ingresa tu nombre: ");
            nombreJugador = reader.readLine();
            
            System.out.print("IP del servidor (enter para localhost): ");
            String ip = reader.readLine();
            if (ip.isEmpty()) {
                ip = "localhost";
            }
            
            System.out.print("Puerto (enter para 5000): ");
            String puertoStr = reader.readLine();
            int puerto = puertoStr.isEmpty() ? 5000 : Integer.parseInt(puertoStr);
            
            // Conectar al servidor
            System.out.println("\nConectando al servidor " + ip + ":" + puerto + "...");
            socket = new Socket(ip, puerto);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            
            System.out.println("¡Conectado al servidor!\n");
            
            // Enviar mensaje de conexión
            out.writeObject(new MensajeConexion(nombreJugador));
            out.flush();
            
            // Recibir confirmación
            Mensaje confirmacion = (Mensaje) in.readObject();
            System.out.println("Servidor: " + confirmacion.getContenido() + "\n");
            
            // Esperar mensaje de que el oponente se conectó
            Mensaje msgOponente = (Mensaje) in.readObject();
            System.out.println("Servidor: " + msgOponente.getContenido() + "\n");
            
            // Fase de posicionamiento
            posicionarBarcos();
            
            // Iniciar el juego
            jugar();
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }
    
    /**
     * Permite al jugador posicionar sus 5 barcos
     */
    private void posicionarBarcos() throws IOException, ClassNotFoundException {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   FASE DE POSICIONAMIENTO DE BARCOS   ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        // Definir los 5 barcos según la consigna
        String[][] barcos = {
            {"Portaaviones", "5"},
            {"Acorazado", "4"},
            {"Crucero", "3"},
            {"Submarino", "3"},
            {"Destructor", "2"}
        };
        
        for (String[] barco : barcos) {
            String nombre = barco[0];
            int longitud = Integer.parseInt(barco[1]);
            
            boolean colocado = false;
            
            while (!colocado) {
                System.out.println("\n--- Colocando: " + nombre + " (longitud: " + longitud + ") ---");
                miTablero.mostrarTablero(false);
                
                System.out.print("Fila inicial (0-9): ");
                String filaStr = reader.readLine();
                int fila = Integer.parseInt(filaStr);
                
                System.out.print("Columna inicial (0-9): ");
                String colStr = reader.readLine();
                int col = Integer.parseInt(colStr);
                
                System.out.print("Orientación (H=Horizontal, V=Vertical): ");
                String orientacionStr = reader.readLine().toUpperCase();
                boolean horizontal = orientacionStr.startsWith("H");
                
                // Intentar colocar en el tablero local
                if (miTablero.colocarBarco(nombre, fila, col, longitud, horizontal)) {
                    // Enviar al servidor
                    out.writeObject(new MensajePosicionBarco(nombre, fila, col, longitud, horizontal));
                    out.flush();
                    
                    // Esperar confirmación del servidor
                    Mensaje respuesta = (Mensaje) in.readObject();
                    
                    if (respuesta.getTipo() == Mensaje.TipoMensaje.POSICION_BARCO) {
                        System.out.println("✓ " + respuesta.getContenido());
                        colocado = true;
                    } else if (respuesta.getTipo() == Mensaje.TipoMensaje.ERROR) {
                        System.out.println("✗ Error: " + respuesta.getContenido());
                        // Revertir el cambio en el tablero local
                        miTablero = new Tablero();
                        // Recolocar los barcos anteriores (simplificado: reiniciar)
                    }
                } else {
                    System.out.println("✗ No se puede colocar el barco ahí. Intenta de nuevo.");
                }
            }
        }
        
        System.out.println("\n✓ ¡Todos tus barcos están posicionados!");
        System.out.println("\nTu tablero final:");
        miTablero.mostrarTablero(false);
        
        // Notificar al servidor que estamos listos
        out.writeObject(new MensajeListoParaJugar());
        out.flush();
        
        System.out.println("\nEsperando que el oponente termine de posicionar...");
    }
    
    /**
     * Bucle principal del juego
     */
    private void jugar() throws IOException, ClassNotFoundException {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║          ¡BATALLA INICIADA!            ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        while (juegoActivo) {
            // Recibir mensaje del servidor
            Mensaje mensaje = (Mensaje) in.readObject();
            
            switch (mensaje.getTipo()) {
                case CAMBIO_TURNO:
                    MensajeCambioTurno msgTurno = (MensajeCambioTurno) mensaje;
                    miTurno = msgTurno.isEsMiTurno();
                    
                    if (miTurno) {
                        System.out.println("\n═══ ES TU TURNO ═══");
                        realizarDisparo();
                    } else {
                        System.out.println("\n═══ TURNO DEL OPONENTE ═══");
                        System.out.println("Esperando...");
                    }
                    break;
                    
                case RESULTADO_DISPARO:
                    MensajeResultadoDisparo msgResultado = (MensajeResultadoDisparo) mensaje;
                    mostrarResultadoDisparo(msgResultado);
                    break;
                    
                case FIN_PARTIDA:
                    MensajeFinPartida msgFin = (MensajeFinPartida) mensaje;
                    mostrarFinPartida(msgFin);
                    juegoActivo = false;
                    break;
                    
                default:
                    System.out.println("Mensaje: " + mensaje.getContenido());
            }
        }
    }
    
    /**
     * Permite al jugador realizar un disparo
     */
    private void realizarDisparo() throws IOException {
        System.out.println("\nTABLERO ENEMIGO (tus disparos):");
        tableroEnemigo.mostrarTablero(true);
        
        System.out.println("\nTU TABLERO:");
        miTablero.mostrarTablero(false);
        
        System.out.print("\nFila del disparo (0-9): ");
        String filaStr = reader.readLine();
        int fila = Integer.parseInt(filaStr);
        
        System.out.print("Columna del disparo (0-9): ");
        String colStr = reader.readLine();
        int col = Integer.parseInt(colStr);
        
        // Enviar disparo al servidor
        out.writeObject(new MensajeDisparo(fila, col));
        out.flush();
        
        System.out.println("Disparo enviado. Esperando resultado...");
    }
    
    /**
     * Muestra el resultado de un disparo
     */
    private void mostrarResultadoDisparo(MensajeResultadoDisparo msg) {
        int fila = msg.getFila();
        int col = msg.getColumna();
        
        if (msg.isDisparoPropio()) {
            // Fue mi disparo
            System.out.println("\n▶ Tu disparo en (" + fila + ", " + col + "):");
            
            switch (msg.getResultado()) {
                case AGUA:
                    System.out.println("  AGUA");
                    // Marcar como fallo en tablero enemigo
                    tableroEnemigo.disparar(fila, col);
                    break;
                case IMPACTO:
                    System.out.println("  ¡IMPACTO!");
                    // Marcar impacto en tablero enemigo
                    tableroEnemigo.getTablero().get(fila).get(col).setTipo(TipoCelda.IMPACTO);
                    tableroEnemigo.getTablero().get(fila).get(col).setDisparada(true);
                    break;
                case HUNDIDO:
                    System.out.println("   ¡HUNDIDO! - " + msg.getNombreBarcoHundido());
                    // Marcar impacto en tablero enemigo
                    tableroEnemigo.getTablero().get(fila).get(col).setTipo(TipoCelda.IMPACTO);
                    tableroEnemigo.getTablero().get(fila).get(col).setDisparada(true);
                    break;
            }
        } else {
            // Fue disparo del enemigo
            System.out.println("\n▶ Disparo enemigo en (" + fila + ", " + col + "):");
            
            switch (msg.getResultado()) {
                case AGUA:
                    System.out.println("  💧 El enemigo falló");
                    break;
                case IMPACTO:
                    System.out.println("  💥 ¡Te impactaron!");
                    break;
                case HUNDIDO:
                    System.out.println("  💀 ¡Hundieron tu " + msg.getNombreBarcoHundido() + "!");
                    break;
            }
        }
    }
    
    /**
     * Muestra el mensaje de fin de partida
     */
    private void mostrarFinPartida(MensajeFinPartida msg) {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════╗");
        
        if (msg.isGane()) {
            System.out.println("║                                        ║");
            System.out.println("║        🎉 ¡HAS GANADO! 🎉             ║");
            System.out.println("║                                        ║");
            System.out.println("║   ¡Hundiste todos los barcos          ║");
            System.out.println("║         enemigos!                      ║");
        } else {
            System.out.println("║                                        ║");
            System.out.println("║          HAS PERDIDO                   ║");
            System.out.println("║                                        ║");
            System.out.println("║    Todos tus barcos fueron             ║");
            System.out.println("║          hundidos                      ║");
            System.out.println("║                                        ║");
            System.out.println("║    Ganador: " + msg.getNombreGanador());
        }
        
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        System.out.println("\nTABLERO FINAL:");
        miTablero.mostrarTablero(false);
    }
    
    /**
     * Cierra la conexión
     */
    private void cerrarConexion() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
            if (reader != null) reader.close();
            
            System.out.println("\nConexión cerrada.");
        } catch (IOException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}
