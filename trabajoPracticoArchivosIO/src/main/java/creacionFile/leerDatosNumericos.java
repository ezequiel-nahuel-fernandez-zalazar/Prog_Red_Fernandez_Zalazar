package creacionFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class leerDatosNumericos {
	static BufferedWriter bw = null;
	static BufferedWriter bwRes = null;
	static FileWriter fw = null;
	static FileWriter fwRes = null;
	static PrintStream ps = new PrintStream(System.out);
	static PrintStream err = new PrintStream(System.err);
	
	public static void leer(Vector<Integer> data){
			try {
				bw = new BufferedWriter(fw = new FileWriter("error.txt"));
				bwRes = new BufferedWriter(fwRes = new FileWriter("response.txt"));
				for(int i = 0; i < data.size()-1; i++) {
					float numerador = data.get(i);
					float dividendo = data.get(i+1)-3;
					if(dividendo == 0 || (dividendo == 0 && numerador == 0)){
						bw.write(numerador + "/" + dividendo + " = " + (numerador/dividendo) + " Error por dividendo = 0 o por ser Indeterminado.");
						bw.newLine();
					}else {
						if((i+1) >= (data.size()-1)){
							bwRes.write(numerador + "/" + 0 + " = " + numerador/0 + " El denominador se pasó del index del vector, por lo que se lo reemplaza por '0'.");
							bwRes.newLine();
						}else {
							bwRes.write(numerador + "/" + dividendo + " = " + (numerador/dividendo));
							bwRes.newLine();
						}
						}
					}
				} catch (FileNotFoundException e) {
					Logger.getLogger(leerDatosNumericos.class.getName()).log(Level.WARNING, null, e);
				} catch (IOException e) {
					err.print(e);
				}finally {
					try {
						if(bwRes != null) {
							bwRes.close();
						}
						if(fwRes != null) {
							fwRes.close();
						}
						if(bw != null) {
							bw.close();
						}
						if(fw != null){
							fw.close();
						}
					}catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	}