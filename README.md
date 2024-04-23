# Foris Test

¡Hola! Bienvenido a mi Test para Foris.

## Tecnologías utilizadas

Para este proyecto se utilizaron las siguientes tecnologías:

- Java 21 (OpenJDK)
- Maven
- JUnit
- Git

## Instrucciones

Para ejecutar el proyecto se debe clonar el repositorio y ejecutar los siguientes comandos:

``` javac -cp src/main/java src/main/java/org/foris/*.java ```

``` java -cp src/main/java org.foris.ForisTest src/main/resources/students.txt ```

Para ejecutar los tests, se debe ejecutar el siguiente comando:

``` mvn test ```

# Razones de las decisiones tomadas

Realmente al principio estaba sin un enfoque claro, pero luego de analizar el problema, decidí dividirlo en tareas más pequeñas y resolverlas una a una. 

Pasos:

1. Crear una clase que leyera el archivo linea por linea.
2. Crear una estrategia para poder reconocer los comandos y ejecutarlos.
3. Crear una estructura de datos para almacenar los datos de los estudiantes y el seguimiento de asistencias.
4. Crear la ejecución para el comando "Student" y "Command":

    - Para el comando "Student" simplemente se almacena en un mapa donde la key es el nombre del estudiante y el value es el seguimiento de asistencias.
    - Para el comando "Presence" se verifica si el estudiante existe y se actualiza su seguimiento de asistencias siguiendo las reglas de negocio como: Omitir asistencias de menos de 5 minutos, validar que la hora de entrada sea antes de la hora de salida, etc.

Por último, ya que no se específica como validar los días de la semana, decidí hacerlo de la siguiente manera:
   - Si el último día registrado es diferente al día del registro que se está validando, se suma un día de asistencia.

Muchas gracias por la oportunidad de participar en este proceso de selección.

## Autor
Javier Andres Charry Coronado