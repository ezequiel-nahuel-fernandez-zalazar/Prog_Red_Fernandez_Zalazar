package ejercicio3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.swing.*;

public class main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintStream ps = new PrintStream(System.out);
	static PrintStream err = new PrintStream(System.err);

	public static void main(String[] args) {
		try {
			Thread jojo = new Thread(new Johnny());
			Thread gyro = new Thread(new Gyro());
			ps.println("¡Comienza la carrera Steel Ball Run!");
			Thread.sleep(2000);
			jojo.start();
			gyro.start();
			while(true) {
				if(jojo.isAlive() && !gyro.isAlive()) {
					var output = "El ganador es Gyro Zepelli.";
					JOptionPane.showMessageDialog(null, output);
					break;
				}else if(!jojo.isAlive() && gyro.isAlive()) {
					var output = "El ganador es Johnny Joestar.";
					JOptionPane.showMessageDialog(null, output);
					break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
