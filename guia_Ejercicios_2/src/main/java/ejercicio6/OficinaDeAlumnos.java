package ejercicio6;

import java.util.ArrayList;
import java.util.List;

public class OficinaDeAlumnos implements Runnable {

    public static List<Alumnos> listaAlumnos = new ArrayList<>();

    @Override
    public void run() {
        listaAlumnos.add(new Alumnos("Benjamín", "Castro Madrid"));
        listaAlumnos.add(new Alumnos("Melina", "Victoria Godoy"));
        listaAlumnos.add(new Alumnos("Román", "Ábalos Ishida"));
        listaAlumnos.add(new Alumnos("Emilia", "Flores Luna"));

        System.out.println("OficinaDeAlumnos: se cargaron " + listaAlumnos.size() + " alumnos.\n");
    }
}
