# Prog_Red_Fernandez_Zalazar

Alumno: Ezequiel Nahuel Fernandez Zalazar

Correo: ezequiel.fernandez.zalazar.e.t32.co@gmail.com

Curso y division: 6° 1° Computación

Docente: Gonzalo Consorti

Materia: Administración de sistemas y redes

Consigna de la Guía de Ejercicios 2
Trabajo Práctico Thread / Swing
Guía de ejercicios para practicar temas de creación de Thread, sincronización, esperas, multitareas utilizando en su mayoría interface grafica JOptionPane o Swing.

PROHIBIDO EL USO DE LA CLASS SCANNER

Toda la guía de ejercicios de resuelve con:

Cada ejercicio en un Package diferente con sus respectivos main().
Todos los datos ingresados y leídos DEBEN de ser verificados, validados para que no se ingresen datos erróneos, de lo contrarios pedir que ingrese datos nuevamente.
1. Crea una clase llamada HiloAlfanumerico que implemente runnable y tenga de atributo un número llamado tipo. Si el tipo es 1, mostrara los números del 1 al 30 (todos al mismo tiempo con un intervalo de 100ms). Si el tipo es 2, mostrara las letras de la ‘a’ a la ‘z’ (todos al mismo tiempo con un intervalo de 100ms). Se quedara a la espera que ingrese que tipo se debe mostrar.

2. Crea una class llamada Contador que contenga un atributo que sea un contador, otro que sea el nombre del hilo y otro que sea el límite del contador, es decir, donde debe acabar, mostrar los datos por JOptionPane cuanto tardo el contador en terminar y como se llama ese contador, cada contador debe contar a tiempos distintos entre 200ms a 1000ms.

Crea X cantidad de contadores (de 3 a 10 mínimo, random) y ejecútalos al mismo tiempo.

3. Implemente una simulación de la fábula que cuenta la carrera entre la liebre y la tortuga. Para hacerlo más interesante la carrera será cuesta arriba por una pista resbaladiza, de modo que a veces podrán resbalar y retroceder algunas posiciones. Habrá un thread que implementará la tortuga y otro la liebre. Cada uno se suspenderá durante un segundo y luego evaluará lo que ha pasado según unas probabilidades:

Animal	Suceso	Probabilidad	Movimiento
Tortuga	Avance rápido	50%	3 hacia adelante
Tortuga	Resbaló	20%	6 hacia atrás
Tortuga	Avance lento	50%	1 hacia adelante
Liebre	Duerme	20%	No avanza, ni retrocede
Liebre	Gran Salto	20%	9 hacia adelante
Liebre	Resbalón grande	10%	12 hacia atrás
Liebre	Pequeño salto	30%	1 hacia adelante
Liebre	Resbalón pequeño	20%	2 hacia atrás
Calcule la probabilidad con random de 1 a 100 y determine con dicho número que ha hecho cada animal. Considere que hay 70 casillas, de la 1 a la 70, la 1 de salida y la 70 de llegada. Si resbala al principio vuelve a la 1, nunca por debajo. Tras cada segundo y después de calcular su nueva posición imprima una línea por cada animal, con blancos de 1 la posición-1 y luego una letra T para la tortuga y una L para la liebre. Imprima al comienza de la carrera un mensaje. Después de imprimir las líneas determine si alguno ha llegado a meta y ha ganado, imprimiendo un mensaje. Si ambos llegan a la vez declare un empate (Usando un JTextArea de Swing)

4. Implemente un programa secuencial que calcule el producto de dos matrices (4x4). Después modifíquelo para que esta tarea se realice entre cuatro threads, cada uno ocupado de un subconjunto de la matriz resultado. Mida el tiempo que emplea cada una de las versiones. Mostrar por JOptionPane, el tiempo que tarda y el resultado en forma secuencial y luego el tiempo que tarda los hilos y el resultado de cada hilo individual como el final.

5. Leer de una carpeta todos los documentos de texto que se encuentren en ella y contar la cantidad total de renglones que poseen en total. Por cada archivos encontrado se deberá contar los renglones 1 Thread distinto. Mostrar por pantalla la cantidad de archivos que se encontraron, cuantos Thread trabajaron y el total de renglones de todos los archivos.

6. Construir un hilo (OficinaDeAlumnos) que cargue un listado de alumnos (mínimo 4) Construir una class Alumnos (nombre, apellido, asistencia [9], notas[3], boolean esAlumnoRegular).

En el main() se debe lanzar a ejecución Preceptor y Docente una vez que terminen se realizan las instrucciones Promedio (perteneciente a la clase Docente) y AlumnoLibre(menor al75%, perteneciente a la clase Preceptor).

Construya 2 Thread (Preceptor y Docente) que realicen las asignaciones de las instrucciones: Ingreso del preceptismo (0% a 100%) e Ingreso de 3 notas por alumno (tareas respectivas para cada Thread). (El % de presentismo y las notas DEBEN ser random).

El main() debe esperar a que terminen Preceptor y Docente, para luego informar una lista de los alumnos eximidos y su respectiva nota final.

7. Control de empleados: Ingresar los nombres de los empleados, que día y a qué hora ingresaron a su centro de labores en un Array de personal (la cantidad de personal total se pregunta al inicio del programa y se ocupa un Thread dedicado), luego de que se carguen los horarios indicar si llego temprano o tarde cada empleado (mientras se vallan cargado empleados el hilo que revisa el personal se va a activando) cabe señalar que la hora de ingreso es a las 8:00 y que pueden ser días diferentes, cuando ya se cargaron todos los empleados y se mostró el último mensaje el programa se cierra.
