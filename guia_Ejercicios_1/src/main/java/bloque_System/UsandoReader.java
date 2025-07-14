package bloque_System;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class UsandoReader {
	static InputStreamReader in = new InputStreamReader(System.in);
	static PrintStream ps = new PrintStream(System.out);
	static BufferedReader br = new BufferedReader(in);
	static PrintStream err = new PrintStream(System.err);
	
	public static String leerLinea() throws IOException{
		int aux;
		String palabra = "";
		while( (aux = System.in.read()) != '\n' ){
			if (aux != '\r') {
	            palabra = palabra + (char) aux;
	        }
		}
		return palabra;
	}
	
	public static void primer_ejercicio(){
		try {
			ps.print("Ingrese un primer apellido: ");
			String apellido = leerLinea();
			ps.print("Ingrese un segundo apellido: ");
			String apellido2 = leerLinea();
			ps.print("Ingrese un tercer apellido: ");
			String apellido3 = leerLinea();

			String[] apellidos = { apellido2, apellido, apellido3 };
			Arrays.sort(apellidos);

			ps.print("Su lista de apellidos ordenada sería: ");
			ps.print(String.join(", ", apellidos));
			ps.flush();
		} catch (Exception e) {
			err.println("Error en el ingreso de datos.");
		}
	}
	
	public static void segundo_ejercicio(){
		float num;
		boolean swap;
		ArrayList<Float> numeros = new ArrayList<>();
		try {
			for (int i = 0; i < 4; i++) {
				swap = false;
				ps.print("Ingrese un valor: ");
				num = Float.parseFloat(br.readLine());
				numeros.add(num);
				for (int a = 0; a < numeros.size() - 1; a++) {
					for (int b = 0; b < numeros.size() - a - 1; b++) {
						if (numeros.get(b) > numeros.get(b + 1)) {
							float temp = numeros.get(b);
							numeros.set(b, numeros.get(b + 1));
							numeros.set(b + 1, temp);
						}
					}
				}
			}
			ps.print("Su lista de numeros ordenado de menor a mayor es: ");
			ps.println(numeros);
		} catch (IOException | NumberFormatException e) {
			err.println("Error al leer los números.");
			}
		}

	public static void tercer_ejercicio(){
		try {
			ps.println("Ingrese un número: ");
			int num = Integer.parseInt(br.readLine());
			if(num %2 == 0) {
				ps.print("Este numero es par.");
			}
			ps.flush();
		}catch(IOException | NumberFormatException e) {
			err.println("Este valor no es válido ya sea porque es una letra o porque es de carácter éspecial.");
		}
	}

	public static void cuarto_ejercicio() {
		try {
			int menor;
			int mayor;
			ps.print("Ingrese un primer valor: ");
			int num = Integer.parseInt(br.readLine());
			ps.print("Ingrese un segundo valor: ");
			int num2 = Integer.parseInt(br.readLine());
			if(num > num2) {
				mayor = num2;
				menor = num;}
				else {
					menor = num;
					mayor = num2;
				}
			if(menor % mayor == 0){
				ps.printf("Este número %d es divisible por %d", menor, mayor);
				ps.print("\n");
			}else {
				ps.println("Este numero no es divisible");
			}
			ps.flush();
		}catch(IOException | NumberFormatException e) {
			err.println("Este valor no es válido ya sea porque es una letra o porque es de carácter éspecial.");
		}
	}

	public static void quinto_ejercicio() {
		try {
			ps.print("Introduce el día de nacimiento: ");
			int dia = Integer.parseInt(br.readLine());
			ps.print("Introduce el mes de nacimiento (número): ");
			int mes = Integer.parseInt(br.readLine());
			String signo = "";
			ps.println("Tu signo del zodíaco es: " + signo);
            switch (mes) {
                case 1:
                    signo = (dia <= 19) ? "Capricornio" : "Acuario";
                    break;
                case 2:
                    signo = (dia <= 18) ? "Acuario" : "Piscis";
                    break;
                case 3:
                    signo = (dia <= 20) ? "Piscis" : "Aries";
                    break;
                case 4:
                    signo = (dia <= 19) ? "Aries" : "Tauro";
                    break;
                case 5:
                    signo = (dia <= 20) ? "Tauro" : "Géminis";
                    break;
                case 6:
                    signo = (dia <= 20) ? "Géminis" : "Cáncer";
                    break;
                case 7:
                    signo = (dia <= 22) ? "Cáncer" : "Leo";
                    break;
                case 8:
                    signo = (dia <= 22) ? "Leo" : "Virgo";
                    break;
                case 9:
                    signo = (dia <= 22) ? "Virgo" : "Libra";
                    break;
                case 10:
                    signo = (dia <= 22) ? "Libra" : "Escorpio";
                    break;
                case 11:
                    signo = (dia <= 21) ? "Escorpio" : "Sagitario";
                    break;
                case 12:
                    signo = (dia <= 21) ? "Sagitario" : "Capricornio";
                    break;
                default:
                    System.out.println("Mes no válido.");
                    break;
            }
            
            ps.println("Tu signo zodiacal es: " + signo);
            ps.flush();
		}catch(IOException | NumberFormatException e) {
			err.println("Error al leer los datos.");
		}
}//Fin quinto_ejercicio();

	public static void sexto_ejercicio() {
		try {
			String mayor = "";
			ps.print("Ingrese un primer apellido: ");
			String ape = br.readLine();
			ps.print("Ingrese un segundo apellido: ");
			String ape2 = br.readLine();
			ArrayList<String> apellidos = new ArrayList<>();
			apellidos.add(ape);
			apellidos.add(ape2);
			int aux = 0;
			for(String apellido : apellidos) {
				int largo = apellido.length();
				if(largo > aux){
					String aux2 = apellido;
					aux = largo;
					mayor = aux2;
				}
			}
			ps.printf("El apellido más largo es: %s", mayor);
			ps.println("");
			ps.flush();
		}catch(IOException | NumberFormatException e) {
			err.print("Los datos ingresados no son los correctos.");
		}
	}//Fin sexto_ejercicio();

	public static void septimo_ejercicio() {
		try {
			ps.print("Ingrese un número: ");
			int num = Integer.parseInt(br.readLine());
			switch(num){
			default:
				int mult = num;
				for(int i = 1; i <= 10 ; i++ ) {
					int res = mult * i;
					ps.printf("Su número %d , multiplicado por %d es %d",num, i, res);
					ps.println("\n");
				}
				break;
			}
			ps.flush();
		}catch(IOException | NumberFormatException e){
			err.println("Datos invalidos, ingreselos de vuelta.");
		}
	}//Fin septimo_ejercicio()

	public static void octavo_ejercicio() {
		try {
			ps.print("Introduce un número natural: ");
            int num = Integer.parseInt(br.readLine());

            if (num < 2) {
                ps.println("No es primo ya que los primos son mayores que 1.");
            } else {
                boolean primo = true;
                for (int i = 2; i <= Math.sqrt(num); i++) {
                    if (num % i == 0) {
                        primo = false;
                        break;
                    }
                }
                if (primo) {
                    ps.println(num + " es un número primo.");
                } else {
                    ps.println(num + " no es un número primo.");
                }
            }
            ps.flush();
		}catch(IOException | NumberFormatException e) {
			err.println("Error al leer los datos.");
		}
	}

}