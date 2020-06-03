Calculadora Spring-Boot
Calculadora como micro-servicio (API) utilizando Spring-boot y Maven con funcionalidades básicas de sumar, restar, multiplicar y dividir. Leer el enunciado para más información.

Comunicación a través de una API REST (p.e. GET /api/calcula?operador1=2&operador2=3&operacion=suma)


Requisitos previos
Necesario disponer de la ultima versión de Maven instalada. Tambien hace falta tener JAVA instalado en el sistema.

Como utilizar
Descargar el repositorio girhub
Ejecutar una verificación de Maven
Instalación limpia de Maven
Ejecutar el JAR de la carpeta /target
Abrir una consola y ejecutar:

git clone https://github.com/Azorruc/Calculadora.git
cd calculadora
mvn validate
mvn verify
mvn clean install
A estas alturas debería haberse generado una carpeta /target con el JAR definitivo del programa calculadora-spring-boot.jar. Para ejecutarlo:

java -jar target/calculadora-spring-boot.jar

Con estos pasos deberiamos tener a nuestra disposición una API REST escuchando en http://localhost:8080/api/calcula

Si has tenido algun error con la ejecución de las instrucciones anteriores, en el proyecto ya hay un JAR definitivo compilado con todas las dependencias, si quieres probar el proyecto directamente puedes (1) clonar el proyecto en local y (2) ejecutar el JAR precompilado calculadora-spring-boot.jar.

git clone https://github.com/Azorruc/Calculadora.git
java -jar calculadora/target7calculadora-spring-boot.jar
API REST
Si has seguido correctamente los pasos anteriores, deberias tener el puerto 8080 abierto y escuchando peticiones REST GET.

Los parámetros se le deben pasar por URL en formato URLEncoded. Los parametros son los siguientes primero el primer numero de la operación, segundo el segundo numero de la operación y operacion una cadena de texto describiendo la operación a realizar (valores aceptados suma, resta). Por ejemplo:

GET http://localhost:8080/api/calcula?primero=4&segundo=9&operacion=suma

El resultado será un valor numerico con decimales (4 + 9 = 13):

13.0

Ejecutar tests
Si has clonado e instalado todas las dependencias de maven en local, para ejecutar los tests debes ejecutar el comando mvn clean test.