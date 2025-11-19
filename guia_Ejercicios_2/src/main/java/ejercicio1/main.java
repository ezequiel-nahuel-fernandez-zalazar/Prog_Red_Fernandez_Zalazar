package ejercicio1;

public class main {

	public static void main(String[] args) {
		Thread proceso = new Thread(new HiloAlfanumerico());
		proceso.start();
	}

}
