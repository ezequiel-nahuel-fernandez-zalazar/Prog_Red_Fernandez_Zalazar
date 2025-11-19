package ejercicio6;

import java.util.Arrays;

public class Alumnos {
    private String nombre;
    private String apellido;
    private int[] asistencia;
    private int[] notas;
    private boolean esAlumnoRegular;

    public Alumnos(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.asistencia = new int[9];
        this.notas = new int[3];
        this.esAlumnoRegular = false;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }

    public int[] getAsistencia() { return asistencia; }
    public void setAsistencia(int[] asistencia) { this.asistencia = asistencia; }

    public int[] getNotas() { return notas; }
    public void setNotas(int[] notas) { this.notas = notas; }

    public boolean isAlumnoRegular() { return esAlumnoRegular; }
    public void setAlumnoRegular(boolean esAlumnoRegular) { this.esAlumnoRegular = esAlumnoRegular; }

    public double getPromedio() {
        int suma = 0;
        for (int n : notas) suma += n;
        return suma / 3.0;
    }

    public double getAsistenciaPromedio() {
        int suma = 0;
        for (int a : asistencia) suma += a;
        return (suma / 9.0);
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", asistencia=" + Arrays.toString(asistencia) +
                ", notas=" + Arrays.toString(notas) +
                ", regular=" + esAlumnoRegular +
                '}';
    }
}
