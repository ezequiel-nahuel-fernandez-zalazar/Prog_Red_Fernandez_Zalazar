package com.batallanaval.cliente;

import com.batallanaval.comunicacion.Mensaje;
import com.batallanaval.modelo.Barco;
import com.batallanaval.modelo.Tablero;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ClienteBatallaNaval {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Tablero tableroPropio;
    private Tablero tableroOponente; 
    private final Scanner scanner = new Scanner(System.in);
    
    private String serverIp = "127.0.0.1";
    private int port = 666;

    public static void main(String[] args) {
        new ClienteBatallaNaval().iniciar();
    }

    public void iniciar() {
        System.out.println("🚢 Cliente Batalla Naval");
        
        System.out.print("Ingrese IP del servidor (default: 127.0.0.1): ");
        String ip = scanner.nextLine();
        if (!ip.isEmpty()) this.serverIp = ip;
        
        try {
            socket = new Socket(serverIp, port);
            System.out.println("Conectado al servidor. Esperando compañero...");

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            tableroPropio = new Tablero();
            tableroOponente = new Tablero(); 

            posicionarBarcos();

            out.writeObject(new Mensaje(Mensaje.Tipo.POSICIONAMIENTO_OK, "Tablero listo", tableroPropio));
            out.flush();
            System.out.println("Barcos posicionados. Esperando inicio de partida...");

            buclePrincipalJuego();

        } catch (IOException e) {
            System.err.println("❌ Error de conexión o I/O: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }
    
    private void posicionarBarcos() {
        List<Barco> barcosRestantes;
        do {
            barcosRestantes = tableroPropio.getBarcosNoColocados();
            if (barcosRestantes.isEmpty()) break;
            
            System.out.println("\n--- 🛠️ Posicionamiento de Barcos ---");
            tableroPropio.mostrarTablero(false);
            System.out.println("\nBarcos pendientes:");
            for (int i = 0; i < barcosRestantes.size(); i++) {
                Barco b = barcosRestantes.get(i);
                System.out.printf("  %d) %s (Longitud: %d)\n", i + 1, b.getNombre(), b.getLongitud());
            }

            try {
                System.out.print("\nSelecciona un barco (ej. Portaaviones): ");
                String nombreBarco = scanner.nextLine().trim();
                
                Barco barcoSel = barcosRestantes.stream()
                    .filter(b -> b.getNombre().equalsIgnoreCase(nombreBarco))
                    .findFirst().orElse(null);

                if (barcoSel == null) {
                    System.out.println("Barco no válido o ya colocado.");
                    continue;
                }
                
                System.out.print("Fila inicial (0-9): ");
                int fila = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Columna inicial (0-9): ");
                int col = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Orientación (H/V): ");
                boolean horizontal = scanner.nextLine().trim().toUpperCase().startsWith("H");

                if (!tableroPropio.colocarBarco(barcoSel.getNombre(), fila, col, horizontal)) {
                    System.out.println("❌ ERROR: No se puede colocar aquí (límites o colisión). Intenta de nuevo.");
                } else {
                    System.out.println("✅ ¡Barco colocado!");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Usa números para coordenadas.");
            }
        } while (barcosRestantes.size() > 0);
    }
    
    private void buclePrincipalJuego() {
        boolean partidaEnCurso = true;
        
        while (partidaEnCurso) {
            try {
                Mensaje mensaje = (Mensaje) in.readObject();
                
                switch (mensaje.getTipo()) {
                    case EMPEZAR_PARTIDA:
                        System.out.println("\n*** " + mensaje.getDatos() + " ***");
                        mostrarAmbosTableros();
                        if (mensaje.getDatos().contains("primero")) {
                            pedirDisparo();
                        }
                        break;
                    case ACTUALIZAR_ESTADO:
                        System.out.println("\n*** NOTIFICACION: " + mensaje.getDatos() + " ***");
                        if (mensaje.getDatos().contains("tu turno") || mensaje.getDatos().contains("Dispara de nuevo")) {
                            mostrarAmbosTableros();
                            pedirDisparo();
                        }
                        break;
                    case RESULTADO_DISPARO:
                        System.out.println("--- Tu Disparo: " + mensaje.getDatos() + " ---");
                        break;
                    case ENVIO_TABLERO_OPONENTE:
                        tableroOponente = (Tablero) mensaje.getObjeto();
                        mostrarAmbosTableros();
                        break;
                    case FIN_PARTIDA:
                        System.out.println("\n" + """
                            ---------------------------
                                🏁 FIN DE PARTIDA 🏁
                            ---------------------------
                            """ + mensaje.getDatos());
                        partidaEnCurso = false;
                        break;
                    default:
                        System.out.println("Mensaje de servidor desconocido.");
                }

            } catch (EOFException e) {
                System.out.println("Servidor cerró la conexión. Fin de partida.");
                partidaEnCurso = false;
            } catch (ClassNotFoundException | IOException e) {
                System.err.println("❌ Error de comunicación con el servidor: " + e.getMessage());
                partidaEnCurso = false;
            }
        }
    }
    
    private void pedirDisparo() {
        System.out.println("\n🎯 Ingresa coordenadas de disparo (Fila,Columna ej: 0,5):");
        try {
            String entrada = scanner.nextLine().trim();
            if (!entrada.matches("\\d+,\\s*\\d+")) {
                 System.out.println("❌ Formato de coordenadas inválido. Intenta de nuevo.");
                 pedirDisparo(); 
                 return;
            }
            out.writeObject(new Mensaje(Mensaje.Tipo.TURNO_DISPARO, entrada));
            out.flush();
        } catch (IOException e) {
            System.err.println("Error enviando disparo: " + e.getMessage());
        }
    }
    
    private void mostrarAmbosTableros() {
        System.out.println("\n" + """
            ==================================
                  TU TABLERO (PROPIO)
            ==================================
            """);
        tableroPropio.mostrarTablero(false); 
        tableroPropio.mostrarBarcos(); 

        System.out.println("\n" + """
            ==================================
                 TABLERO OPONENTE
            ==================================
            """);
        tableroOponente.mostrarTablero(true); 
        System.out.println("==================================");
    }

    private void cerrarRecursos() {
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) { /* Ignorar */ }
        scanner.close();
    }
}