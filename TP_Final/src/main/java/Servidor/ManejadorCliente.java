package Servidor;

import java.io.*;
import java.net.*;
import Comun.mensajes.*;

/**
 * Thread que maneja la comunicación con un cliente específico
 * Cada cliente conectado tiene su propio ManejadorCliente
 */
public class ManejadorCliente extends Thread {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    private String nombreJugador;
    private Partida partida;
    private boolean listo; // true cuando terminó de posicionar barcos
    
    private volatile boolean activo;
    
    public ManejadorCliente(Socket socket) {
        this.socket = socket;
        this.activo = true;
        this.listo = false;
    }
    
    @Override
    public void run() {
        try {
            // Inicializar streams
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            
            System.out.println("[ManejadorCliente] Nueva conexión desde: " + 
                             socket.getInetAddress());
            
            // Recibir mensaje de conexión inicial
            Mensaje mensajeInicial = (Mensaje) in.readObject();
            
            if (mensajeInicial instanceof MensajeConexion) {
                nombreJugador = ((MensajeConexion) mensajeInicial).getNombreJugador();
                System.out.println("[ManejadorCliente] Jugador conectado: " + nombreJugador);
                
                // Confirmar conexión
                enviarMensaje(new MensajeGenerico(Mensaje.TipoMensaje.CONEXION,
                    "Conectado al servidor. Esperando oponente..."));
                
                // Agregar a la sala de espera
                SalaEspera.getInstance().agregarJugador(this);
            }
            
            // Bucle principal: escuchar mensajes del cliente
            while (activo) {
                try {
                    Mensaje mensaje = (Mensaje) in.readObject();
                    procesarMensaje(mensaje);
                } catch (EOFException | SocketException e) {
                    // Cliente desconectado
                    break;
                }
            }
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("[ManejadorCliente] Error con " + nombreJugador + ": " + 
                             e.getMessage());
        } finally {
            cerrar();
        }
    }
    
    /**
     * Procesa los mensajes recibidos del cliente
     */
    private void procesarMensaje(Mensaje mensaje) {
        if (partida == null) {
            System.err.println("[ManejadorCliente] " + nombreJugador + 
                             " envió mensaje sin estar en partida");
            return;
        }
        
        switch (mensaje.getTipo()) {
            case POSICION_BARCO:
                MensajePosicionBarco msgBarco = (MensajePosicionBarco) mensaje;
                boolean colocado = partida.recibirBarco(this, msgBarco);
                
                if (colocado) {
                    enviarMensaje(new MensajeGenerico(Mensaje.TipoMensaje.POSICION_BARCO,
                        "Barco colocado correctamente"));
                } else {
                    enviarMensaje(new MensajeGenerico(Mensaje.TipoMensaje.ERROR,
                        "Error al colocar barco. Intenta de nuevo."));
                }
                break;
                
            case LISTO_PARA_JUGAR:
                partida.jugadorListo(this);
                break;
                
            case DISPARO:
                MensajeDisparo disparo = (MensajeDisparo) mensaje;
                partida.procesarDisparo(this, disparo);
                break;
                
            default:
                System.out.println("[ManejadorCliente] Mensaje no manejado: " + 
                                 mensaje.getTipo());
        }
    }
    
    /**
     * Envía un mensaje al cliente
     */
    public synchronized void enviarMensaje(Mensaje mensaje) {
        try {
            if (out != null && activo) {
                out.writeObject(mensaje);
                out.flush();
            }
        } catch (IOException e) {
            System.err.println("[ManejadorCliente] Error al enviar mensaje a " + 
                             nombreJugador + ": " + e.getMessage());
            cerrar();
        }
    }
    
    /**
     * Cierra la conexión con el cliente
     */
    public void cerrar() {
        if (!activo) return;
        
        activo = false;
        
        System.out.println("[ManejadorCliente] Cerrando conexión con " + nombreJugador);
        
        // Notificar a la partida si existe
        if (partida != null) {
            partida.jugadorDesconectado(this);
        }
        
        // Cerrar streams y socket
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            System.err.println("[ManejadorCliente] Error al cerrar conexión: " + 
                             e.getMessage());
        }
    }
    
    // Getters y Setters
    public String getNombreJugador() {
        return nombreJugador;
    }
    
    public Partida getPartida() {
        return partida;
    }
    
    public void setPartida(Partida partida) {
        this.partida = partida;
    }
    
    public boolean isListo() {
        return listo;
    }
    
    public void setListo(boolean listo) {
        this.listo = listo;
    }
    
    public boolean isActivo() {
        return activo;
    }
}