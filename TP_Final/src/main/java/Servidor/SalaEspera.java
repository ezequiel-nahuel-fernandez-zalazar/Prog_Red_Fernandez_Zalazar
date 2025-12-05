package Servidor;

import java.util.ArrayList;
import java.util.List;

/**
 * Sala de espera para emparejar jugadores
 * Usa patrón Singleton para tener una única instancia
 */
public class SalaEspera {
    private static SalaEspera instance;
    
    private List<ManejadorCliente> jugadoresEsperando;
    
    private SalaEspera() {
        jugadoresEsperando = new ArrayList<>();
    }
    
    /**
     * Obtiene la instancia única de la sala de espera
     */
    public static synchronized SalaEspera getInstance() {
        if (instance == null) {
            instance = new SalaEspera();
        }
        return instance;
    }
    
    /**
     * Agrega un jugador a la sala de espera y busca oponente
     */
    public synchronized void agregarJugador(ManejadorCliente jugador) {
        System.out.println("[SalaEspera] " + jugador.getNombreJugador() + 
                         " agregado a la sala de espera");
        
        // Si hay alguien esperando, emparejar
        if (!jugadoresEsperando.isEmpty()) {
            ManejadorCliente oponente = jugadoresEsperando.remove(0);
            
            System.out.println("[SalaEspera] Emparejando: " + jugador.getNombreJugador() + 
                             " vs " + oponente.getNombreJugador());
            
            // Crear nueva partida
            Partida nuevaPartida = new Partida(oponente, jugador);
            nuevaPartida.iniciar();
            
        } else {
            // Agregar a la lista de espera
            jugadoresEsperando.add(jugador);
            System.out.println("[SalaEspera] Jugadores en espera: " + 
                             jugadoresEsperando.size());
        }
    }
    
    /**
     * Remueve un jugador de la sala de espera
     */
    public synchronized void removerJugador(ManejadorCliente jugador) {
        if (jugadoresEsperando.remove(jugador)) {
            System.out.println("[SalaEspera] " + jugador.getNombreJugador() + 
                             " removido de la sala de espera");
        }
    }
    
    /**
     * Obtiene el número de jugadores esperando
     */
    public synchronized int getJugadoresEsperando() {
        return jugadoresEsperando.size();
    }
}