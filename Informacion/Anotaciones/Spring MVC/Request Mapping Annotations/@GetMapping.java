/*
    TODO: @GetMapping
    * La anotación @GetMapping es una especialización de @RequestMapping
    * en Spring MVC y se utiliza para manejar solicitudes HTTP GET en un controlador.
    * Es una forma más concisa y específica de definir rutas en los controladores de Spring Boot.
    & Ejemplo Básico de @GetMapping
*/
@RestController
@RequestMapping("/api")
public class MiControlador {

    @GetMapping("/saludo")
    public String saludo() {
        return "¡Hola desde Spring Boot!";
    }
}
/*
    ^ Explicación:
    ^ @RestController: Define que esta clase es un controlador REST.
    ^ @RequestMapping("/api"): Agrupa todas las rutas bajo el prefijo /api.
    ^ @GetMapping("/saludo"): Maneja solicitudes GET en la ruta /api/saludo.
    ^ Características Clave de @GetMapping
    ^ Manejo de Solicitudes GET:
    ^ Solo responde a solicitudes HTTP GET.
    ^ Retorno Automático de Datos:
    ^ Si el método devuelve un String, Spring Boot lo enviará como respuesta.
    ^ Si devuelve un objeto, lo convertirá automáticamente a JSON si está en un controlador REST
    ^ (@RestController).
    ^ Permite Parámetros de Consulta (@RequestParam)
*/ 
@GetMapping("/saludo")
public String saludo(@RequestParam String nombre) {
    return "Hola, " + nombre;
}
/*
    *  Llamando a /api/saludo?nombre=Juan, responderá: "Hola, Juan".
    *  Permite Parámetros en la URL (@PathVariable)
*/
@GetMapping("/usuario/{id}")
public String obtenerUsuario(@PathVariable int id) {
    return "Usuario con ID: " + id;
}
/* 
    * Llamando a /api/usuario/5, responderá: "Usuario con ID: 5".
    * Retorno de Objetos JSON

*/
@GetMapping("/producto")
public Producto obtenerProducto() {
    return new Producto(1, "Laptop", 1200.99);
}

/*
    TODO :  Respuesta en JSON:
*/
{
    "id": 1,
    "nombre": "Laptop",
    "precio": 1200.99
}
/*
    TODO: Diferencias entre @GetMapping y @RequestMapping
    TODO: Característica                @GetMapping	    @RequestMapping
    * Soporta múltiples métodos HTTP	❌ No	        ✅ Sí (GET, POST, etc.)
    * Más conciso	                    ✅ Sí	        ❌ No
    * Soporta atributos de método HTTP	❌ No (solo GET)	✅ Sí (method = RequestMethod.GET)
    * Ejemplo equivalente con @RequestMapping:
*/ 
@RequestMapping(value = "/saludo", method = RequestMethod.GET)
public String saludo() {
    return "¡Hola desde Spring Boot!";
}
/*
    * @GetMapping es una forma más simple de escribirlo.
    * Conclusión
    * @GetMapping se usa para manejar solicitudes GET de manera sencilla y directa.
    * Soporta parámetros en la URL (@PathVariable) y en la consulta (@RequestParam).
    * Devuelve datos en formato texto o JSON si está en un @RestController
    * Es más conciso y legible que @RequestMapping(method = RequestMethod.GET).
    * 
*/