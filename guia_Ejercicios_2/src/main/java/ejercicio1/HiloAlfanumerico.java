package ejercicio1;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;


public class HiloAlfanumerico implements Runnable{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintStream ps = new PrintStream(System.out);
	static PrintStream err = new PrintStream(System.err);
	
	private void calculo(int tipo) {
		List<String> letras = new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","ñ","o","p","q","r","s","t","u","v","w","x","y","z"));
		
		if(tipo == 1) {
			for(int i = 1 ; i < 30 + 1; i++) {
				ps.println(i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(tipo == 2) {
			for(int i = 0; i <letras.size(); i++) {
				ps.println(letras.get(i));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void run() {
		try {
			ps.print("Ingrese un valor númerico del 1 al 2: ");
			int tipo = Integer.parseInt(br.readLine());
			calculo(tipo);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}