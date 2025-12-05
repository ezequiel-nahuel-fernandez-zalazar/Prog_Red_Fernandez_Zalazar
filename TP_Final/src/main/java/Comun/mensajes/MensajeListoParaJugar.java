package Comun.mensajes;

/**
 * Mensaje que envía el cliente cuando terminó de posicionar todos sus barcos
 */
public class MensajeListoParaJugar extends Mensaje {
    private static final long serialVersionUID = 1L;
    
    public MensajeListoParaJugar() {
        super(TipoMensaje.LISTO_PARA_JUGAR, "Jugador listo para iniciar");
    }
}