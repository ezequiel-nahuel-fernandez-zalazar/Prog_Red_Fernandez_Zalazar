package ejercicio5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class LeerArchivo implements Runnable{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintStream ps = new PrintStream(System.out);
	static PrintStream err = new PrintStream(System.err);
	
	private static int lineasTotales = 0;
	private static final Object lock = new Object();
	
	private final File arch;
	
	public LeerArchivo(File arch) {
		this.arch = arch;
	}
	
	public int leerArchivos() {
		FileReader fr = null;
		BufferedReader br = null;
		int count = 0;
		try {
			fr = new FileReader(arch);
			br = new BufferedReader(fr);
			String line = "";
			String texto = "";
			while((line = br.readLine()) != null) {
				count++;
			}
		}catch(FileNotFoundException e) {
			Logger.getLogger(LeerArchivo.class.getName()).log(Level.WARNING, null, e);
		}
		catch(IOException e) {
			Logger.getLogger(LeerArchivo.class.getName()).log(Level.WARNING, null, e);
		}finally {
			try {
				if(fr != null) fr.close();
				if(br != null) br.close();
			}catch(IOException e) {
				Logger.getLogger(LeerArchivo.class.getName()).log(Level.WARNING, null, e);
			}
		}
		return count;
	}

	public void run() {
		int lineas = leerArchivos();
		JOptionPane.showMessageDialog(null, "El archivo " + arch.getName() + " tiene " + lineas + " líneas.");
		synchronized(lock) {
			lineasTotales += lineas;
			}
		}
	
	public static int getLineasTotales() {
		return lineasTotales;
		}
	}
