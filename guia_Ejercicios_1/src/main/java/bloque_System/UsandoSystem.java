package bloque_System;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Locale;

public class UsandoSystem {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintStream ps = new PrintStream(System.out);
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
	
	public static void primer_Ejercicio(){//Metodo de clase, Exclusivo de la misma
		try {
			ps.print("Ingrese el valor por hora: ");
			String txt = leerLinea();
			int valorHora = Integer.parseInt(txt);
			ps.println("Valor de sus horas: " + valorHora);
			ps.print("Ingrese el valor de horas trabajadas: ");
			txt = leerLinea();
			int horaTrabajo = Integer.parseInt(txt);
			ps.println("Sus horas trabajadas: " + horaTrabajo);
			int sueldoBruto = valorHora * horaTrabajo;
			ps.println("Su sueldo bruto es de: " + sueldoBruto + " Pesos Argentinos");
			ps.flush();
		}catch(IOException e){}
	}
	public static void segundo_Ejercicio(){
		try {
			ps.print("Ingrese su primer ángulo: ");
			int ang1 = Integer.parseInt(leerLinea());
			ps.println("Su ángulo es de: " + ang1);
			//
			ps.print("Ingrese su segundo ángulo: ");
			int ang2 = Integer.parseInt(leerLinea());
			ps.println("Su ángulo es de: " + ang2);
			int total;
			if((ang1 + ang2) < 180){
				total = (180 - (ang1 + ang2));
				ps.println("Su tercer ángulo es de: " + total);
			}else {
				ps.println("Esto no puede ocurrir, ya que el último ángulo será de 0°");
			}
			ps.flush();
		}catch(IOException e){
			err.print("Se ingresaron los datos de manera incorrecta.");
		}
	}
	public static void tercer_Ejercicio(){
		try {
			ps.print("Ingrese la superficie del cuadrado en metros cuadrados: ");
			int sup = Integer.parseInt(leerLinea());
			double lado = Math.sqrt(sup);
			int perimeter = (int) (4*lado);
			ps.printf("El perimetro del valor ingresado es de: %d", perimeter);
		}catch(IOException e) {
			err.print("Se ingresaron los datos de manera incorrecta.");
		}
	}
	public static void cuarto_Ejercicio(){
		try {
			ps.print("Ingrese su temperatura en Fahrenheits: ");
			int tempF = Integer.parseInt(leerLinea());
			int tempC = (tempF-32) * 5/9;
			ps.println("Su temperatura en Celsius es de: "+ tempC);
			ps.flush();
		}catch(IOException e){
			err.print("Se ingresaron los datos de manera incorrecta.");
		}
	}
	public static void quinto_Ejercicio(){
		ps.println("Ingrese su valor en segundos: ");
		try {
			int sec = Integer.parseInt(leerLinea());
			if (sec < 0) {
				ps.println("Por favor ingrese un valor positivo para los segundos.");
	            return;
			}
			int day = sec/86400;
			int restDay = sec-(day*86400);
			int hour = restDay/3600;
			int restHour = restDay - (hour*3600);
			int min = restHour/60;
			int restSec = restHour % 60;
			ps.println(String.format("Días: %d, Horas: %d, Minutos: %d, Segundos: %d", day, hour, min, restSec));
			ps.flush();
		}catch(IOException e){
			err.print("Se ingresaron los datos de manera incorrecta.");
		}
		
	}
	public static void sexto_Ejercicio(){
		try {
			ps.print("Ingrese el valor del producto: ");
			int valor = Integer.parseInt(leerLinea());
			ps.print("Ingrese que plan desea aplicar: ");
			int plan = Integer.parseInt(leerLinea());
			switch(plan) {
			case 1:
				int desc = (int)(valor * 0.10);
				int precioFinal = valor - desc;
				ps.printf("El valor final del producto será de: %d", precioFinal);
			case 2:
				int aux = (int)(valor * 1.10);
				int precioCont = aux/2;
				int cuotas = (aux/2) / 2;
				ps.printf("El valor final del producto será de: %d, pagarás al contado : %d y luego 2 cuotas de: %d", aux, precioCont, cuotas);
			case 3:
				float aux1 = (float) (valor * 1.15);
				float precioCont1 = (float) (aux1*0.25);
				float cuotas1 = (aux1-precioCont1) / 5;
				ps.printf("El valor final del producto será de: %g, pagarás al contado : %g y luego 5 cuotas de: %g", aux1, precioCont1, cuotas1);
			case 4:
				float aux2 = (float)(valor*1.25);
				float primCuotas = (float) ((aux2*0.60)/4);
				float ultimCuotas = (float) (aux2*0.40/4);
				ps.printf("El valor final del producto será de: %g, las primeras 4 cuotas son de: %g y las últimas son de: %g", aux2, primCuotas, ultimCuotas);
			}
			ps.flush();
		}catch(IOException e){
			err.print("Se ingresaron los datos de manera incorrecta.");
		}
	}
	public static void septimo_Ejercicio(){
		ps.println("Ingrese su signo zodiacal: ");
			try {
				String signo = leerLinea();
				signo = signo.toLowerCase();
					switch(signo){
					case "aries":
				        ps.println("Su fecha de nacimiento está entre 21 de marzo y 19 de abril");
				        break;
				    case "tauro":
				        ps.println("Su fecha de nacimiento está entre 20 de abril y 20 de mayo");
				        break;
				    case "geminis":
				        ps.println("Su fecha de nacimiento está entre 21 de mayo y 20 de junio");
				        break;
				    case "cancer":
				        ps.println("Su fecha de nacimiento está entre 21 de junio y 22 de julio");
				        break;
				    case "leo":
				        ps.println("Su fecha de nacimiento está entre 23 de julio y 22 de agosto");
				        break;
				    case "virgo":
				        ps.println("Su fecha de nacimiento está entre 23 de agosto y 22 de septiembre");
				        break;
				    case "libra":
				        ps.println("Su fecha de nacimiento está entre 23 de septiembre y 22 de octubre");
				        break;
				    case "escorpio":
				        ps.println("Su fecha de nacimiento está entre 23 de octubre y 21 de noviembre");
				        break;
				    case "sagitario":
				        ps.println("Su fecha de nacimiento está entre 22 de noviembre y 21 de diciembre");
				        break;
				    case "capricornio":
				        ps.println("Su fecha de nacimiento está entre 22 de diciembre y 19 de enero");
				        break;
				    case "acuario":
				        ps.println("Su fecha de nacimiento está entre 20 de enero y 18 de febrero");
				        break;
				    case "piscis":
				        ps.println("Su fecha de nacimiento está entre 19 de febrero y 20 de marzo");
				        break;
					}
					ps.flush();
			}catch(IOException e){
				err.print("Se ingresaron los datos de manera incorrecta.");
				}
			}
	}