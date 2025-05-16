package Guia_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class main{
	public static void main(String[] args){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintStream err = new PrintStream(System.err);
		while(true) {
			System.out.println("Seleccione una opción entre 1 y 2.");
			try {
				System.out.print("Seleccione que Punto quiere usar: ");
				int aux = Integer.parseInt(br.readLine());
				switch(aux){
				case 1:
					System.out.println("Usted eligió el Punto 1");
					System.out.println("Seleccione una opción entre 1 y 8.");
					System.out.print("Seleccione que Ejercicio quiere ejecutar: ");
					int aux2 = Integer.parseInt(br.readLine());
					switch(aux2) {
						case 1:
							Ejercicios_Punto1_System.primer_Ejercicio();
							break;
						case 2:
							Ejercicios_Punto1_System.segundo_Ejercicio();
							break;
						case 3:
							Ejercicios_Punto1_System.tercer_Ejercicio();
							break;
						case 4:
							Ejercicios_Punto1_System.cuarto_Ejercicio();
							break;
						case 5:
							Ejercicios_Punto1_System.quinto_Ejercicio();
							break;
						case 7:
							Ejercicios_Punto1_System.septimo_Ejercicio();
							break;
						default:
							return;
						}
				case 2:
					System.out.println("Usted eligió el Punto 2");
					System.out.println("Seleccione una opción entre 1 y 8.");
					System.out.print("Seleccione que Ejercicio quiere ejecutar: ");
					int aux3 = Integer.parseInt(br.readLine());
					switch(aux3) {
					case 1:
						Ejercicios_Punto2_Reader.primer_ejercicio();
						break;
					case 2:
						Ejercicios_Punto2_Reader.segundo_ejercicio();
						break;
					case 3:
						Ejercicios_Punto2_Reader.tercer_ejercicio();
						break;
					case 4:
						Ejercicios_Punto2_Reader.cuarto_ejercicio();
						break;
					case 5:
						Ejercicios_Punto2_Reader.quinto_ejercicio();
						break;
					case 6:
						Ejercicios_Punto2_Reader.sexto_ejercicio();
						break;
					case 7:
						Ejercicios_Punto2_Reader.septimo_ejercicio();
						break;
					case 8:
						Ejercicios_Punto2_Reader.octavo_ejercicio();
						break;
						}
					}
				}		
			catch(IOException | NumberFormatException e){
				err.println("Error: datos ingresados mal");
			}
		}
		}
}