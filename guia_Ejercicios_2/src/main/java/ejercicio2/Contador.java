package ejercicio2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.swing.*;

public class Contador implements Runnable {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintStream ps = new PrintStream(System.out);
	static PrintStream err = new PrintStream(System.err);
	
	public static int randomConteo(int min, int max) {
		int rango = (max - min) + 1;
		int random = (int) ((rango * Math.random()) + min);
		return random;
	}

	
	private void Count(int count, String Tname, int countLimit){
		long start = System.currentTimeMillis();
		for(int i = count ; i < countLimit + 1; i++) {
			try {
				int random = randomConteo(200,1000);
				Thread.sleep(random);
				ps.println(i + ", " + Tname);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis() - start;
		var output = "El hilo ejecutado se llama: " + Tname + " y tardó: " + end + "ms.";
		JOptionPane.showMessageDialog(null, output);	
	}
	
	public void run() {
		int minNum , maxNum ;
		do {
			minNum = randomConteo(1,10);
			maxNum = randomConteo(1,10);
		}while( minNum >= maxNum );
		int random = randomConteo(3,10);
		Count(minNum,"Hilo"+ random,maxNum);
	}
}
