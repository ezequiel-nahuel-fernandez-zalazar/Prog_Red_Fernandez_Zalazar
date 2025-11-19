package ejercicio4;

public class main {

	public static void main(String[] args) {
		int matriz [][] = new int [4][4];
		matriz[0][0] = 5;
		matriz[0][1] = 10;
		matriz[0][2] = 2;
		matriz[0][3] = 4;
		matriz[1][0] = 0;
		matriz[1][1] = 12;
		matriz[1][2] = 6;
		matriz[1][3] = 8;
		matriz[2][0] = 9;
		matriz[2][1] = 15;
		matriz[2][2] = 3;
		matriz[2][3] = 8;
		matriz[3][0] = 17;
		matriz[3][1] = 13;
		matriz[3][2] = 20;
		matriz[3][3] = 16;
		
		int matriz2 [][] = new int [4][4];
		matriz2[0][0] = 5;
		matriz2[0][1] = 10;
		matriz2[0][2] = 2;
		matriz2[0][3] = 4;
		matriz2[1][0] = 0;
		matriz2[1][1] = 12;
		matriz2[1][2] = 6;
		matriz2[1][3] = 8;
		matriz2[2][0] = 9;
		matriz2[2][1] = 15;
		matriz2[2][2] = 3;
		matriz2[2][3] = 8;
		matriz2[3][0] = 17;
		matriz2[3][1] = 13;
		matriz2[3][2] = 20;
		matriz2[3][3] = 16;
		
		Thread proceso = new Thread(new primerThreads(matriz, matriz2, 0, 0));
		Thread proceso2 = new Thread(new primerThreads(matriz, matriz2, 1, 0));
		Thread proceso3 = new Thread(new primerThreads(matriz, matriz2, 2, 0));
		Thread proceso4 = new Thread(new primerThreads(matriz, matriz2, 3, 0));
		
		try {
			proceso.start();
			Thread.sleep(1000);
			proceso2.start();
			Thread.sleep(1000);
			proceso3.start();
			Thread.sleep(1000);
			proceso4.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
