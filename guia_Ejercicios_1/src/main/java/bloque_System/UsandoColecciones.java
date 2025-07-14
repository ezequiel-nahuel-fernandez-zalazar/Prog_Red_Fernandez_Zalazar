package bloque_System;

import java.io.*;
import java.util.*;

public class UsandoColecciones {
	 private static List<Integer> valores = new ArrayList<>();
	 private static int suma = 0;

	    public static void leerValores() throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String entrada;
	        System.out.println("Introduce números enteros (termina con -99):");
	        while (true) {
	            entrada = br.readLine();
	            int num = Integer.parseInt(entrada);
	            if (num == -99) break;
	            valores.add(num);
	        }
	    }

	    public static void calcularSuma() {
	        for (int n : valores) {
	            suma += n;
	        }
	    }

	    public static void mostrarResultados() {
	        System.out.println("Total de valores leídos: " + valores.size());
	        System.out.println("Suma: " + suma);
	        double media = (valores.size() > 0) ? (double) suma / valores.size() : 0;
	        System.out.println("Media: " + media);

	        int mayores = 0;
	        System.out.println("Valores introducidos:");
	        for (int n : valores) {
	            System.out.println(n);
	            if (n > media) mayores++;
	        }

	        System.out.println("Cantidad de valores mayores que la media: " + mayores);
	    }

	    private static Map<String, List<String>> alumnos = new HashMap<>();

	    public static void addAlumno(String apellido, String nacionalidad) {
	        alumnos.putIfAbsent(nacionalidad, new ArrayList<>());
	        alumnos.get(nacionalidad).add(apellido);
	        System.out.println("Alumno añadido: " + apellido + " (" + nacionalidad + ")");
	    }

	    public static void showAll() {
	        if (alumnos.isEmpty()) {
	            System.out.println("No hay datos.");
	            return;
	        }
	        for (String nacionalidad : alumnos.keySet()) {
	            int cantidad = alumnos.get(nacionalidad).size();
	            System.out.println("Nacionalidad: " + nacionalidad + " - Cantidad: " + cantidad);
	        }
	    }

	    public static void showNacionalidad(String nacionalidad) {
	        List<String> lista = alumnos.get(nacionalidad);
	        if (lista == null || lista.isEmpty()) {
	            System.out.println("No hay alumnos de nacionalidad " + nacionalidad);
	            return;
	        }
	        System.out.println("Nacionalidad: " + nacionalidad);
	        for (String apellido : lista) {
	            System.out.println(apellido);
	        }
	        System.out.println("Cantidad: " + lista.size());
	    }

	    public static void cuantos() {
	        System.out.println("Nacionalidades diferentes: " + alumnos.keySet().size());
	    }

	    public static void borra() {
	        alumnos.clear();
	        System.out.println("Datos eliminados.");
	    }

	    public static void menu() throws IOException{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String opcion;
	        do {
	            System.out.println("\n1. Añadir alumno");
	            System.out.println("2. Mostrar todas las nacionalidades");
	            System.out.println("3. Mostrar alumnos por nacionalidad");
	            System.out.println("4. Contar nacionalidades diferentes");
	            System.out.println("5. Borrar todos los datos");
	            System.out.println("6. Salir");
	            System.out.print("Opción: ");
	            opcion = br.readLine();

	            switch (opcion) {
	                case "1":
	                    System.out.print("Apellido: ");
	                    String apellido = br.readLine();
	                    System.out.print("Nacionalidad: ");
	                    String nacionalidad = br.readLine();
	                    addAlumno(apellido, nacionalidad);
	                    break;
	                case "2":
	                    showAll();
	                    break;
	                case "3":
	                    System.out.print("Nacionalidad a consultar: ");
	                    String nac = br.readLine();
	                    showNacionalidad(nac);
	                    break;
	                case "4":
	                    cuantos();
	                    break;
	                case "5":
	                    borra();
	                    break;
	                case "6":
	                    System.out.println("Fin del programa.");
	                    break;
	                default:
	                    System.out.println("Opción inválida.");
	            }
	        } while (!opcion.equals("6"));
	    }

	    public static void barcelona(){
	        Set<String> jugadores = new HashSet<>();
	        jugadores.add("Jordi Alba");
	        jugadores.add("Pique");
	        jugadores.add("Busquets");
	        jugadores.add("Iniesta");
	        jugadores.add("Messi");

	        System.out.println("Jugadores del FC Barcelona:");
	        for (String jugador : jugadores) {
	            System.out.println(jugador);
	        }

	        String jugadorBuscado = "Neymar JR";
	        if (jugadores.contains(jugadorBuscado)) {
	            System.out.println(jugadorBuscado + " está en el conjunto.");
	        } else {
	            System.out.println(jugadorBuscado + " no está en el conjunto.");
	        }

	        Set<String> jugadores2 = new HashSet<>();
	        jugadores2.add("Piqué");
	        jugadores2.add("Busquets");

	        if (jugadores.containsAll(jugadores2)) {
	            System.out.println("Todos los jugadores de jugadores2 están en jugadores.");
	        } else {
	            System.out.println("No todos los jugadores de jugadores2 están en jugadores.");
	        }

	        jugadores.addAll(jugadores2);
	        System.out.println("Conjunto después de la unión:");
	        for (String jugador : jugadores) {
	            System.out.println(jugador);
	        }

	        boolean añadido = jugadores.add("Piqué");
	        if (!añadido) {
	            System.out.println("\"Piqué\" ya existía en el conjunto, no se ha agregado de nuevo.");
	        } else {
	            System.out.println("\"Piqué\" fue agregado al conjunto.");
	        }
	    }

	    
	    public static void semana(){
	        List<String> listaDias = new ArrayList<>(Arrays.asList(
	            "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
	        ));

	        listaDias.add(4, "Juernes");

	        List<String> listaDos = new ArrayList<>(listaDias);

	        listaDias.addAll(listaDos);

	        System.out.println("Posición 3: " + listaDias.get(3));
	        System.out.println("Posición 4: " + listaDias.get(4));

	        System.out.println("Primer elemento: " + listaDias.get(0));
	        System.out.println("Último elemento: " + listaDias.get(listaDias.size() - 1));

	        boolean eliminado = listaDias.removeIf(dia -> dia.equals("Juernes"));
	        System.out.println("¿Se eliminó 'Juernes'?: " + eliminado);

	        System.out.println("Lista con Iterador:");
	        Iterator<String> it = listaDias.iterator();
	        while (it.hasNext()) {
	            System.out.println(it.next());
	        }

	        boolean existeLunes = listaDias.stream().anyMatch(d -> d.equalsIgnoreCase("Lunes"));
	        System.out.println("¿Existe 'Lunes' (sin importar mayúsculas)? " + existeLunes);

	        Collections.sort(listaDias);
	        System.out.println("Lista ordenada:");
	        for (String dia : listaDias) {
	            System.out.println(dia);
	        }
	    }	    
	        public static void generarApuesta() throws IOException {
	        Random random = new Random();

	        Set<Integer> bolasRojas = new TreeSet<>();
	        while (bolasRojas.size() < 6) {
	            int numero = random.nextInt(33) + 1;
	            bolasRojas.add(numero);
	        }

	        int bolaAzul = random.nextInt(16) + 1;

	        System.out.println("Bolas rojas: " + bolasRojas);
	        System.out.println("Bola azul: [" + bolaAzul + "]");
	    }
	    
}
