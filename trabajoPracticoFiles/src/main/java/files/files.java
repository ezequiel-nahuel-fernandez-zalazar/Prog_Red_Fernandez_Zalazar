package files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class files {
	
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	static PrintStream ps = new PrintStream(System.out);
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintStream err = new PrintStream(System.err);
	
	public static String leerDato() throws IOException{
		String aux = br.readLine().toLowerCase();
		String palabra = aux.replaceAll("\\s+", "");
		palabra = palabra.trim();
		ps.print(ANSI_GREEN + "¡El código funciona correctamente!");
		return palabra;
		}
	
	public static String stringLecture(){
		String text = null;
		ps.print("Ingrese texto: ");
		try {
			text = br.readLine();
		}catch(IOException e) {
			err.print(e);
		}
		ps.print(ANSI_GREEN + "¡El código funciona correctamente!");
		return text;
	}
	
	public static String textLecture(){
		String text = null;
		Double textSub = 0.0;
		try {
			ps.print("Ingrese algo: ");
			text = br.readLine();
			text = text.trim();
			if(text.isEmpty()) {
				ps.print(ANSI_RED + "Esta vacío no se puede hacer ninguna conversión.");
			}else {
				textSub = Double.parseDouble(text);
				if((textSub % 1.0) == 0.0) {
					ps.print(ANSI_GREEN + "Se trata de un Integer.");
				}else {
					ps.print(ANSI_GREEN + "Se trata de un Double.");
				}
			}
		}catch(IOException e) {
			err.print(e);
		}catch(NumberFormatException e) {
			err.print("No es un número.");
		}
		return text;
	}

	public static Integer stringtoInt() {
		String text = null;
		int textSub = 0;
		try{
			ps.print("Ingrese un dato: ");
			text = br.readLine();
			textSub = Integer.parseInt(text);
			ps.print(ANSI_GREEN + "El resultado en formato Integer es: " + ANSI_YELLOW + textSub);
		}catch(IOException e){
			err.print(e);
		}
		return textSub;
	}

	public static Integer stringtoFloat() {
		String text = null;
		float textSub = 0;
		try{
			ps.print("Ingrese un dato: ");
			text = br.readLine();
			textSub = Float.parseFloat(text);
			ps.print(ANSI_GREEN + "El resultado en formato Float es: " + ANSI_YELLOW + textSub);
		}catch(IOException e){
			err.print(e);
		}
		return null;
	}

	public static String[] creatingData() {
		String[] data = new String[4];
		
		while (true) {
	        try {
	            System.out.print("Ingrese el nombre del producto: ");
	            String nombre = br.readLine();
	            if (nombre != null && !nombre.trim().isEmpty()) {
	                data[0] = nombre.trim();
	                break;
	            }
	            System.out.println("El nombre no puede estar vacío.");
	        } catch (IOException e) {
	            System.out.println("Error al ingresar el nombre.");
	        }
	    }

	    // Precio de compra
	    while (true) {
	        try {
	            System.out.print("Ingrese el precio de compra: ");
	            String input = br.readLine();
	            input.replace(",", ".");
	            Float.parseFloat(input); 
	            data[1] = input.trim();
	            break;
	        } catch (Exception e) {
	            System.out.println("Ingrese un número válido para el precio de compra.");
	        }
	    }

	    // Precio de venta
	    while (true) {
	        try {
	            System.out.print("Ingrese el precio de venta: ");
	            String input = br.readLine();
	            input.replace(",", ".");
	            Float.parseFloat(input); 
	            data[2] = input.trim();
	            break;
	        } catch (Exception e) {
	            System.out.println("Ingrese un número válido para el precio de venta.");
	        }
	    }

	    // Stock
	    while (true) {
	        try {
	            System.out.print("Ingrese el stock: ");
	            String input = br.readLine();
	            Integer.parseInt(input); 
	            data[3] = input.trim();
	            break;
	        } catch (Exception e) {
	            System.out.println("Ingrese un número entero válido para el stock.");
	        }
	    }

	    return data;
	}
	public static void creatingInv() {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter("inventario.dat");
			bw = new BufferedWriter(fw);
		}catch(FileNotFoundException e){
			Logger.getLogger(files.class.getName()).log(Level.WARNING, null, e);
		}catch(IOException e) {
			Logger.getLogger(files.class.getName()).log(Level.WARNING, null, e);
		}finally {
			try {
				if(fw != null) {fw.close();}
				if(bw != null) {bw.close();}
				}catch(IOException e) {
					err.print(e.getMessage());
					}
			}
		ps.print(ANSI_GREEN + "Se creo la plantilla" + ANSI_YELLOW + " inventario.dat");
	}

	public static void addInv(File f, String[] data) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			bw.write(data[0]+";"+data[1]+";"+data[2]+";"+data[3]);
			bw.newLine();
			bw.flush();
		}catch(IOException e) {
			err.print(e.getMessage());
		}
	}

	public static void readPlainText(File originalFile) {
		File dupFile = new File(originalFile.getAbsoluteFile() + ".txt");
		StringBuilder sb= new StringBuilder();
		
		try(
				BufferedReader br = new BufferedReader(new FileReader(originalFile));
				BufferedWriter bw = new BufferedWriter(new FileWriter(dupFile));
				){
			String line = "";
			while((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				String strLine = br.readLine();
				ps.print(strLine);
			}
			br.close();
		}catch(IOException e) {
			err.print("IOException: " + e.getMessage());
		}
		
		ps.print(ANSI_GREEN + "¡El código funciona correctamente!");
	}

	public static void readData(File f){
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			
			String line = "";
			String texto = "";
			while((line = br.readLine()) != null) {
				texto = texto.concat(line).replace(";", " ");
			}
			String[] finalText = texto.split(" ");
			ps.println(ANSI_GREEN + "Su nombre es: "+ ANSI_YELLOW + finalText[0]);
			ps.println(ANSI_GREEN + "Su precio de compra es de: "+ ANSI_YELLOW + finalText[1]);
			ps.println(ANSI_GREEN + "Su precio de venta es de: "+ ANSI_YELLOW + finalText[2]);
			ps.println(ANSI_GREEN + "Su stock es de: "+ ANSI_YELLOW + finalText[3]);
		}catch(FileNotFoundException e) {
			Logger.getLogger(files.class.getName()).log(Level.WARNING, null, e);
		}catch(IOException e) {
			Logger.getLogger(files.class.getName()).log(Level.WARNING, null, e);
		}finally {
			try {
				if(fr != null) {fr.close();}
				if(br != null) {br.close();}
			}catch(IOException e) {
				Logger.getLogger(files.class.getName()).log(Level.WARNING, null, e);
			}
		}
	}

	public static void deleteData(File f) throws IOException {
		File archTemp = new File( f.getAbsolutePath() + ".tmp");
		FileReader fr = null;
		ps.print("Que desea cambiar?: ");
		String search = br.readLine();
		try(
				BufferedReader br = new BufferedReader( new FileReader(f) );
				BufferedWriter bw = new BufferedWriter( new FileWriter(archTemp) );
				){
			String linea = "";
			while( (linea = br.readLine()) != null){
				if(linea.contains(search)){
					linea = linea.replace(search, "");
				}
				bw.write(linea);
				bw.newLine();
			}
			
			ps.print(ANSI_RED + "¡Se ha borrado exitosamente!");
			
			if(!f.delete())
				throw new IOException("No se pudo borrar el archivo original");  
				
			if( !archTemp.renameTo(f) )
				throw new IOException("No puedo renombrar el archivo temporal.");
			
		}catch(FileNotFoundException e) {
			Logger.getLogger(files.class.getName()).log(Level.WARNING, null, e);
		}catch(IOException e) {
			Logger.getLogger(files.class.getName()).log(Level.WARNING, null, e);
		}finally {
			try {
				if(fr != null) {fr.close();}
				if(br != null) {br.close();}
			}catch(IOException e) {
				Logger.getLogger(files.class.getName()).log(Level.WARNING, null, e);
			}
		}
	}

	public static void changeData(File f) throws IOException {
	File archTemp = new File( f.getAbsolutePath() + ".tmp");
	FileReader fr = null;
	ps.print("Que desea cambiar?: ");
	String search = br.readLine();
	ps.print("Que desea cambiar?: ");
	String changed = br.readLine();
	try(
			BufferedReader br = new BufferedReader( new FileReader(f) );
			BufferedWriter bw = new BufferedWriter( new FileWriter(archTemp) );
			){
		String linea = "";
		while( (linea = br.readLine()) != null){
			if(linea.contains(search)){
				linea = linea.replace(search, changed);
			}
			bw.write(linea);
			bw.newLine();
		}
		
		ps.print(ANSI_GREEN + "¡Se ha cambiado exitosamente!");
		if(!f.delete())
			throw new IOException("No se pudo borrar el archivo original");  
			
		if( !archTemp.renameTo(f) )
			throw new IOException("No puedo renombrar el archivo temporal.");
		
	}catch(FileNotFoundException e) {
		Logger.getLogger(files.class.getName()).log(Level.WARNING, null, e);
	}catch(IOException e) {
		Logger.getLogger(files.class.getName()).log(Level.WARNING, null, e);
	}finally {
		try {
			if(fr != null) {fr.close();}
			if(br != null) {br.close();}
		}catch(IOException e) {
			Logger.getLogger(files.class.getName()).log(Level.WARNING, null, e);
		}
	}
}

}
