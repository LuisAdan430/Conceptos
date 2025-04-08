/*
    * ¿Qué es @ControllerAdvice?
    * La anotación @ControllerAdvice es una anotación especializada de Spring
    * que se utiliza para manejar de forma global excepciones, respuestas y lógica
    * compartida entre múltiples controladores (@Controller) en una aplicación
    * Spring MVC.
    * ¿Para qué sirve?
    * @ControllerAdvice permite:
    * Manejo global de excepciones (@ExceptionHandler)
    * Agregar atributos a todos los modelos (@ModelAttribute)
    * Modificar las respuestas antes de que se envíen al cliente (@InitBinder)
    * Estructura básica
*/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ModelAttribute
    public void addGlobalAttributes(Model model) {
        model.addAttribute("appName", "Mi Aplicación Spring");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // * Configuraciones globales de binding
    }
}
/*
    * Detalles por cada anotación útil dentro de @ControllerAdvice
    * @ExceptionHandler
    * Maneja excepciones específicas lanzadas desde cualquier controlador.
*/
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
    return new ResponseEntity<>("Recurso no encontrado", HttpStatus.NOT_FOUND);
}
/*
    * También puedes manejar múltiples excepciones:
*/
@ExceptionHandler({SQLException.class, DataAccessException.class})
public ResponseEntity<String> handleDatabaseErrors(Exception ex) {
    return new ResponseEntity<>("Error de base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
}

/*
    * @ModelAttribute
    * Agrega atributos automáticamente al Model de todos los controladore
*/
@ModelAttribute
public void setDefaultData(Model model) {
    model.addAttribute("defaultTitle", "Mi App Web");
}

/*
    * @InitBinder
    * Permite personalizar el binding de datos para todos los controladores.
*/
@InitBinder
public void customizeBinding(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
}
/*
    * Casos comunes de uso
    * Manejo centralizado de excepciones con mensajes personalizados.
    * Evitar duplicación de código en múltiples controladores.
    * Agregar atributos comunes al modelo como título, configuración, o info del usuario.
    * Configurar formateadores o editores de datos compartidos.
    * Buenas prácticas
    * Crear una clase con nombre claro como GlobalExceptionHandler o AppControllerAdvice.
    * Separar los métodos de manejo por tipo de excepción.
    * No abusar de @ControllerAdvice para lógica que debería estar en servicios.
    * Compatibilidad con @RestControllerAdvice
    * Spring también ofrece una versión especializada:
*/
@RestControllerAdvice
/*
    * Es equivalente a usar @ControllerAdvice + @ResponseBody, es decir
    * devuelve automáticamente JSON o XML en las respuestas, ideal para APIs
    * REST.
    * Un ejemplo completo y realista de cómo usar @RestControllerAdvice para manejar errores globalmente en una API REST con Spring Boot.
    * Escenario:
    * Supongamos que tienes una API que lanza una excepción personalizada
    * RecursoNoEncontradoException. Queremos manejar esa excepción de forma
    * global y devolver una respuesta JSON clara al cliente.
    * Crear una excepción personalizada
*/
public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
/*
    * Crear el handler global con @RestControllerAdvice
*/

@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("mensaje", ex.getMessage());
        error.put("codigo", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarErroresGenerales(Exception ex) {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("mensaje", "Error inesperado: " + ex.getMessage());
        error.put("codigo", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
/*
    * Controlador que lanza la excepción
*/
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @GetMapping("/{id}")
    public ResponseEntity<String> obtenerUsuario(@PathVariable Long id) {
        if (id != 1) {
            throw new RecursoNoEncontradoException("Usuario con ID " + id + " no encontrado.");
        }
        return ResponseEntity.ok("Usuario encontrado");
    }
}
/*
    * Resultado esperado (ejemplo de respuesta JSON)
    * Request:
*/
GET /api/usuarios/99
/*
    * Response:
*/
{
  "timestamp": "2025-04-07T12:45:00.123",
  "mensaje": "Usuario con ID 99 no encontrado.",
  "codigo": 404
}
