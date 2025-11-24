package com.batallanaval.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorBatallaNaval {
    private static final int PUERTO = 666;
    private final ConcurrentLinkedQueue<Socket> salaDeEspera = new ConcurrentLinkedQueue<>();
    private int contadorPartidas = 0;
    
    private final ExecutorService pool = Executors.newCachedThreadPool(); 

    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            serverSocket.setReuseAddress(true); 
            
            System.out.println("🚀 Servidor de Batalla Naval iniciado en el puerto " + PUERTO);
            System.out.println("Esperando clientes...");

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("  -> Nuevo cliente conectado desde: " + clienteSocket.getInetAddress().getHostAddress());

                salaDeEspera.add(clienteSocket);
                
                if (salaDeEspera.size() >= 2) {
                    Socket jugador1 = salaDeEspera.poll();
                    Socket jugador2 = salaDeEspera.poll();
                    
                    contadorPartidas++;
                    ManejadorPartida partida = new ManejadorPartida(jugador1, jugador2, "Partida " + contadorPartidas);
                    
                    pool.execute(partida); 
                    System.out.println("\n--- Partida #" + contadorPartidas + " creada. Esperando posicionamiento. ---\n");
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Error grave en el Servidor: " + e.getMessage());
        } finally {
            pool.shutdown();
        }
    }

    public static void main(String[] args) {
        new ServidorBatallaNaval().iniciar();
    }
}