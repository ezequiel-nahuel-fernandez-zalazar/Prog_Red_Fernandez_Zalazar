package com.batallanaval.servidor;

import com.batallanaval.comunicacion.Mensaje;
import com.batallanaval.modelo.Tablero;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Semaphore;

public class HiloCliente implements Runnable {
    private final Socket socket;
    private final String nombreJugador;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    
    private Tablero tableroPropio; 
    private HiloCliente oponente;
    private ManejadorPartida partida;
    
    private final Semaphore tableroListo = new Semaphore(0); 

    public HiloCliente(Socket socket, String nombre) {
        this.socket = socket;
        this.nombreJugador = nombre;
        ObjectOutputStream tempOut = null;
        ObjectInputStream tempIn = null;
        try {
            // Inicialización de flujos: OUT antes de IN
            tempOut = new ObjectOutputStream(socket.getOutputStream());
            tempIn = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("Error al inicializar flujos para " + nombre + ": " + e.getMessage());
        }
        this.out = tempOut;
        this.in = tempIn;
    }

    // Getters y Setters
    public void setOponente(HiloCliente oponente) { this.oponente = oponente; }
    public void setPartida(ManejadorPartida partida) { this.partida = partida; }
    public Semaphore getTableroListo() { return tableroListo; }

    public void enviarMensaje(Mensaje mensaje) {
        if (out == null) return;
        try {
            out.writeObject(mensaje);
            out.flush();
        } catch (SocketException e) {
            System.out.println("Desconexión de " + nombreJugador + " detectada. Partida terminada.");
        } catch (IOException e) {
            System.err.println("Error enviando mensaje a " + nombreJugador + ": " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            // 1. Fase de Posicionamiento
            recibirPosicionamiento();
            tableroListo.release(); 

            // 2. Bucle principal de escucha y manejo de movimientos
            while (!socket.isClosed()) {
                Mensaje mensaje = (Mensaje) in.readObject();
                
                if (mensaje.getTipo() == Mensaje.Tipo.TURNO_DISPARO) {
                    manejarDisparo(mensaje.getDatos());
                }
            }
        } catch (EOFException | SocketException e) {
            System.out.println(nombreJugador + " se desconectó.");
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error en el hilo de " + nombreJugador + ": " + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }
    
    private void recibirPosicionamiento() throws ClassNotFoundException, IOException {
        Mensaje posMsg = (Mensaje) in.readObject();
        if (posMsg.getTipo() == Mensaje.Tipo.POSICIONAMIENTO_OK) {
            this.tableroPropio = (Tablero) posMsg.getObjeto();
        } else {
             throw new IOException("Protocolo de posicionamiento inválido.");
        }
    }

    private void manejarDisparo(String coordenadas) {
        if (!partida.esTurnoDe(this)) {
            enviarMensaje(new Mensaje(Mensaje.Tipo.RESULTADO_DISPARO, "ESPERA_TU_TURNO"));
            return;
        }

        try {
            String[] parts = coordenadas.split(",");
            int fila = Integer.parseInt(parts[0].trim());
            int col = Integer.parseInt(parts[1].trim());

            // Disparar en el tablero del oponente
            String resultado = oponente.tableroPropio.disparar(fila, col);
            
            // Notificar al atacante
            enviarMensaje(new Mensaje(Mensaje.Tipo.RESULTADO_DISPARO, resultado));
            
            // Notificar al oponente que ha sido atacado
            oponente.enviarMensaje(new Mensaje(Mensaje.Tipo.ACTUALIZAR_ESTADO, "¡Te atacaron en (" + fila + "," + col + ")! Resultado: " + resultado));
            
            // Reenviar el tablero del oponente al atacante para que lo visualice
            enviarMensaje(new Mensaje(Mensaje.Tipo.ENVIO_TABLERO_OPONENTE, "Tablero oponente actualizado", oponente.tableroPropio));

            // LÓGICA DE CAMBIO DE TURNO
            
            // 1. Verificar si el oponente fue hundido completamente
            if (oponente.tableroPropio.todosLosBarcosCaidos()) {
                partida.notificarFinPartida(this);
            } 
            // 2. Si no es un impacto ni un hundimiento (es decir, fue Agua), cambiar el turno.
            else if (!resultado.contains("IMPACTO") && !resultado.contains("HUNDIDO")) { 
                partida.cambiarTurno();
            }

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            enviarMensaje(new Mensaje(Mensaje.Tipo.RESULTADO_DISPARO, "COORDENADAS_INVALIDAS"));
        }
    }
    
    private void cerrarRecursos() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) { /* Ignorar */ }
    }
}