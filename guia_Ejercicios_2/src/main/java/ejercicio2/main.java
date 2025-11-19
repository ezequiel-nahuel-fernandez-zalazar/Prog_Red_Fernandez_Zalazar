package ejercicio2;
public class main {

	public static int randomConteo(int min, int max) {
		int rango = (max - min) + 1;
		int random = (int) ((rango * Math.random()) + min);
		return random;
	}
	
	public static void main(String[] args) {
		for(int i = 1; i < 3 + 1 ; i++) {
			Thread proceso = new Thread(new Contador());
			proceso.start();
		}
		
	}
}
