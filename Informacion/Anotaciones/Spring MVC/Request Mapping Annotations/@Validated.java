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
*/