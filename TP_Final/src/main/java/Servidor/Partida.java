package Servidor;



import Comun.*;
import Comun.mensajes.*;

/**
 * Representa una partida entre dos jugadores
 * Maneja los tableros, turnos y lógica del juego
 */
public class Partida {
    private ManejadorCliente jugador1;
    private ManejadorCliente jugador2;
    
    private Tablero tableroJugador1;
    private Tablero tableroJugador2;
    
    private boolean turnoJugador1;
    private boolean juegoActivo;
    
    private int idPartida;
    private static int contadorPartidas = 0;
    
    public Partida(ManejadorCliente jugador1, ManejadorCliente jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tableroJugador1 = new Tablero();
        this.tableroJugador2 = new Tablero();
        this.turnoJugador1 = true;
        this.juegoActivo = false;
        this.idPartida = ++contadorPartidas;
        
        // Asociar esta partida a los manejadores
        jugador1.setPartida(this);
        jugador2.setPartida(this);
        
        System.out.println("[Partida " + idPartida + "] Creada entre " + 
                          jugador1.getNombreJugador() + " y " + jugador2.getNombreJugador());
    }
    
    /**
     * Inicia la fase de posicionamiento de barcos
     */
    public void iniciar() {
        try {
            // Notificar a ambos jugadores que la partida está lista
            jugador1.enviarMensaje(new MensajeGenerico(Mensaje.TipoMensaje.CONEXION,
                "Oponente conectado: " + jugador2.getNombreJugador() + ". ¡Posiciona tus barcos!"));
            jugador2.enviarMensaje(new MensajeGenerico(Mensaje.TipoMensaje.CONEXION,
                "Oponente conectado: " + jugador1.getNombreJugador() + ". ¡Posiciona tus barcos!"));
            
            System.out.println("[Partida " + idPartida + "] Esperando posicionamiento de barcos...");
            
            // Los jugadores posicionarán sus barcos de forma asíncrona
            // El método recibirBarco() será llamado por cada ManejadorCliente
            
        } catch (Exception e) {
            System.err.println("[Partida " + idPartida + "] Error al iniciar: " + e.getMessage());
        }
    }
    
    /**
     * Recibe un barco posicionado por un jugador
     */
    public synchronized boolean recibirBarco(ManejadorCliente jugador, MensajePosicionBarco msgBarco) {
        Tablero tablero = (jugador == jugador1) ? tableroJugador1 : tableroJugador2;
        
        boolean colocado = tablero.colocarBarco(
            msgBarco.getNombreBarco(),
            msgBarco.getFila(),
            msgBarco.getColumna(),
            msgBarco.getLongitud(),
            msgBarco.isHorizontal()
        );
        
        if (colocado) {
            int barcosColocados = tablero.getBarcos().size();
            System.out.println("[Partida " + idPartida + "] " + jugador.getNombreJugador() + 
                             " colocó barco " + barcosColocados + "/5: " + msgBarco.getNombreBarco());
        }
        
        return colocado;
    }
    
    /**
     * Notifica que un jugador está listo (terminó de posicionar barcos)
     */
    public synchronized void jugadorListo(ManejadorCliente jugador) {
        jugador.setListo(true);
        
        System.out.println("[Partida " + idPartida + "] " + jugador.getNombreJugador() + " está listo");
        
        // Verificar si ambos jugadores están listos
        if (jugador1.isListo() && jugador2.isListo()) {
            iniciarBatalla();
        }
    }
    
    /**
     * Inicia la fase de batalla
     */
    private void iniciarBatalla() {
        juegoActivo = true;
        System.out.println("[Partida " + idPartida + "] ¡Batalla iniciada!");
        
        // Notificar turno inicial
        jugador1.enviarMensaje(new MensajeCambioTurno(true));
        jugador2.enviarMensaje(new MensajeCambioTurno(false));
    }
    
    /**
     * Procesa un disparo de un jugador
     */
    public synchronized void procesarDisparo(ManejadorCliente atacante, MensajeDisparo disparo) {
        // Verificar que sea el turno del atacante
    	if ((atacante == jugador1 && !turnoJugador1) || 
    		    (atacante == jugador2 && turnoJugador1)) {
    		    atacante.enviarMensaje(new MensajeGenerico(Mensaje.TipoMensaje.ERROR, 
    		        "No es tu turno"));
    		    return;
        }
        
        // Determinar tablero enemigo y jugadores
    	Tablero tableroEnemigo;
    	ManejadorCliente defensor;
        
    	if (atacante == jugador1) {
    	    tableroEnemigo = tableroJugador2;
    	    defensor = jugador2;
    	} else {
    	    // Si no es jugador1, debe ser jugador2 (ya se validó el turno)
    	    tableroEnemigo = tableroJugador1;
    	    defensor = jugador1;
    	}
    	
        int fila = disparo.getFila();
        int col = disparo.getColumna();
        
        System.out.println("[Partida " + idPartida + "] " + atacante.getNombreJugador() + 
                         " dispara a (" + fila + ", " + col + ")");
        
        // Procesar disparo
        String resultadoTexto = tableroEnemigo.disparar(fila, col);
        
        // Determinar tipo de resultado
        MensajeResultadoDisparo.ResultadoDisparo resultado;
        String nombreBarcoHundido = null;
        
        if (resultadoTexto.contains("HUNDIDO")) {
            resultado = MensajeResultadoDisparo.ResultadoDisparo.HUNDIDO;
            // Extraer nombre del barco hundido
            int indiceEl = resultadoTexto.indexOf("el ");
            if (indiceEl != -1) {
                nombreBarcoHundido = resultadoTexto.substring(indiceEl + 3);
            }
            System.out.println("[Partida " + idPartida + "] ¡HUNDIDO! " + nombreBarcoHundido);
        } else if (resultadoTexto.contains("IMPACTO")) {
            resultado = MensajeResultadoDisparo.ResultadoDisparo.IMPACTO;
            System.out.println("[Partida " + idPartida + "] ¡IMPACTO!");
        } else {
            resultado = MensajeResultadoDisparo.ResultadoDisparo.AGUA;
            System.out.println("[Partida " + idPartida + "] Agua");
        }
        
        // Enviar resultado a ambos jugadores
        MensajeResultadoDisparo msgAtacante = new MensajeResultadoDisparo(
            fila, col, resultado, nombreBarcoHundido, true);
        MensajeResultadoDisparo msgDefensor = new MensajeResultadoDisparo(
            fila, col, resultado, nombreBarcoHundido, false);
        
        atacante.enviarMensaje(msgAtacante);
        defensor.enviarMensaje(msgDefensor);
        
        // Verificar si el juego terminó
        if (tableroEnemigo.todosLosBarcosCaidos()) {
            finalizarPartida(atacante, defensor);
        } else {
            // Cambiar turno
            turnoJugador1 = !turnoJugador1;
            jugador1.enviarMensaje(new MensajeCambioTurno(turnoJugador1));
            jugador2.enviarMensaje(new MensajeCambioTurno(!turnoJugador1));
        }
    }
    
    /**
     * Finaliza la partida y notifica al ganador
     */
    private void finalizarPartida(ManejadorCliente ganador, ManejadorCliente perdedor) {
        juegoActivo = false;
        
        System.out.println("[Partida " + idPartida + "] ¡Terminada! Ganador: " + 
                         ganador.getNombreJugador());
        
        // Notificar resultado a ambos jugadores
        ganador.enviarMensaje(new MensajeFinPartida(true, ganador.getNombreJugador()));
        perdedor.enviarMensaje(new MensajeFinPartida(false, ganador.getNombreJugador()));
        
        // Cerrar conexiones después de un pequeño delay (para que lleguen los mensajes)
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                jugador1.cerrar();
                jugador2.cerrar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    /**
     * Maneja la desconexión de un jugador
     */
    public void jugadorDesconectado(ManejadorCliente jugador) {
        if (juegoActivo) {
            System.out.println("[Partida " + idPartida + "] " + jugador.getNombreJugador() + 
                             " se desconectó. Partida terminada.");
            
            ManejadorCliente oponente;
            if (jugador == jugador1) {
                oponente = jugador2;
            } else {
                oponente = jugador1;
            }
            // ==============================
            
            if (oponente != null) {
                oponente.enviarMensaje(new MensajeGenerico(Mensaje.TipoMensaje.ERROR,
                    "Tu oponente se desconectó. Has ganado por default."));
                oponente.cerrar();
            }
            
            juegoActivo = false;
        }
    }
    
    // Getters
    public int getIdPartida() {
        return idPartida;
    }
    
    public boolean isJuegoActivo() {
        return juegoActivo;
    }
}