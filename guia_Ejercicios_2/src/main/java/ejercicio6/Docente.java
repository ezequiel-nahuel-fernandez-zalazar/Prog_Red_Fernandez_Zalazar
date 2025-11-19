package ejercicio6;

import java.util.Random;

public class Docente implements Runnable {

    @Override
    public void run() {
        Random r = new Random();
        for (Alumnos a : OficinaDeAlumnos.listaAlumnos) {
            int[] notas = new int[3];
            for (int i = 0; i < notas.length; i++) {
                notas[i] = r.nextInt(10) + 1;
            }
            a.setNotas(notas);
        }
        System.out.println("Docente: notas asignadas.\n");
    }

    public static void calcularPromedios() {
        System.out.println("Promedios finales:");
        for (Alumnos a : OficinaDeAlumnos.listaAlumnos) {
            System.out.println(a.getNombre() + " " + a.getApellido() +
                    "Promedio: " + a.getPromedio());
        }
        System.out.println();
    }
}
