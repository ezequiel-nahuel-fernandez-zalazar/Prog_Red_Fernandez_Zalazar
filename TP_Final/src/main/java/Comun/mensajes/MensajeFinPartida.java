package Comun.mensajes;

public class MensajeFinPartida extends Mensaje {
    private static final long serialVersionUID = 1L;
    
    private boolean gane;
    private String nombreGanador;
    
    public MensajeFinPartida(boolean gane, String nombreGanador) {
        super(TipoMensaje.FIN_PARTIDA);
        this.gane = gane;
        this.nombreGanador = nombreGanador;
        setContenido(gane ? "¡HAS GANADO!" : "Has perdido. Ganador: " + nombreGanador);
    }
    
    public boolean isGane() {
        return gane;
    }
    
    public String getNombreGanador() {
        return nombreGanador;
    }
}