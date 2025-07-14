package bloque_System;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class main{
	
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_UNDERLINE = "\u001B[4m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	public static void main(String[] args) throws IOException{
		while (System.in.available() > 0) {
		    System.in.read();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintStream err = new PrintStream(System.err);
		while(true) {
			System.out.println(ANSI_PURPLE + "==================================================" + ANSI_RESET);
			System.out.println("	Bienvenido al menu infinito");
			System.out.println("	Seleccione una opcion entre:");
			System.out.println("	1 - Usando System");
			System.out.println("	2 - Usando Reader");
			System.out.println("	3 - Usando Files");
			System.out.println("	4 - Usando Colecciones");
			System.out.println(ANSI_PURPLE + "==================================================" + ANSI_RESET);
			try {
				System.out.print("	Seleccione que Bloque quiere usar: ");
				int aux = Integer.parseInt(br.readLine());
				switch(aux){
				case 1:
					System.out.println(ANSI_PURPLE + "==================================================" + ANSI_RESET);
					System.out.println("	Usted eligio el bloque de Usando System class");
					System.out.println("	Seleccione una opcion entre 1 y 8.");
					System.out.println(ANSI_PURPLE + "==================================================" + ANSI_RESET);
					System.out.print("	Seleccione que Ejercicio quiere ejecutar: ");
					int aux2 = Integer.parseInt(br.readLine());
					switch(aux2) {
						case 1:
							UsandoSystem.primer_Ejercicio();
							break;
						case 2:
							UsandoSystem.segundo_Ejercicio();
							break;
						case 3:
							UsandoSystem.tercer_Ejercicio();
							break;
						case 4:
							UsandoSystem.cuarto_Ejercicio();
							break;
						case 5:
							UsandoSystem.quinto_Ejercicio();
							break;
						case 7:
							UsandoSystem.septimo_Ejercicio();
							break;
						default:
							return;
						}
				case 2:
					System.out.println(ANSI_PURPLE + "==================================================" + ANSI_RESET);
					System.out.println("	Usted eligio el bloque de Usando Reader class");
					System.out.println("	Seleccione una opcion entre 1 y 8.");
					System.out.println(ANSI_PURPLE + "==================================================" + ANSI_RESET);
					System.out.println("	Seleccione que Ejercicio quiere ejecutar: ");
					int aux3 = Integer.parseInt(br.readLine());
					switch(aux3) {
					case 1:
						UsandoReader.primer_ejercicio();
						break;
					case 2:
						UsandoReader.segundo_ejercicio();
						break;
					case 3:
						UsandoReader.tercer_ejercicio();
						break;
					case 4:
						UsandoReader.cuarto_ejercicio();
						break;
					case 5:
						UsandoReader.quinto_ejercicio();
						break;
					case 6:
						UsandoReader.sexto_ejercicio();
						break;
					case 7:
						UsandoReader.septimo_ejercicio();
						break;
					case 8:
						UsandoReader.octavo_ejercicio();
						break;
						}
				case 3:
					System.out.println(ANSI_PURPLE + "==================================================" + ANSI_RESET);
					System.out.println("	Usted eligio el bloque de Usando Files");
					System.out.println("	Seleccione una opcion entre 1 y 9.");
					System.out.println(ANSI_PURPLE + "==================================================" + ANSI_RESET);
					System.out.print("	Seleccione que Ejercicio quiere ejecutar: ");
					int aux4 = Integer.parseInt(br.readLine());
					switch(aux4) {
					case 1:
						UsandoFiles.leerConsola(aux4);
						break;
					case 2:
						UsandoFiles.leerNumeros();
						break;
					case 3:
						UsandoFiles.guardarPares();
						break;
					case 4:
						UsandoFiles.leerPares();
						break;
					case 5:
						UsandoFiles.borrarRenglones();
						break;
					case 6:
						UsandoFiles.guardarPrimos();
						break;
					case 7:
						UsandoFiles.caracteres();
						break;
					case 8:
						UsandoFiles.leerHTML();
						break;
					case 9:
						UsandoFiles.clima();
						break;
					default:
						return;
						}
				case 4:
					System.out.println(ANSI_PURPLE + "==================================================" + ANSI_RESET);
					System.out.println("	Usted eligio el bloque de Usando Colecciones");
					System.out.println("	Seleccione una opcion entre 1 y 5.");
					System.out.println(ANSI_PURPLE + "==================================================" + ANSI_RESET);
					System.out.print("	Seleccione que Ejercicio quiere ejecutar: ");
					int aux5 = Integer.parseInt(br.readLine());
					switch(aux5) {
						case 1:
							UsandoColecciones.leerValores();
							UsandoColecciones.calcularSuma();
							UsandoColecciones.mostrarResultados();
							break;
						case 2:
							UsandoColecciones.menu();
							break;
						case 3:
							UsandoColecciones.semana();
							break;
						case 4:
							UsandoColecciones.barcelona();
							break;
						case 5:
							UsandoColecciones.generarApuesta();
							break;
						default:
							return;
						}
					}
					
				}		
			catch(IOException | NumberFormatException e){
				err.println("	Datos ingresados en el menu fueron incorrectos");
			}
		}
		}
}
