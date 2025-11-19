package ejercicio6;

public class main {
    public static void main(String[] args) throws InterruptedException {

        Thread oficina = new Thread(new OficinaDeAlumnos());
        oficina.start();
        oficina.join();

        Thread preceptor = new Thread(new Preceptor());
        Thread docente = new Thread(new Docente());

        preceptor.start();
        docente.start();

        preceptor.join();
        docente.join();

        Docente.calcularPromedios();

        System.out.println("Resultado final:");
        for (Alumnos a : OficinaDeAlumnos.listaAlumnos) {
            String estado = a.isAlumnoRegular()
                    ? " | REGULAR"
                    : " | LIBRE (menos de 75% asistencia)";
            System.out.println(a.getNombre() + " " + a.getApellido() + estado + " | Promedio: " + a.getPromedio());
            }
        }
    }
