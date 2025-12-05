package Comun.mensajes;

/**
 * Mensaje enviado por el cliente al conectarse al servidor
 */
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