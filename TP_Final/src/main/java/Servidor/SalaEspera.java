package Servidor;

import java.util.ArrayList;
import java.util.List;

public class SalaEspera {
    private static SalaEspera instance;
    
    private List<ManejadorCliente> jugadoresEsperando;
    
    private SalaEspera() {
        jugadoresEsperando = new ArrayList<>();
    }

    public static synchronized SalaEspera getInstance() {
        if (instance == null) {
            instance = new SalaEspera();
        }
        return instance;
    }

    public synchronized void agregarJugador(ManejadorCliente jugador) {
        System.out.println("[SalaEspera] " + jugador.getNombreJugador() + 
                         " agregado a la sala de espera");
        
        if (!jugadoresEsperando.isEmpty()) {
            ManejadorCliente oponente = jugadoresEsperando.remove(0);
            
            System.out.println("[SalaEspera] Emparejando: " + jugador.getNombreJugador() + 
                             " vs " + oponente.getNombreJugador());
            
            Partida nuevaPartida = new Partida(oponente, jugador);
            nuevaPartida.iniciar();
            
        } else {
            jugadoresEsperando.add(jugador);
            System.out.println("[SalaEspera] Jugadores en espera: " + 
                             jugadoresEsperando.size());
        }
    }

    public synchronized void removerJugador(ManejadorCliente jugador) {
        if (jugadoresEsperando.remove(jugador)) {
            System.out.println("[SalaEspera] " + jugador.getNombreJugador() + 
                             " removido de la sala de espera");
        }
    }

    public synchronized int getJugadoresEsperando() {
        return jugadoresEsperando.size();
    }
}