package ejercicio4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class primerThreads implements Runnable {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintStream ps = new PrintStream(System.out);
	static PrintStream err = new PrintStream(System.err);
	
	private final int[][] matriz;
	private final int[][] matriz2;
	private int i;
	private int j;
	
	public primerThreads(int[][] matriz, int[][] matriz2, int i, int j) {
		this.matriz = matriz;
		this.matriz2 = matriz2;
		this.i = i;
		this.j = j;
	}
	
	public void run() {
		int matrizF [][] = new int [4][4];
		//long end = System.currentTimeMillis() - start;
		long start = System.currentTimeMillis();
		for(int f = i; f < 4; f++){
			for(int c= j; c < 4; c++) {
				matrizF[f][c] = matriz[f][c] * matriz2[f][c];
			}
		}
		long end = System.currentTimeMillis() - start;
		var output = "El cálculo tardó: " + end;
		ps.println(Arrays.deepToString(matrizF));
		JOptionPane.showMessageDialog(null, output);
	}
}
