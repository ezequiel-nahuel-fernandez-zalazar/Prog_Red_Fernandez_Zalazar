package Servidor;

import java.io.*;
import java.net.*;

public class Servidor {
    private static final int PUERTO = 5000;
    private ServerSocket serverSocket;
    private volatile boolean activo;
    
    private int clientesConectados;
    private int partidasCreadas;
    
    public Servidor() {
        this.activo = true;
        this.clientesConectados = 0;
        this.partidasCreadas = 0;
    }
    
    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciar();
    }

    public void iniciar() {
        try {
            serverSocket = new ServerSocket(PUERTO);
            
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║     SERVIDOR BATALLA NAVAL - MULTIJUGADOR     ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.println();
            System.out.println("✓ Servidor iniciado en puerto " + PUERTO);
            System.out.println("✓ Soporte para múltiples partidas simultáneas");
            System.out.println("✓ Esperando conexiones de jugadores...");
            
            while (activo) {
                try {
                    Socket clienteSocket = serverSocket.accept();
                    clientesConectados++;
                    
                    System.out.println("═══════════════════════════════════════════════");
                    System.out.println("✓ Nueva conexión desde: " + clienteSocket.getInetAddress());
                    System.out.println("  Total clientes conectados: " + clientesConectados);
                    System.out.println("  Jugadores en espera: " + SalaEspera.getInstance().getJugadoresEsperando());
                    System.out.println("═══════════════════════════════════════════════");
                    System.out.println();
                    
                    ManejadorCliente manejador = new ManejadorCliente(clienteSocket);
                    manejador.start();
                    
                } catch (SocketException e) {
                    if (!activo) {
                        System.out.println("\n[Servidor] Deteniendo servidor...");
                        break;
                    }
                    throw e;
                }
            }
            
        } catch (IOException e) {
            System.err.println("[Servidor] Error fatal: " + e.getMessage());
            e.printStackTrace();
        } finally {
            detener();
        }
    }

    public void detener() {
        activo = false;
        
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            
            System.out.println("\n╔════════════════════════════════════════════════╗");
            System.out.println("║           SERVIDOR DETENIDO                    ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.println();
            System.out.println("Estadísticas de la sesión:");
            System.out.println("  • Clientes conectados: " + clientesConectados);
            System.out.println("  • Partidas creadas: " + partidasCreadas);
            System.out.println();
            
        } catch (IOException e) {
            System.err.println("[Servidor] Error al cerrar: " + e.getMessage());
        }
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\n[Servidor] Señal de interrupción recibida...");
        }));
    }
}