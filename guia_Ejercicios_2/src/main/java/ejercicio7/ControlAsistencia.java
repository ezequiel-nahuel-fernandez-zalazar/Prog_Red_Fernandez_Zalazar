package ejercicio7;

import java.time.LocalTime;

public class ControlAsistencia implements Runnable {

    @Override
    public void run() {
        int revisados = 0;
        LocalTime horaReferencia = LocalTime.of(8, 0); // 8:00 a.m.
        
        while (true) {
            synchronized (RegistroEmpleados.listaEmpleados) {
                while (revisados < RegistroEmpleados.listaEmpleados.size()) {
                    Empleados e = RegistroEmpleados.listaEmpleados.get(revisados);
                    String estado;
                    if (e.getHoraIngreso().isBefore(horaReferencia) || e.getHoraIngreso().equals(horaReferencia)) {
                        estado = "| TEMPRANO";
                    } else {
                        estado = "| TARDE";
                    }
                    System.out.println( e.getNombre() + " (" + e.getDia() + ") " + estado);

                    revisados++;
                }

                if (Thread.activeCount() == 2 && revisados == RegistroEmpleados.listaEmpleados.size()) {
                    System.out.println("Todos los empleados fueron revisados. Programa finalizado.");
                    break;
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
