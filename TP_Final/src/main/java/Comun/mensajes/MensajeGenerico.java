package Comun.mensajes;

/**
 * Mensaje genérico para comunicaciones simples
 */
public class MensajeGenerico extends Mensaje {
    private static final long serialVersionUID = 1L;
    
    public MensajeGenerico(TipoMensaje tipo, String contenido) {
        super(tipo, contenido);
    }
    
    public MensajeGenerico(TipoMensaje tipo) {
        super(tipo);
    }
}