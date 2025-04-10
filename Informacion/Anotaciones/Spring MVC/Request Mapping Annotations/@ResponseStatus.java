/*
    * Esta anotación se utiliza para personalizar el código de estado HTTP que se devuelve desde un controlador de Spring (por ejemplo, en una API REST).
    * ¿Qué es @ResponseStatus?
    * @ResponseStatus es una anotación que se usa para indicar el código de estado HTTP que
    * debe devolver un método (o una clase de excepción) en un controlador Spring.

    * Sintaxis básica:
*/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
/*
    * O en un método del controlador:
*/
@GetMapping("/saludo")
@ResponseStatus(HttpStatus.ACCEPTED)
public String saludar() {
    return "¡Hola!";
}
/*
    * Atributos de la anotación:
    * value: Código de estado HTTP (por ejemplo HttpStatus.NOT_FOUND)
    * reason (opcional): Mensaje que puede mostrarse como motivo del error
*/

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Datos inválidos")
public class DatosInvalidosException extends RuntimeException {
}

/*
    * Casos de uso comunes:
    * Excepciones personalizadas: Para lanzar excepciones que automáticamente devuelvan un código de error específico (como 404 o 400).
    * Controladores REST: Para métodos que devuelven un código diferente al 200 OK por defecto (como 201 Created o 202 Accepted).
    
    * Nota:
    * Si usas @ResponseStatus en una excepción, no necesitas un @ExceptionHandler para devolver el código HTTP, ya que Spring lo hace automáticamente.
*/
