package examen_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

public class main {
	
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_MAGENTA = "\u0033[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

	public static void main(String[] args) throws IOException {
		File f = new File("datos.dat");
		List<String> Datos=resolucion.lectura(f);
		f.delete();
		boolean continuar = true;
		PrintStream ps = new PrintStream(System.out);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option = 0;
		while(continuar) {
			ps.println("================================================");
			ps.println("| 1 | Agregar Datos Nuevos Al Archivo de Texto.");
			ps.println("| 2 | Eliminar Datos Nuevos Al Archivo de Texto.");
			ps.println("| 3 | Mostrar datos existentes.");
			ps.println("| 4 | Salir.");
			ps.println("================================================");
			try {
				String input = br.readLine();
				if(input != null && ! input.isEmpty()) {
					option = Integer.parseInt(input);
				}else {
					ps.println("Ingrese un valor válido:");
					continue;
				}
			}catch(IOException e){
				ps.print(e);
				}
			switch(option) {
			case 1:
				resolucion.agregarDatos();
				break;
			case 2:
				ps.print("	Ingrese el reglón que desea eliminar:");
				int valor = Integer.parseInt(br.readLine());
				resolucion.eliminarDato(valor);
				break;
			case 3:
				resolucion.mostrarDatos(Datos);
				break;
			case 4:
				ps.println("Saliendo del programa");
				continuar = false;
			}
		}
	}
}
