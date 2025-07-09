package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.FileWriter;

public class main {
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	static PrintStream ps = new PrintStream(System.out);
	static PrintStream err = new PrintStream(System.err);
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args){		
		try{
			File file = new File("inventario.dat");
			while(true) {
				ps.print("Ingrese un número:");
				String aux = files.leerDato();
				switch(aux) {
				case "agregarproducto":
					return;
				case "mostrar":
					return;
				case "salir":
					return;
				default:
					ps.println(ANSI_RED + "Opción no válida, intente de nuevo.");
					break;
				}
			}
		}catch(IOException e) {
			err.print(e);
		}
	}

}
