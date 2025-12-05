package Comun.mensajes;

public class MensajeCambioTurno extends Mensaje {
    private static final long serialVersionUID = 1L;
    
    private boolean esMiTurno;
    
    public MensajeCambioTurno(boolean esMiTurno) {
        super(TipoMensaje.CAMBIO_TURNO);
        this.esMiTurno = esMiTurno;
        setContenido(esMiTurno ? "Es tu turno" : "Turno del oponente");
    }
    
    public boolean isEsMiTurno() {
        return esMiTurno;
    }
}