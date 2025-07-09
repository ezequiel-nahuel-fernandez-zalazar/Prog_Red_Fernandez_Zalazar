package examen_1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.events.Characters;

public class resolucion {
	
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_MAGENTA = "\u0033[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
	
	static PrintStream ps = new PrintStream(System.out);
	static PrintStream err = new PrintStream(System.err);
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {		
	}

	public static List<String> lectura(File f) {
		FileReader fr = null;
		BufferedReader br = null;
		List<String> lineas = new ArrayList<>();
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String linea;
			int count = 0;
			while((linea = br.readLine()) != null) {
				linea = linea.replace(".", ";");
				lineas.add(linea);

			}
		}catch (IOException e) {
			Logger.getLogger(resolucion.class.getName()).log(Level.WARNING, null, e);
		} finally {
			try {
				if (fr != null)
					fr.close();
				if(br != null)
					br.close();
			} catch (IOException e) {
				Logger.getLogger(resolucion.class.getName()).log(Level.WARNING, null, e);
			}
		}
		return lineas;

	}

	public static List<String> conversion(List<String> lista) throws IOException{
		FileWriter fw = null;
		FileWriter fwErr = null;
		BufferedWriter bwErr = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter("tuti-fruti.csv",false);
			bw = new BufferedWriter(fw);
			fwErr = new FileWriter("ERROR.log");
			bwErr = new BufferedWriter(fwErr);
			
			for(int i = 0; i < lista.size(); i++) {
				String prueba = lista.get(i);
				char prueba2 = prueba.charAt(1);
				prueba2 = Character.toUpperCase(prueba2);
				bw.write(prueba2 + prueba);
				bw.newLine();
			} 
			
		} catch (IOException e) {
			Logger.getLogger(resolucion.class.getName()).log(Level.WARNING, null, e);
			fwErr = new FileWriter("ERROR.log");
			bwErr = new BufferedWriter(fwErr);
			bwErr.write("Error al leer dato:" + e);
		} finally {
			try {
				if (bw != null)
					bw.close();
				if(fw != null)
					fw.close();
			} catch (IOException e) {
				Logger.getLogger(resolucion.class.getName()).log(Level.WARNING, null, e);
			}
		}
		return lista;
	}

	public static void eliminarDato(int ingreso) {
		File F = new File("tuti-fruti.csv");
		BufferedWriter bw = null;
		FileWriter fw = null;
		PrintStream ps = new PrintStream(System.out);
		List<String> lineas = new ArrayList<>();
		lineas = resolucion.lectura(F);
	
		if(ingreso < 1 || ingreso > lineas.size()) {
			ps.println("Número inválido");
			return;
		}
		
		lineas.remove(ingreso-1);
		
		try {
			fw = new FileWriter(F);
			bw = new BufferedWriter(fw);
			for(String linea: lineas) {
				bw.write(linea);
				ps.println(linea);
				bw.newLine();
			}
			ps.println("Linea " + ingreso + " Eliminada");
		} catch (IOException e) {
			Logger.getLogger(resolucion.class.getName()).log(Level.WARNING, null, e);

		} finally {
			try {
				if (bw != null)
					bw.close();
				if(fw != null)
					fw.close();
			} catch (IOException e) {
				Logger.getLogger(resolucion.class.getName()).log(Level.WARNING, null, e);
				}
			}
		}
	
	public static void mostrarDatos(List<String> lista) throws IOException {
		File F = new File("tuti-fruti.csv");
		List<String> lineas = resolucion.lectura(F);
		ps.printf(ANSI_PURPLE + "Color" + " " + "animal" +" " + "cosa"+" "+ "comida" + ANSI_RESET + "\n");
		int count = 0;
		for(int i = 1; i < lista.size(); i++) {
			if(count == 0) {
				ps.printf(ANSI_BLUE + lista.get(i).replace(";", " ").trim()+ ANSI_RESET + "\n");
				count = count + 1;
			}else {
				ps.printf(ANSI_YELLOW + lista.get(i).replace(";", " ").trim()+ ANSI_RESET + "\n");
				count = 0;
			}
		}
	}
	
	public static void agregarDatos() {

	    File f = new File("tuti-fruti.csv");
	    List<String> lista = resolucion.lectura(f);
	    List<Character> usado = new ArrayList<>();
	    List<Character> disponible = new ArrayList<>();
	    String abecedario = "abcdefghijklmnñopqrstuvwxyz";
	    FileWriter fw = null;
	    BufferedWriter bw = null;
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    for (String linea : lista) {
	        if (linea.length() > 0) {
	            char letra = Character.toLowerCase(linea.charAt(0));
	            if (!usado.contains(letra)) {
	                usado.add(letra);
	            }
	        }
	    }
	    for (int i = 0; i < abecedario.length(); i++) {
	        char letra = abecedario.charAt(i);
	        if (!usado.contains(letra)) {
	            disponible.add(letra);
	        }
	    }
	    try {

	        fw = new FileWriter("tuti-fruti.csv", true);
	        bw = new BufferedWriter(fw);

	        String aux = "";
	        boolean letraValida = false;

	        while (!letraValida) {
	            System.out.print("Ingrese una letra: ");
	            aux = br.readLine().toLowerCase();

	            if (aux.length() == 1 && disponible.contains(aux.charAt(0))) {
	                letraValida = true;
	            } else {
	                System.out.print("Letra ya usada o inválida. Letras disponibles: ");
	                for (char c : disponible) {
	                    System.out.print(c + " ");
	                }
	                System.out.println();
	            }
	        }

	        char letraFinal = aux.charAt(0);
	        char letraprint = Character.toUpperCase(letraFinal);
	        String color = "";
	        String animal = "";
	        String cosa = "";
	        String comida = "";

	        while (true) {
	        	ps.print("Color: ");
	            color = br.readLine();
	            if (color.toLowerCase().startsWith(String.valueOf(letraFinal))) break;
	            ps.println("Debe comenzar con la letra '" + letraFinal + "'.");
	        }

	        while (true) {
	        	ps.print("Animal: ");
	            animal = br.readLine();
	            if (animal.toLowerCase().startsWith(String.valueOf(letraFinal))) break;
	            ps.println("Debe comenzar con la letra '" + letraFinal + "'.");
	        }

	        while (true) {
	        	ps.print("Cosa: ");
	            cosa = br.readLine();
	            if (cosa.toLowerCase().startsWith(String.valueOf(letraFinal))) break;
	            ps.println("Debe comenzar con la letra '" + letraFinal + "'.");
	        }

	        while (true) {
	        	ps.print("Comida: ");
	            comida = br.readLine();
	            if (comida.toLowerCase().startsWith(String.valueOf(letraFinal))) break;
	            ps.println("Debe comenzar con la letra '" + letraFinal + "'.");
	        }
	        bw.write(letraprint + "," + color + "," + animal + "," + cosa + "," + comida);
	        bw.newLine();
	    } catch (IOException e) {
	        Logger.getLogger(resolucion.class.getName()).log(Level.WARNING, null, e);
	    } finally {
	        try {
	            if (bw != null)
	                bw.close();
	            if (fw != null)
	                fw.close();
	        } catch (IOException e) {
	            Logger.getLogger(resolucion.class.getName()).log(Level.WARNING, null, e);
	        }

	    }

	}
}



