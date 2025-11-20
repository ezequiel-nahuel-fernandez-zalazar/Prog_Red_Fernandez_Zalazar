package com.batallanaval.servidor;

import com.batallanaval.comunicacion.Mensaje;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class ManejadorPartida implements Runnable {
    private final String nombrePartida;
    private final HiloCliente jugador1;
    private final HiloCliente jugador2;
    private final AtomicBoolean turnoJugador1; 
    
    // Bandera para mantener el hilo de la partida activo hasta el final.
    private volatile boolean partidaActiva = true; 

    public ManejadorPartida(Socket s1, Socket s2, String nombre) {
        this.nombrePartida = nombre;
        this.jugador1 = new HiloCliente(s1, "Jugador 1");
        this.jugador2 = new HiloCliente(s2, "Jugador 2");
        this.turnoJugador1 = new AtomicBoolean(true); 
    }

    @Override
    public void run() {
        try {
            // Configurar referencias cruzadas
            jugador1.setOponente(jugador2);
            jugador2.setOponente(jugador1);
            jugador1.setPartida(this);
            jugador2.setPartida(this);

            // Iniciar los hilos de los clientes
            new Thread(jugador1).start();
            new Thread(jugador2).start();

            // Sincronización: Esperar a que ambos posicionen sus barcos
            jugador1.getTableroListo().acquire();
            System.out.println("[" + nombrePartida + "] Jugador 1 listo.");
            jugador2.getTableroListo().acquire();
            System.out.println("[" + nombrePartida + "] Jugador 2 listo.");
            
            // Notificar el inicio y el primer turno
            jugador1.enviarMensaje(new Mensaje(Mensaje.Tipo.EMPEZAR_PARTIDA, "¡La batalla ha comenzado! Eres el primero en disparar."));
            jugador2.enviarMensaje(new Mensaje(Mensaje.Tipo.EMPEZAR_PARTIDA, "¡La batalla ha comenzado! Espera el turno del oponente."));
            
            // Bucle de bloqueo: Mantiene el hilo ManejadorPartida vivo mientras los clientes juegan
            while (partidaActiva) {
                Thread.sleep(1000); 
            }

        } catch (InterruptedException e) {
            System.err.println("[" + nombrePartida + "] Partida interrumpida: " + e.getMessage());
        } finally {
            System.out.println("[" + nombrePartida + "] Partida finalizada.");
        }
    }

    public synchronized boolean esTurnoDe(HiloCliente jugador) {
        return (jugador == jugador1 && turnoJugador1.get()) || (jugador == jugador2 && !turnoJugador1.get());
    }
    
    public synchronized void cambiarTurno() {
        turnoJugador1.set(!turnoJugador1.get());
        
        HiloCliente siguiente = turnoJugador1.get() ? jugador1 : jugador2;
        HiloCliente actual = turnoJugador1.get() ? jugador2 : jugador1;
        
        // Notificaciones de cambio de turno
        siguiente.enviarMensaje(new Mensaje(Mensaje.Tipo.ACTUALIZAR_ESTADO, "¡Es tu turno! Ingresa tus coordenadas."));
        actual.enviarMensaje(new Mensaje(Mensaje.Tipo.ACTUALIZAR_ESTADO, "Turno del oponente. Esperando..."));
    }

    public synchronized void notificarFinPartida(HiloCliente ganador) {
        HiloCliente perdedor = (ganador == jugador1) ? jugador2 : jugador1;
        
        ganador.enviarMensaje(new Mensaje(Mensaje.Tipo.FIN_PARTIDA, "¡VICTORIA! Todos los barcos del oponente han sido hundidos."));
        perdedor.enviarMensaje(new Mensaje(Mensaje.Tipo.FIN_PARTIDA, "DERROTA. Todos tus barcos han sido hundidos."));
        
        // Finaliza el bucle de espera
        this.partidaActiva = false;
    }
}