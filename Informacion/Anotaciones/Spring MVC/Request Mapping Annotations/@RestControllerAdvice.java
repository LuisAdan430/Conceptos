/*
    * La anotación @RestControllerAdvice en Spring Boot es una especialización de
    * @ControllerAdvice, combinada con @ResponseBody. Se utiliza para manejar excepciones
    * globales o aplicar lógica a todas las respuestas de los controladores REST (los que usan
    * @RestController).
    
    * ¿Qué hace @RestControllerAdvice?
    * Globaliza el manejo de excepciones para todos los controladores REST de tu aplicación.
    * Permite interceptar y modificar respuestas salientes.
    * Se aplica automáticamente a todos los métodos marcados con @RequestMapping, @GetMapping, etc., dentro de controladores @RestController.
    * Equivalente a:
*/
@ControllerAdvice
@ResponseBody
public class MiAdvice { ... }

/*
    * Usos comunes
    * Manejo global de excepciones
*/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        return new ResponseEntity<>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
// * Modificar la respuesta de todos los controladores

@RestControllerAdvice
public class ResponseWrapperAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true; //  * aplicar a todas las respuestas
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        // *  Envolver la respuesta en un objeto estándar
        return Map.of("status", "success", "data", body);
    }
}

/*
    * Cuándo usarlo
    * Cuando quieras un formato uniforme de respuestas para toda tu API REST.
    * Cuando necesites centralizar el manejo de errores.
    * Para aplicar lógica común antes de enviar cualquier respuesta.
*/