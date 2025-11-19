package ejercicio7;

public class main {

	public static void main(String[] args) throws InterruptedException {
		Thread registro = new Thread(new RegistroEmpleados());
        Thread control = new Thread(new ControlAsistencia());
        
		registro.start();
		registro.join();
		control.join();
		control.start();
		}

}
