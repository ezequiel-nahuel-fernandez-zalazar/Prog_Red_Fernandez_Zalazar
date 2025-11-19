# Prog_Red_Fernandez_Zalazar

# TP 4 - Socket Mini-chat SEGURO
Desarrollar un sistema de chat donde los clientes puedan intercambiar mensajes de texto y archivos, mientras que el servidor redirige los mensajes a todos los clientes conectados, además de permitir la mensajería privada. Todos los mensajes deben ser encriptados para asegurar la privacidad, y los comandos específicos deben estar disponibles para los usuarios.

Funcionalidad básica de chat:

Los clientes pueden enviar mensajes de texto a todos los usuarios conectados.
Los mensajes privados pueden ser enviados a un cliente específico mediante el comando /msg [usuario] [mensaje].
Los mensajes deben ser encriptados antes de ser enviados al servidor, asegurando que el servidor no pueda leer el contenido del mensaje.
Comandos disponibles:
Los clientes deben poder usar los siguientes comandos desde la consola:

/salir: Cierra la sesión del cliente y desconecta al usuario.
/listar: Muestra una lista de todos los usuarios conectados al chat.
/verComandos: Muestra una lista de todos los comandos disponibles.
/msg [usuario] [mensaje]: Envía un mensaje privado a un cliente específico.
/enviarArchivo [usuario] [archivo]: Envía un archivo a un cliente específico.
/ayuda: Muestra información de ayuda sobre cómo utilizar el chat y los comandos disponibles.
Redirección de mensajes:

Los mensajes enviados por los clientes deben ser recibidos por todos los demás clientes conectados al chat, salvo que se trate de un mensaje privado, en cuyo caso solo el destinatario recibirá el mensaje.
Los mensajes deben ser enviados por el servidor de manera que todos los clientes los reciban (utilizando hilos y sockets).
Encriptación de mensajes:

Todos los mensajes deben ser encriptados antes de ser enviados y desencriptados al ser recibidos. El servidor no podrá ver ni modificar los contenidos de los mensajes. Se puede utilizar alguna de las siguientes librerías para la encriptación:
AES (Advanced Encryption Standard)
RSA (Rivest-Shamir-Adleman)
La encriptación debe ser transparente para el usuario; es decir, los usuarios no deben notar ningún cambio en la interacción, pero los mensajes estarán protegidos
Interfaz y consola con colores:

Diferenciar entre los mensajes enviados por el cliente y los mensajes enviados por el servidor en la consola utilizando códigos ANSI:
para los mensajes del servidor o mensajes de éxito.
para los mensajes del cliente.
para mensajes de error.
para mensajes informativos de sistema (como comandos).
Envío de archivos:

Los clientes deben poder enviar archivos a otros usuarios mediante el comando /enviarArchivo [usuario] [archivo].
El servidor debe recibir el archivo y redirigirlo al cliente destinatario, asegurando la correcta recepción y almacenamiento.
Manejo de conexiones:

El servidor debe manejar múltiples clientes simultáneamente utilizando hilos (Thread).
El servidor debe desconectar al cliente correctamente cuando este envíe el comando /salir o cierre la conexión.
Interfaz de Usuario (CLI):

Los clientes deben tener una interfaz de línea de comandos (CLI) donde pueden ver los mensajes del servidor y de otros clientes, escribir sus propios mensajes, y ejecutar comandos como los mencionados anteriormente.
El cliente debe mostrar mensajes como:
"Conexión exitosa al servidor."
"Comando no válido, intente /verComandos."
"Archivo enviado correctamente."
"Usuario no encontrado para el mensaje privado."
