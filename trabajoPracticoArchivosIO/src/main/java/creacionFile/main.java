package creacionFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class main {
	
	static PrintStream ps = new PrintStream(System.out);
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		File file = new File("hola.txt");
		ps.print("Ingrese un método (1-2):");
		int aux = Integer.parseInt(br.readLine());
		switch(aux) {
		case 1:
			ps.print("Ingrese un método:");
			int aux2 = Integer.parseInt(br.readLine());
			switch(aux2) {
			case 1:
				creacionConsolaArchivos.consola();
				break;
			case 2:
				creacionConsolaArchivos.files(file);
				break;
			}
		case 2:
			leerDatosNumericos.leer(creacionConsolaArchivos.consola());
			break;
		}
	}
}