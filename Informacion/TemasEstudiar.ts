/*
¿Qué es REST?
Estilo de arquitectura para sistemas distribuidos
¿Cuál es la diferencia entre Restful y Restless?
Restful está orientado a recursos y restless a acciones.
Bajo esquema RESTful, ¿Qué verbo HTTP debe utilizarse para crear un recurso?
POST
Bajo esquema RESTful, ¿Qué verbo HTTP debe utilizarse para actualizar completamente un
recurso?
PUT
Bajo esquema RESTful, ¿Qué verbo HTTP debe utilizarse para actualizar parcialmente un
recurso?
PATCH
Bajo esquema RESTful, ¿Qué verbo HTTP debe utilizarse para eliminar un recurso?
DELETE
De las siguientes opciones, elige la que contenga tres principios de REST:
Es stateless, tiene interfaz uniforme, el recurso tiene más de una manera de representación
¿Qué es un JSON?
Formato ligero para intercambio de datos
Son dos características de la arquitectura REST:
Cacheable y en capas
En REST, ¿Para qué es utilizado el código de respuesta HTTP 404?
Error funcional, indica que el recurso solicitado no se encuentra
En REST, ¿Para qué es utilizado el código de respuesta HTTP 204?
Respuesta exitosa pero sin body.
¿Qué es idempotencia?
Capacidad de hacer una solicitud N veces con los mismos parámetros y que los resultados sean los
mismos
Elige de las siguientes opciones la que contenga 3 verbos que cumplen con la idempotencia:
GET, HEAD, DELETE
Es un antipatrón al definir un recurso REST:
GET update_customer/12345
En REST el código de error _____ se utiliza para describir una petición mal formato
400
En REST el código de error _____ se utiliza para describir un recurso inexistente
404
Comando GIT utilizado para actualizar un repositorio:
git pull
Comando utilizado para renombrar la rama actual de un repositorio Git a <nombreRama>
git branch -m <nombreRama>
Comando GIT que NO modifica la historia del repositorio:
revert
¿Qué comando en GIT se usa para crear un repositorio?
git init
¿Qué comando en GIT se usa para copiar un repositorio existente?
git clone
¿Cuál es el comando utilizado para listar los cambios (commits) que han producido en el
repositorio?
git log
¿Qué comando en GIT se usa para añadir contenido al área de preparación (staging area)?
git add
¿Qué comando en GIT lista todas las ramas (branches) disponibles localmente?
git branch
Comando que proporciona git, si quieres saltarte el área de preparación(staging area).
git commit -a
¿Qué comando crea una nueva rama local llamada develop?
git checkout -b develop
¿Qué es polimorfismo?
Propiedad por la que es posible enviar mensajes sintácticamente iguales a objetos de tipos distintos
¿Qué significa sobrecargar un método?
Crear un método con el mismo nombre pero con diferentes argumentos
Tomando en cuenta Java >=8, ¿cuál es la diferencia entre una interfaz y una clase abstracta?
Las clases abstractas pueden contener variables o métodos privados
La clase TreeMap es utilizada para implementar la colección:_____________
SortedMap
*/
/* 
 *  ¿Cuáles son los tipos de memoria en JAVA?
 *  Stack: Está limitada por la memoria ram, siempre que no se usen grandes bloques de memoria, o
 *  sabiendo la cantidad de memoria utilizar.
 *  Heap: Almacena objetos y sus variables de instancia. Es un espacio de memoria dinámica que se
 *  crea al inicio de la máquina virtual y es único.
 *  PermGen: área donde JVM almacena las clases que se cargan, por lo que en algunos servidores de
 *  aplicación será necesario incrementarla para que dé cabida a las clases del Servidor de aplicación.
*/
/*
    * ¿Qué hace el Garbage Collector?
    * Realiza un escaneo dinámico de la memoria en busca de objetos que ya no se encuentren
    * referenciados para poder liberar memoria.
*/
/*
/*
    ^ El concepto de "clase inmutable" en java , se refiere:
    ^ Una vez creada una instancia de esa clase, de asignarse un nuevo valor, resultará un objeto nuevo
*/
/*
El archivo pom.xml es un archivo de configuración de…
Maven
¿Qué es Maven?
Es una herramienta para la gestión y construcción de proyectos
¿Que significa instanciar una clase?
Crear un objeto a partir de una clase
¿Para qué sirve el comando sudo?
Para ejecutar tareas de super usuario
¿Para qué sirve el comando cd?
Para navegar entre directorios
¿Para qué sirve el comando ls?
Para listar el contenido de un directorio
¿Para qué sirve el comando mkdir?
Para crear un directorio
/* 
    ^ ¿Cual de los siguientes comandos sirve para visualizar el contenido de un archivo?
    ^ Nano, cat, vi
*/
/*
¿Cual es la funcionalidad del comando pwd?
Verificar la ruta en la que se encuentra
¿Cuál afirmación es correcta sobre el maven?
Es una herramienta de software para la gestión y construcción de proyectos Java
Utiliza un Project Object Model (POM) para describir el proyecto de software a construir
Optimiza la gestión de dependencias
Es el archivo de configuración necesario para que maven pueda comunicarse con el
repositorio de dependencias
settings.xml
Parámetro maven que forza a que se descarguen de nuevo las dependencias del proyecto:
-U
/*
    * ¿En maven Para qué sirve el comando Install?
    * Para compilar el proyecto
*/
/*
Un ________ proporciona una solución a un problema de diseño. Debe cumplir con diferentes
características, como la efectividad al resolver problemas similares en ocasiones anteriores.
Por lo tanto, debe ser reutilizable, es decir, aplicable a diferentes problemas en diferentes
circunstancias.
Patrón de diseño
Un patrón es un diseño que conduce a una mala solución de un problema. Como lo son el
Blob y el contenedor mágico en librerías.
Falso
No puede incluir _______ o etiquetas similares. Estas anotaciones no reciben errores ni
advertencias en el compilador en el momento de la compilación
@SuppressWarnings
¿Los niveles más comunes son DEBUG, INFO, WARNING y ERROR?
Verdadero
Para información de muy bajo nivel, solo útil para depurar la aplicación en el desarrollo ¿A
qué tipo de nivel de Log se refiere?
Debug
Indica a JUnit que la propiedad que usa esta anotación es una simulación y, por lo tanto, se
inicializa como tal y es susceptible de ser inyectada por @InjectMocks.
Mock
¿Que se almacena y distribuye en el Docker Registry?
Imágenes Docker
Archivo de texto con los comandos para construir una imagen Docker…
Docker File
Comando utilizado para detener la ejecución de un contenedor…
docker stop <container_id>
Comando utilizado para borrar imagen del contenedor
docker rm <container_id>
Define un modelo estricto de ramificación diseñado alrededor de la publicación del proyecto
Gitflow
Permite que los desarrolladores puedan enfocarse sólo en desarrollar y puedan desplegar su
código en segundos
DevOps
¿Qué definición se adapta mejor al término Agile?
Es un conjunto de principios rectores que utilizan un enfoque iterativo para el desarrollo de proyectos
o software
La rama _________ debería ser estrictamente fiel al código desplegado en ambientes
productivos
Master
La rama _________ es utilizada para los nuevos desarrollos y se utiliza para trabajar con
ambientes previos
Develop
¿Qué definición se adapta mejor al término DevOps?
Es una metodología de desarrollo software basada en la integración entre desarrolladores y
administradores de sistemas.
La integración continua no es una manera de automatizar tareas cuando se sube el código
Falso
¿Cuál de las siguientes es una herramienta de integración continua?
Jenkins
¿Cuál de las siguientes herramientas podría considerarse en el modelo DevOps?
Docker
Bitbucket
Jenkins
Archivo de configuración Spring framework es:
config.xml
¿Cual es el scope de un bean stateless?
singleton
¿Para qué sirve anotación @Autowired?
Permite hacer Inyección de dependencias en Spring
¿Cual es el scope de un bean stateful?
global-session
¿que es DispatcherServlet?
Maneja las peticiones HTTP
Cual de las siguientes funcionalidades aparecen con PowerMock
Mockear invocaciones a métodos estáticos.
Mockear clases y métodos marcados como finales.
Acceder a la verificación del estado de atributos privados.
PowerMock es un framework que extiende tanto EasyMock como Mockito
Verdadero
¿Es una librería Java que permite simular el comportamiento de una clase de forma
dinámica.?
Mockito
Se llaman así a los objetos que imitan el comportamiento de objetos reales de una forma
controlada
Mock
¿Cual de las siguientes afirmaciones es correcta sobre la inyección de dependencias en
Spring?
Es un patrón de desarrollo de software donde los objetos no son responsables de inicializar sus
dependencias
¿Cuál afirmación corresponde a la inversión de control en Spring?
Es un estilo de programación en el cual un framework o librería controla el flujo de un programa
¿Qué descripción se adapta mejor a la función del contenedor de Spring?
Se encarga de crear los objetos, conectarlos entre sí, configurarlos y además controla los ciclos de
vida de cada objeto mediante el patrón de Inyección de dependencias
¿Cuál es la forma de proporcionar metadatos de configuración a Spring?
Archivo de configuración basado en XML
Configuración basada en anotaciones
Configuración basada en Java
IOC o inyección de dependencias es un …
Patrón de diseño
La definición de Beans en Spring por Defecto son:
Singlenton
Cual de las siguientes afirmaciones es verdadera
ApplicationContext extiende de BeanFactory
Una instancia de Bean, que se ejecuta en una petición Http (request http) su tiempo de vida
(lifeTime) dura solo a nivel ..
Request

¿Cual de las siguientes son anotaciones de Stereotipos? Selecciona la opción correcta.
@Service, @Repository, @Component
*/