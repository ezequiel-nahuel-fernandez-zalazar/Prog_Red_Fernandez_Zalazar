package ejercicio7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RegistroEmpleados implements Runnable{
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintStream ps = new PrintStream(System.out);
	static PrintStream err = new PrintStream(System.err);

    public static List<Empleados> listaEmpleados = new ArrayList<>();

	@Override
	public void run() {
		try {
			ps.print("Ingrese cuantos empleados entraron a trabajar hoy: ");
			int totalEmp = Integer.parseInt(br.readLine());
			ps.flush();
			for(int i = 0;i < totalEmp; i++) {
				ps.print("Ingrese el nombre del empleado: ");
				String EmpleadoNombre = br.readLine();
				ps.flush();
				ps.print("Ingrese el apellido del empleado: ");
				String EmpleadoApellido = br.readLine();
				ps.flush();
				ps.print("Ingrese el día en el que asistió: ");
				String DiaAsistido = br.readLine();
				ps.flush();
				ps.print("Ingrese la hora en la que entró: ");
				int HoradeEntrada = Integer.parseInt(br.readLine());
				ps.flush();
				ps.print("Ingrese los minutos de la hora en la que entró: ");
				int MindeEntrada = Integer.parseInt(br.readLine());
				LocalTime Entrada = LocalTime.of(HoradeEntrada, MindeEntrada);
				Empleados Empleado = new Empleados(EmpleadoNombre, EmpleadoApellido, DiaAsistido, Entrada);
				listaEmpleados.add(Empleado);
				if(i != totalEmp) {}
			}
			ps.println(listaEmpleados);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

}
