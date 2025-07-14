# Prog_Red_Fernandez_Zalazar

Alumno: Ezequiel Nahuel Fernandez Zalazar

Correo: ezequiel.fernandez.zalazar.e.t32.co@gmail.com

Curso y division: 6° 1° Computación

Docente: Gonzalo Consorti

Materia: Administración de sistemas y redes

Guia de ejercicios de I/O - Colection

Toda la guía de ejercicios de resuelve con:

 Un MENU INFINITO DE OPCIONES (en el Main), donde el usuario elije el número de ejercicio a ejecutar y se llama al ejercicio a través de un objeto de una clase creada por ustedes (ejemplo: ClaseEjerciciosIO) que contendrá los métodos que resuelven cada ejercicio ( Ejemplo: ClaseEjerciciosIO.ResolucioEjercicio1()  ).
 
 Todos los datos ingresados y leídos DEBEN de ser verificados, validados para que no se ingresen datos erróneos, de lo contrarios pedir que ingrese datos nuevamente.
 
NO USAR rEADERS O BUFERED EN EL PUNTO 1 ESTA BIEN EACLARO

1. Usando SOLO los Métodos de la Class.System para la lectura y PrintStream para escritura en consola. 

 Dados el valor de una hora de trabajo y la cantidad de horas trabajadas, la computadora muestra el valor del sueldo bruto.

 Dados los valores de dos de los ángulos interiores de un triángulo, la computadora muestra el valor del restante.

 Dada la superficie de un cuadrado (en m2), la computadora muestra su perímetro.

 Dada una temperatura en grados Fahrenheit, la computadora la muestra en grados centígrados.

 Dado un tiempo en segundos, la computadora lo muestra expresado en días, horas, minutos y segundos.

 Dado el precio de un artículo, la computadora muestra los valores a pagar según cada plan. Una casa de artículos para el hogar ofrece a sus clientes los siguientes planes de pago:

Plan 1: 100% al contado. Se hace el 10% de descuento sobre el precio publicado.
Plan 2: 50% al contado y el resto en 2 cuotas iguales. El precio publicado se incrementa en un 10%.
Plan 3: 25% al contado y el resto en 5 cuotas iguales. El precio publicado se incrementa en un 15%.
Plan 4: Totalmente financiado en 8 cuotas. El 60% se reparte en partes iguales en las primeras 4 cuotas y el resto se reparte en partes iguales en las últimas 4 cuotas. El precio publicado se incrementa en un 25%.

 Dado el signo zodiacal del usuario, la computadora muestra su mes de nacimiento aproximado.

2. Usando SOLO los Métodos de la Class.Reader para la lectura y 
PrintStream para escritura en consola. 

 Dados tres apellidos, la computadora los muestra ordenados alfabéticamente.

 Dados cuatro números reales, la computadora indica cuál es el menor.

 Dado un número, la computadora indica si es par o impar.

 Dados dos números reales, la computadora indica si el mayor es divisible por el menor.

 Dada la fecha de nacimiento de una persona, la computadora muestra su signo del zodíaco.

 Dado el nombre y apellido de 2 personas, la computadora muestra cuál de los 2 tiene el apellido más largo.

 Dado un entero N natural, la computadora muestra su tabla de multiplicar.

 Dado un número natural, la computadora indica si es primo o no.

3. Usando SÓLO los Métodos de la Class.Reader para la lectura y PrintStream para escritura en consola, la class PrintWriter para escritura en archivos y para las lecturas de archivos usar Class Buffered correspondiente. (los archivos de texto plano DEBEN crearse desde línea de código NO a mano, al menos que especifique el ejerció.)

 Crear un archivo de texto (en la carpeta del proyecto) que guarde solo el último dato que el usuario escribe por consola.

 Crear un archivo de texto (en la carpeta del proyecto) que guarde TODOS los valores NUMERICOS que ingrese el usuario por consola, cada uno en un renglón (puede ingresar otros datos que no sean numero OJO).

 Crear un archivo de texto (fuera de la carpeta del proyecto) que se llame “números.txt” que guarde los números pares desde el 0 al 1000.

 Utilizar el archivo creado anteriormente “números.txt” y leer los valores para mostrarlos por la consola.

 Utilizar el archivo creado anteriormente “números.txt” y borrarle todos los renglones que contengas números multiplos de 3.

 Utilizar el archivo creado anteriormente “números.txt” (con los números impares eliminados) y colocar en otro archivos de texto “primos.dat” (fuera de la carpeta del proyecto y de la carpeta que tiene el archivo “números.txt”) todos los números de ”números.txt” que sean primos.

 Crear un archivo de texto plano (fuera de la carpeta del proyecto) que se llame “caracteres.dat”, cargarle 10 palabras que contengan la letra ‘ñ’ mediante consola. Luego mostrar las 10 palabras por consola con la leyenda “Fichero original: ” y luego editar el fichero para que cambie todas las apariciones de la letra ‘ñ’ por su fonético ‘nie-nio’ y mostrar de nuevo las palabras con la leyenda “Fichero arreglado:”.

 Crear un archivo HTML con un lorem en algun lugar (a mano), con un algoritmo lograr abrir el archivo y borrar toda la leyenda de lore

 Se pide a un usuario mediante un menú cargar una serie de datos de clima, ubicados por la fecha, le permitimos al usuario mostrar todos los datos, le permitimos al usuario poder borrar algún registro (del archivo)


4. Ejercicios de Colecciones  con entrada y salida de datos por consola (se deben usar entradas y salidas a elección del punto 1. o 2.)

 Una serie de valores numéricos enteros desde el teclado y los guarde en un List de tipo Integer. La lectura de números enteros termina cuando se introduzca el valor -99. Este valor no se guarda en el List. A continuación el programa mostrará por pantalla el número de valores que se han leído, su suma y su media. Por último se mostrarán todos los valores leídos, indicando cuántos de ellos son mayores que la media. Realizar los metodos: leerValores():  calcularSuma()  mostrarResultados()

 Crea una clase colegio que almacene los apellidos junto a la nacionalidad de los alumnos de un colegio. La clase tendrá los siguientes métodos: addAlumno(String nacionalidad) ->  añade la nacionalidad de un nuevo alumno,  showAll() ->  Muestra las distintas nacionalidades y el número de alumnos que existen por nacionalidad  ,       showNacionalidad(String nacionalidad)  ->  Muestra la nacionalidad y el número de alumnos de esa nacionalidad  ,  cuantos()  ->   Muestra cuántas nacionalidades diferentes existen en el colegio.  ,  borra() ->  Elimina los datos insertados.

 Crea una colección apropiada que contenga los días de la semana.  Inserta en la posición 4 el elemento «Juernes».  Crea una copia de esa lista a otra llamada listaDos.   Añade a listDias el contenido de listaDos.  Muestra el contenido de las posiciones 3 y 4 de la lista original.  Muestra el primer elemento y el último de la lista original.   Elimina el elemento que contenga «Juernes» de la lista y comprueba si elimina algo o no.   Muestra uno a uno los valores de la lista original a través de un Objeto Iterador .   Busca si existe en la lista un elemento que se denomine «Lunes». No importa si está en mayúscula o minúscula.    Ordena la lista y muestra su contenido.

 Crea un conjunto al que se le va a llamar jugadores. Inserta en el conjunto los jugadores del FC Barcelona. («Jordi Alba» «Pique» «Busquets» «Iniesta» «Messi»)  Realiza una Iteracion  sobre los jugadores del conjunto y muestra sus nombres.     Consulta si en el conjunto existe el jugador «Neymar JR». Avisa si existe o no.     Crea un segundo conjunto jugadores2 con los jugadores «Piqué» y «Busquets».        Consulta si todos los elementos de jugadores2 existen en jugadores.        Realiza una unión de los conjuntos jugadores y jugadores2.         Trate de ingresar a «Piqué» a la primera coleccion de tal manera que la coleccion elegida no permite ingresarlo esta vez.

 Reglas de bolas de dos colores: cada apuesta en la bola de dos colores consta de 6 números de bolas rojas y 1 número de bolas azules. El número de bola roja se selecciona del 1 al 33; el número de bola azul se selecciona del 1 al 16; genere aleatoriamente un número de bola de doble color. (Requiere que no se repita el mismo número de color)
