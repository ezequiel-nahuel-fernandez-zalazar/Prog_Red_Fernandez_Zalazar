package ejercicio7;

import java.time.LocalTime;

public class Empleados{
	
	private String nombre, apellido, dia;
	private LocalTime horario;
	
	public Empleados(String nombre, String apellido, String dia, LocalTime horario) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dia = dia;
		this.horario = horario;
	}
	
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDia() { return dia; }
    public LocalTime getHoraIngreso() { return horario; }

	public String toString(){
		return nombre + " " + apellido + " entro a las " + horario + " del día " + dia;
	}
}
