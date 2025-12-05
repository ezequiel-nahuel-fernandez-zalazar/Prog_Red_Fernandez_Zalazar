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
    private Tablero tableroEnemigo; 
    
    private boolean miTurno;
    private boolean juegoActivo;
    
    public Cliente() {
        miTablero = new Tablero();
        tableroEnemigo = new Tablero(); 
        reader = new BufferedReader(new InputStreamReader(System.in));
        juegoActivo = true;
    }
    
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.iniciar();
    }
    
    public void iniciar() {
        try {
            System.out.println("=== BATALLA NAVAL - CLIENTE ===\n");
            
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
            
            System.out.println("\nConectando al servidor " + ip + ":" + puerto + "...");
            socket = new Socket(ip, puerto);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            
            System.out.println("¡Conectado al servidor!\n");
            
            out.writeObject(new MensajeConexion(nombreJugador));
            out.flush();
            
            Mensaje confirmacion = (Mensaje) in.readObject();
            System.out.println("Servidor: " + confirmacion.getContenido() + "\n");
            
            Mensaje msgOponente = (Mensaje) in.readObject();
            System.out.println("Servidor: " + msgOponente.getContenido() + "\n");
            
            posicionarBarcos();
            
            jugar();
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }
    
    private void posicionarBarcos() throws IOException, ClassNotFoundException {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   FASE DE POSICIONAMIENTO DE BARCOS   ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
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
                
                if (miTablero.colocarBarco(nombre, fila, col, longitud, horizontal)) {
                    out.writeObject(new MensajePosicionBarco(nombre, fila, col, longitud, horizontal));
                    out.flush();
                    
                    Mensaje respuesta = (Mensaje) in.readObject();
                    
                    if (respuesta.getTipo() == Mensaje.TipoMensaje.POSICION_BARCO) {
                        System.out.println("✓ " + respuesta.getContenido());
                        colocado = true;
                    } else if (respuesta.getTipo() == Mensaje.TipoMensaje.ERROR) {
                        System.out.println("✗ Error: " + respuesta.getContenido());
                        miTablero = new Tablero();
                    }
                } else {
                    System.out.println("✗ No se puede colocar el barco ahí. Intenta de nuevo.");
                }
            }
        }
        
        System.out.println("\n✓ ¡Todos tus barcos están posicionados!");
        System.out.println("\nTu tablero final:");
        miTablero.mostrarTablero(false);
        
        out.writeObject(new MensajeListoParaJugar());
        out.flush();
        
        System.out.println("\nEsperando que el oponente termine de posicionar...");
    }
    
    private void jugar() throws IOException, ClassNotFoundException {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║          ¡BATALLA INICIADA!            ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        while (juegoActivo) {
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
        
        out.writeObject(new MensajeDisparo(fila, col));
        out.flush();
        
        System.out.println("Disparo enviado. Esperando resultado...");
    }
    
    private void mostrarResultadoDisparo(MensajeResultadoDisparo msg) {
        int fila = msg.getFila();
        int col = msg.getColumna();
        
        if (msg.isDisparoPropio()) {
            System.out.println("\n▶ Tu disparo en (" + fila + ", " + col + "):");
            
            switch (msg.getResultado()) {
                case AGUA:
                    System.out.println("  AGUA");
                    tableroEnemigo.disparar(fila, col);
                    break;
                case IMPACTO:
                    System.out.println("  ¡IMPACTO!");
                    tableroEnemigo.getTablero().get(fila).get(col).setTipo(TipoCelda.IMPACTO);
                    tableroEnemigo.getTablero().get(fila).get(col).setDisparada(true);
                    break;
                case HUNDIDO:
                    System.out.println("   ¡HUNDIDO! - " + msg.getNombreBarcoHundido());
                    tableroEnemigo.getTablero().get(fila).get(col).setTipo(TipoCelda.IMPACTO);
                    tableroEnemigo.getTablero().get(fila).get(col).setDisparada(true);
                    break;
            }
        } else {
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
