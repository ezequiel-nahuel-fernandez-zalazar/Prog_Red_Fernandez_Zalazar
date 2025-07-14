package bloque_System;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsandoFiles {
	
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
	
	static PrintStream ps = new PrintStream(System.out);
	static PrintStream err = new PrintStream(System.err);
	
	private static boolean parseInteger(String cambiar) {
		if(cambiar == null) {
			return false;
		}
		try {
			Integer.parseInt(cambiar);
			return true;
		}catch(NumberFormatException e) {
			err.print(e.getMessage());
			return false;
		}
	}
	
    private static boolean esPrimo(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
	
	private static File crearArchivo() {
		File f = new File("..//números.txt");
		return f;
		}
	
	public static void leerConsola(Integer valor) {
		FileWriter fw = null;
		PrintWriter pw = null;
		File f = new File("ultimoDato.txt");
		try {
			fw = new FileWriter(f, false);
			pw = new PrintWriter(fw);
			pw.write(valor);
			pw.flush();
		}catch(FileNotFoundException e){
			Logger.getLogger(UsandoFiles.class.getName()).log(Level.WARNING, null, e);
		}catch(IOException e) {
			Logger.getLogger(UsandoFiles.class.getName()).log(Level.WARNING, null, e);
		}finally {
			try {
				if(fw != null) {fw.close();}
				if(pw != null) {pw.close();}
				}catch(IOException e) {
					err.print(e.getMessage());
					}
			}
		if(f.isFile() && !f.isDirectory()) {
			ps.println(ANSI_UNDERLINE + ANSI_GREEN + "Se creo el archivo correctamente." + ANSI_RESET);
		}else {
			err.println(ANSI_UNDERLINE + "El archivo no pudo ser creado." + ANSI_RESET);
		}
		}
	
	public static void leerNumeros() {
		FileWriter fw = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		File f = new File("valoresNumericos.txt");
		try {
			fw = new FileWriter("valoresNumericos.txt");
			pw = new PrintWriter(fw);
			br = new BufferedReader(new InputStreamReader(System.in));
			ps.print(ANSI_YELLOW_BACKGROUND + "Cuantos valores desea ingresar:\n"+ ANSI_RESET);
			int count = Integer.parseInt(br.readLine());
			for(int i = 1; i < count+1; i++) {
				ps.print(ANSI_YELLOW_BACKGROUND + "Ingrese un valor numerico:\n" + ANSI_RESET);
				String valor = br.readLine();
				if(parseInteger(valor)){
					pw.println(valor);
					pw.flush();
				}else {
					ps.print(ANSI_UNDERLINE + ANSI_RED + "Hubo un error." + ANSI_RESET);
				}
			}
		}catch(FileNotFoundException e){
			Logger.getLogger(UsandoFiles.class.getName()).log(Level.WARNING, null, e);
		}catch(IOException e) {
			Logger.getLogger(UsandoFiles.class.getName()).log(Level.WARNING, null, e);
		}finally {
			try {
				if(fw != null) {fw.close();}
				if(pw != null) {pw.close();}
				}catch(IOException e) {
					err.print(e.getMessage());
					}
			}
		if(f.isFile() && !f.isDirectory()) {
			ps.println(ANSI_UNDERLINE + ANSI_GREEN + "Se creo el archivo correctamente." + ANSI_RESET);
		}else {
			err.println(ANSI_UNDERLINE + "El archivo no pudo ser creado." + ANSI_RESET);
		}
	}
 
	public static void guardarPares() {
		FileWriter fw = null;
		PrintWriter pw = null;
		File f = null;
		try {
			f = crearArchivo();
			if(!(f = new File("..//números.txt")).exists() || f.length() == 0) {
				fw = new FileWriter(f);
				pw = new PrintWriter(fw);
			}else {
				ps.println(ANSI_YELLOW_BACKGROUND + "Archivo ya creado anteriormente." + ANSI_RESET);
			}
			int out = 0;
			for(int i = 0; i < 1000; i++) {
				if(i % 2 == 0) {
					pw.println(i);
					pw.flush();
				}
				else {
					out = out +1;
				}
			}
			ps.println(ANSI_UNDERLINE + ANSI_RED + out + " Valores quedaron fuera del documento." + ANSI_RESET);
			ps.println(ANSI_UNDERLINE + ANSI_GREEN + "Archivo creado correctamente." + ANSI_RESET);
		}catch(NumberFormatException e){
			Logger.getLogger(UsandoFiles.class.getName()).log(Level.WARNING, null, e);
		}catch(IOException e) {
			Logger.getLogger(UsandoFiles.class.getName()).log(Level.WARNING, null, e);
		}finally {
			try {
				if(fw != null) {fw.close();}
				if(pw != null) {pw.close();}
				}catch(IOException e) { err.print(e.getMessage());}
			}
	}
	
	public static void leerPares() {
		FileReader fr = null;
		BufferedReader br = null;
		File f = new File("..//números.txt");
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			if(f.length() != 0) {
				String leido = "";
				String newText = "";
				while((leido = br.readLine()) != null) {
					newText = leido;
					ps.println(newText);
				}
			}else {
				ps.println(ANSI_UNDERLINE + ANSI_RED + "Este archivo esta vacío." + ANSI_RESET);
			}
		}catch(FileNotFoundException e){
			Logger.getLogger(UsandoFiles.class.getName()).log(Level.WARNING, null, e);
		}catch(IOException e) {
			Logger.getLogger(UsandoFiles.class.getName()).log(Level.WARNING, null, e);
		}finally {
			try {
				if(fr != null) {fr.close();}
				if(br != null) {br.close();}
				}catch(IOException e){
					err.print(e.getMessage());
					}
			}
	}
	
	public static void borrarRenglones() {
		File f = crearArchivo();
		File fTemp = new File("..//números.txt" + ".tmp");
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			br = new BufferedReader(new FileReader(f));
			pw = new PrintWriter(new FileWriter(fTemp));
			String leido = "";
			//Hacemos el filtrado por los múltiplos de 3.
			if(f.length() != 0) {
				while((leido = br.readLine()) != null) {
					if(!(Integer.parseInt(leido) % 3 == 0)) {
						pw.println(leido.trim());
						pw.flush();
					}
				}
			}else {
				ps.println(ANSI_UNDERLINE + ANSI_RED + "Este archivo esta vacío." + ANSI_RESET);
			}
			ps.println(ANSI_UNDERLINE + ANSI_GREEN + "Se ha completado la operación." + ANSI_RESET);
		}catch(FileNotFoundException e){
			Logger.getLogger(UsandoFiles.class.getName()).log(Level.WARNING, null, e);
		}catch(IOException e) {
			Logger.getLogger(UsandoFiles.class.getName()).log(Level.WARNING, null, e);
		}finally {
			try {
				if(br != null) {br.close();}
				if(pw != null) {pw.close();}
				
				if(!f.delete()) {
					throw new IOException("No se pudo borrar el archivo original.");
				}
				
				if(!fTemp.renameTo(f)) {
					throw new IOException("No puedo renombrar el archivo temporal.");
				}
				
				}catch(IOException e){
					err.print(e.getMessage());
					}
			}
	}

	public static void guardarPrimos() {
		File f = new File("..//números.txt");
		File b = new File("..//..//primos.dat");
		FileWriter fw = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			pw = new PrintWriter(new FileWriter(b));
			String leido = "";
			String newText = "";
			while((leido = br.readLine()) != null) {
				newText = leido;
				if(esPrimo(Integer.parseInt(newText))) {
					pw.println(newText);
					pw.flush();
				}
			}
			ps.println(ANSI_UNDERLINE + ANSI_GREEN + "Se realizo la operación correctamente." + ANSI_RESET);
		}catch(FileNotFoundException e){
			Logger.getLogger(UsandoFiles.class.getName()).log(Level.WARNING, null, e);
		}catch(IOException e) {
			Logger.getLogger(UsandoFiles.class.getName()).log(Level.WARNING, null, e);
		}finally {
			try {
				if(fw != null) {fw.close();}
				if(pw != null) {pw.close();}
				}catch(IOException e) {
					err.print(e.getMessage());
					}
			}
		if(b.isFile() && !b.isDirectory()) {
			ps.println(ANSI_UNDERLINE + ANSI_GREEN + "Se creo el archivo correctamente." + ANSI_RESET);
		}else {
			err.println(ANSI_UNDERLINE + "El archivo no pudo ser creado." + ANSI_RESET);
		}
	}
	
	public static void caracteres(){
        File archivo = new File("..//..//caracteres.dat");
        File archivoTemp = new File(archivo.getAbsolutePath() + ".tmp");
        
            System.out.println("\nFichero original:");
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

            try (
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                FileWriter fwTemp = new FileWriter(archivoTemp);
                PrintWriter pwTemp = new PrintWriter(fwTemp)
            ) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    pwTemp.println(linea.toLowerCase().replace("ñ", "nie-nio"));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

            archivo.delete();
            archivoTemp.renameTo(archivo);

            System.out.println("\nFichero arreglado:");
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

	public static void leerHTML() {
		File f = new File("index.html");
		File fTemp = new File(f.getAbsoluteFile() + ".tmp");
		FileReader fr = null;
		FileWriter fwt = null;
		PrintWriter pw = null;
		BufferedReader br = null;
        try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            fwt = new FileWriter(fTemp, false);
            pw = new PrintWriter(fwt);
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.toLowerCase().contains("lorem")) {
                    pw.println(linea);
                    pw.flush();
                }
            }

		}catch(NumberFormatException e){
			Logger.getLogger(UsandoFiles.class.getName()).log(Level.WARNING, null, e);
		}catch(IOException e) {
			Logger.getLogger(UsandoFiles.class.getName()).log(Level.WARNING, null, e);
		}finally {
			try {
				if (br != null) br.close();
				if (fr != null) fr.close();
				if (pw != null) pw.close();
				if (fwt != null) fwt.close();
				}catch(IOException e) { err.print(e.getMessage());}
			}
        if (f.delete()) {
            fTemp.renameTo(f);
            System.out.println("Archivo limpio correctamente.");
        } else {
            System.err.println("No se pudo eliminar el archivo original.");
        }
	}

	public static void clima() throws IOException {
		BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
		File archivoClima = new File("..//..//clima.dat");
		
		 while (true) {
	            System.out.println("\n--- MENÚ DE CLIMA ---");
	            System.out.println("1. Agregar registro");
	            System.out.println("2. Mostrar registros");
	            System.out.println("3. Eliminar registro por fecha");
	            System.out.println("4. Salir");
	            System.out.print("Seleccione una opción: ");
	            String opcion = consola.readLine();

	            switch (opcion) {
	                case "1":
	                	System.out.print("Ingrese la fecha (ej: 2025-07-13): ");
	        	        String fecha = consola.readLine();
	        	        System.out.print("Ingrese el clima de ese día: ");
	        	        String clima = consola.readLine();

	        	        try (PrintWriter pw = new PrintWriter(new FileWriter(archivoClima, true))) {
	        	            pw.println(fecha + " - " + clima);
	        	        }

	        	        System.out.println("Registro agregado.");
	                    break;
	                case "2":
	                	 if (!archivoClima.exists() || archivoClima.length() == 0) {
	         	            System.out.println("No hay registros.");
	         	            return;
	         	        }

	         	        try (BufferedReader br = new BufferedReader(new FileReader(archivoClima))) {
	         	            String linea;
	         	            System.out.println("\n--- REGISTROS DE CLIMA ---");
	         	            while ((linea = br.readLine()) != null) {
	         	                System.out.println(linea);
	         	            }
	         	        }
	                    break;
	                case "3":
	        	        if (!archivoClima.exists()) {
	        	            System.out.println("El archivo no existe.");
	        	            return;
	        	        }

	        	        System.out.print("Ingrese la fecha exacta del registro a eliminar (ej: 2025-07-13): ");
	        	        String fechaEliminar = consola.readLine();

	        	        File temp = new File(archivoClima.getAbsolutePath() + ".tmp");
	        	        boolean encontrado = false;

	        	        try (
	        	            BufferedReader br = new BufferedReader(new FileReader(archivoClima));
	        	            PrintWriter pw = new PrintWriter(new FileWriter(temp))
	        	        ) {
	        	            String linea;
	        	            while ((linea = br.readLine()) != null) {
	        	                if (!linea.startsWith(fechaEliminar)) {
	        	                    pw.println(linea);
	        	                } else {
	        	                    encontrado = true;
	        	                }
	        	            }
	        	        }

	        	        if (!archivoClima.delete() || !temp.renameTo(archivoClima)) {
	        	            System.out.println("No se pudo modificar el archivo.");
	        	            return;
	        	        }

	        	        if (encontrado) {
	        	            System.out.println("Registro eliminado.");
	        	        } else {
	        	            System.out.println("No se encontró el registro con esa fecha.");
	        	        }
	                    break;
	                case "4":
	                    System.out.println("Saliendo...");
	                    return;
	                default:
	                    System.out.println("Opción inválida.");
	            }
	        }
	    }

}