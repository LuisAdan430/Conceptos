/*
    * ¿Qué hace @ExceptionHandler?
    * Permite que un método dentro de un controlador maneje una o varias
    * excepciones específicas que puedan ocurrir durante la ejecución de una
    * petición HTTP.
    
    * Sintaxis básica
*/
@ExceptionHandler(MiExcepcion.class)
public ResponseEntity<String> manejarExcepcion(MiExcepcion ex) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("Ocurrió un error: " + ex.getMessage());
}
/*
    * ¿Dónde se puede usar?
    * Dentro de un controlador específico
    *   Solo maneja las excepciones ocurridas en ese controlador.
    * Dentro de una clase anotada con @ControllerAdvice
    *   Aplica el manejo de excepciones a todos los controladores del proyecto.
    * Múltiples excepciones
    * Puedes capturar varias excepciones con un solo método:
*/
@ExceptionHandler({IOException.class, SQLException.class})
public ResponseEntity<String> manejarErrores(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                         .body("Error: " + ex.getMessage());
}
/*
    * Usado junto con @ResponseStatus o ResponseEntity
    * @ResponseStatus: define el código HTTP directamente.
*/
@ExceptionHandler(MiExcepcion.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public String manejarError(MiExcepcion ex) {
    return "Recurso no encontrado: " + ex.getMessage();
}
/*
    * ResponseEntity: mayor control sobre la respuesta (estado, cabeceras, cuerpo, etc.).
    * Ventajas
    * Centraliza el manejo de errores.
    * Mejora la legibilidad del código.
    * Permite respuestas consistentes a nivel de API REST.
    * Facilita el logging y trazabilidad de errores.
*/