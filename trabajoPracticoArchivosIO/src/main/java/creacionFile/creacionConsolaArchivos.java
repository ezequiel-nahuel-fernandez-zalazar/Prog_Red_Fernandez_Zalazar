package creacionFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Logger;

public class creacionConsolaArchivos {
	static PrintStream ps = new PrintStream(System.out);
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(isr);
	static PrintStream err = new PrintStream(System.err);
	
	public static Vector<Integer> consola(){
		int count = 0;
		int countSec = 0;
		Vector<Integer> datos = new Vector<Integer>();
		try {
			while(count < 5){
				ps.print("Ingrese su dato: ");
				Integer dato = Integer.parseInt(br.readLine());
				datos.add(dato);
				count++;
				}
			for(int i = 0; i < datos.size(); i++){
				if(datos.get(i) == 0) {
					countSec = countSec + 1;
				}
			}
			if(countSec >= 2) {
				ps.println(datos);
				ps.print("Esta todo correcto.");
			}else {
				err.print("El array debe de tener al menos dos ceros.");
				}
		}catch(IOException | NumberFormatException e) {
			err.print(e);
		}
		return datos;
	}

	public static String files(File f){
		FileWriter fw = null;
		BufferedWriter bw = null;
		int count = 0;
		
		try {
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			for(int i = 0; i < 5; i++) {
				int texto;
				ps.print("Ingrese su valor: ");
				texto = Integer.parseInt(br.readLine());
				if(texto == 0) {
					count++;
				}
				String newTexto = Integer.toString(texto);
				bw.write(newTexto);
				bw.newLine();
				bw.flush();
			}
			if(count < 2) {
				err.print("El archivo debe contener al menos dos ceros.");
			}else {
				ps.print("Esta perfecto.");
			}
		} catch (IOException e) {
			Logger.getLogger(creacionConsolaArchivos.class.getName()).log(null);
		} finally {
			try {
				if (fw != null)
					fw.close();
				if (bw != null)
					bw.close();
			} catch (IOException e) {
				Logger.getLogger(creacionConsolaArchivos.class.getName()).log(null);
			}
			
		}
		return f.toString();
	}
}
