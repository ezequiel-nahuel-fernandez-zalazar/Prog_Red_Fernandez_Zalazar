package Comun.mensajes;

public class MensajeConexion extends Mensaje {
    private static final long serialVersionUID = 1L;
    
    private String nombreJugador;
    
    public MensajeConexion(String nombreJugador) {
        super(TipoMensaje.CONEXION);
        this.nombreJugador = nombreJugador;
    }
    
    public String getNombreJugador() {
        return nombreJugador;
    }
}