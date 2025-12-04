Programación Sobre Redes 2025

Gonzalez Herrera Agustina Sol
Mail: gonzalez.h.agustina@gmail.com
Mail secundario: gus.g.backup@gmail.com

Ezequiel Nahuel Fernadnez Zalazar
Mail:ezequiel.fernandez.zalazar.e.t32.co@gmail.com

6to1ra Computación
ET32 de14
Gonzalo Nicolás Consorti
Profesor de la materia



Contenido de la rama



Consigna

Desarrollar un juego de Batalla Naval para dos jugadores utilizando programación en red mediante sockets TCP/IP, gestión de hilos concurrentes y manejo de flujos de datos en Java.


Deberán implementar una versión multijugador del clásico juego Batalla Naval, donde dos jugadores conectados a través de una red puedan competir en tiempo real. El proyecto debe estar compuesto por una aplicación servidor y una aplicación cliente.

Requisitos Funcionales
1. Mecánica del Juego
Tablero: Cada jugador posee un tablero de 10x10 casillas
Barcos: Cada jugador debe posicionar los siguientes barcos:
1 Portaaviones (5 casillas)
1 Acorazado (4 casillas)
1 Crucero (3 casillas)
1 Submarino (3 casillas)
1 Destructor (2 casillas)
Posicionamiento: Los barcos pueden colocarse horizontal o verticalmente, sin superposición
Objetivo: Hundir todos los barcos del oponente antes que él hunda los tuyos

2. Arquitectura Cliente-Servidor 

Servidor
Debe aceptar conexiones de dos clientes (que juegan entre si)
Gestionar el estado del juego para ambos jugadores
Coordinar los turnos alternados
Validar los movimientos de cada jugador
Notificar el resultado de cada disparo (agua, tocado, hundido)
Determinar y notificar al ganador
Permitir múltiples partidas simultáneas ( crear una sala para que varios jugadores puedan jugar, ósea en pares de 2 jugadores pero el servidor puede permitir varias partidas )

Cliente
Conectarse al servidor proporcionando IP y puerto
Permitir al jugador posicionar sus barcos al inicio
Mostrar dos tableros: el propio y el del oponente
Enviar coordenadas de disparo durante su turno
Recibir y mostrar el resultado de los disparos (propios y del oponente)
Actualizar la interfaz según el estado del juego
3. Comunicación y Protocolo
Utilizar para la comunicación cliente-servidor
Definir un claro para: Conexión inicial y autenticación de jugadores
Envío de posiciones de barcos
Envío de coordenadas de disparo
Respuestas del servidor (resultado del disparo)
Notificaciones de cambio de turno
Fin de partida
Los mensajes de movimiento enviados deben ser serializables (objetos, JSON)
(ver sección de estructura de codoco al final)

4. Gestión de Hilos
El servidor debe usar para manejar múltiples clientes simultáneamente
Cada conexión de cliente debe ser atendida en un hilo separado
Implementar sincronización apropiada
Garantizar que el estado del juego se mantenga consistente entre hilos

5. Manejo de Flujos de Datos
Utilizar correctamente las clases de I/O de Java:InputStream / OutputStream
ObjectInputStream / ObjectOutputStream (si se serializan objetos)
BufferedReader / PrintWriter (si se usa texto)
Implementar manejo robusto de excepciones de I/O
Cerrar correctamente todos los recursos (try-with-resources recomendado)
Requisitos Técnicos Obligatorios
Implementación correcta de ServerSocket y Socket
Uso de Thread para concurrencia
Captura y gestión de IOException, SocketException, etc.
Código organizado en clases con propósitos claros
Debe ser por consola 

Opcionales (Puntos Extra)
Sistema de sala de espera para emparejar jugadores
Chat entre jugadores durante la partida
Estadísticas y historial de partidas (guardada en archivos de texto)
Múltiples partidas simultáneas en el servidor
Estructura de codigo:

1- ArrayList de ArrayList
ArrayList<ArrayList<Celda>> tablero;
- Representa una matriz dinámica de 10x10
- Cada posición contiene un objeto de tipo `Celda`

2- Enum TipoCelda

Debe contener los siguientes valores: 
- `AGUA` - Celda sin barco 
- `BARCO` - Celda con parte de un barco
- `IMPACTO` - Celda con barco que fue disparada 
- `FALLO` - Celda de agua que fue disparada

3- public Celda() { 
this.tipo = TipoCelda.AGUA; 
this.disparada = false; 
this.nombreBarco = null; 
}


4-public Barco(String nombre, int longitud) { 
this.nombre = nombre; 
this.longitud = longitud; 
this.impactos = 0;

}

- `recibirImpacto()` - Incrementa el contador de impactos
- `estaHundido()` - Retorna true si impactos >= longitud
 
- Getters para todos los atributos

5- Clase Tablero:
 - `tablero` (ArrayList<ArrayList<Celda>>) - Grilla del juego 
- `barcos` (ArrayList<Barco>) 
- Lista de barcos colocados
- `TAMANIO` (int) - Constante con valor 10

6-. Inicialización 
- `inicializarTablero()`
- Crea el ArrayList de ArrayList con celdas de agua 

Colocación de Barcos 
- `colocarBarco(String nombre, int fila, int col, int longitud, boolean horizontal)` 
- Valida que el barco quepa en el tablero 
- Verifica que no haya colisiones con otros barcos 
- Coloca el barco en las posiciones correspondientes 
- Retorna `true` si se colocó exitosamente, `false` en caso contrario

Disparar 
- `disparar(int fila, int col)` 
- Valida la posición ingresada
 
- Verifica que no se haya disparado previamente en esa celda 
- Actualiza el estado de la celda (IMPACTO o FALLO)

- Si impacta un barco, registra el impacto 
- Retorna un String con el resultado: "¡IMPACTO!", "¡HUNDIDO!", "Agua", etc.

Visualización
 
- `mostrarTablero(boolean ocultarBarcos)` 
- Muestra el tablero en consola con formato de grilla 
- Si `ocultarBarcos` es `true`, no muestra barcos intactos (para el oponente)

- Si `ocultarBarcos` es `false`, muestra todo (tablero propio) 
- Usa símbolos: `~` (agua), `B` (barco), `X` (impacto), `O` (fallo)

`mostrarBarcos()` 
- Lista todos los barcos con su estado actual
 
- Muestra: nombre, longitud, impactos recibidos, estado (ACTIVO/HUNDIDO)

7. Condiciones de Victoria/Derrota 
- `todosLosBarcosCaidos()`
- Retorna `true` si todos los barcos están hundidos 
- Retorna `false` si al menos un barco está activo

- `verificarVictoria()`
- Muestra mensaje de victoria si todos los barcos enemigos fueron hundidos 

- `verificarDerrota()`
- Muestra mensaje de derrota si todos los barcos propios fueron hundidos
