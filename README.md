# Prog_Red_Fernandez_Zalazar

Alumno: Ezequiel Nahuel Fernandez Zalazar

Correo: ezequiel.fernandez.zalazar.e.t32.co@gmail.com

Curso y division: 6° 1° Computación

Docente: Gonzalo Consorti

Materia: Administración de sistemas y redes

# TP 3 - Socket transmisión Archivos
Implementar un sistema de transmisión de archivos entre un cliente y un servidor utilizando sockets en Java, con comunicación punto a punto. El cliente enviará un archivo al servidor y el servidor lo recibirá y guardará en el sistema local. Además, el cliente debe permitir la selección dinámica de los archivos a enviar y mostrar mensajes de estado de la conexión y de la transmisión utilizando colores en la consola.

Servidor:

El servidor debe escuchar en un puerto específico (por ejemplo, 5000).
Aceptar la conexión de un cliente.
Recibir el archivo enviado por el cliente.
Guardar el archivo recibido en el sistema de archivos local.
Mostrar mensajes de estado, indicando la recepción del archivo.
Cliente:

El cliente debe conectarse al servidor a través de un socket.
Permitir al usuario elegir el archivo que desea enviar mediante un JOptionPane para mostrar una ventana de selección de archivos.
Mostrar mensajes de estado usando colores para indicar:Conexión exitosa al servidor.
Envío exitoso de archivo.
Error en la transmisión o conexión.
El cliente debe poder enviar múltiples archivos de forma secuencial (uno a uno), y después de cada envío, dar al usuario la opción de enviar otro archivo o finalizar la transmisión.
Colores en la Consola: Utilizar códigos ANSI para mostrar mensajes en diferentes colores en la consola:

para indicar éxito (por ejemplo, conexión establecida o archivo enviado correctamente).
para indicar errores (por ejemplo, problemas de conexión o transmisión fallida).
para mensajes informativos (por ejemplo, inicio de conexión o espera de acción del usuario).
Elección de archivos (Cliente):

El cliente debe permitir la selección de archivos mediante una ventana de selección (usando JOptionChoiser).
El cliente debe preguntar al usuario si desea enviar otro archivo después de cada transferencia exitosa.
Condiciones adicionales:

Utilizar sockets TCP (clase Socket y ServerSocket).
El archivo debe ser transmitido en bloques de datos (por ejemplo, utilizando un buffer de 4 KB).
Los flujos de entrada y salida deben manejarse correctamente.
El cliente y el servidor deben manejar adecuadamente las excepciones (errores de conexión, lectura/escritura de archivos, etc.).
Este es el detalle de los nuevos requisitos:

Selección dinámica de archivos: El cliente puede elegir cualquier archivo de su sistema para enviarlo al servidor mediante un cuadro de diálogo de selección de archivos (

JOptionChoiser ).

Colores en consola: Usar códigos ANSI para que el texto de la consola se muestre en diferentes colores (verde para éxito, rojo para error, azul para mensajes informativos).

Enviar múltiples archivos: El cliente puede enviar varios archivos de manera secuencial.
