/*
    * La anotación @Valid en Java se utiliza en combinación con Java Bean Validation (JSR
    * 380), que es parte del estándar Jakarta Bean Validation (anteriormente Java EE). Se usa
    * para validar automáticamente los atributos de un objeto, generalmente en el contexto
    * de aplicaciones Spring o Jakarta EE.
    
    * ¿Qué hace @Valid?
    * @Valid se utiliza para activar la validación en:
    * Controladores de Spring (por ejemplo, en peticiones HTTP con objetos @RequestBody)
    * Servicios o cualquier método donde se desea validar objetos.
    * Parámetros de métodos y campos anidados en objetos.
    
    * Ejemplo básico con Spring Boot:
    * Supón que tienes una clase Usuario:

*/
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

public class Usuario {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "El correo debe ser válido")
    private String correo;

    // *  Getters y setters
}
// * En tu controlador:

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PostMapping
    public ResponseEntity<String> crearUsuario(@RequestBody @Valid Usuario usuario) {
        return ResponseEntity.ok("Usuario válido");
    }
}

/*
    * ¿Qué pasa si hay errores?
    * Si hay errores de validación, Spring los lanza como una excepción:
    * MethodArgumentNotValidException. Puedes capturarla con un @ControllerAdvice.
    * Validación anidada: 
    * Si tienes objetos dentro de objetos, puedes usar @Valid en los campos también:
*/
public class Pedido {
    @Valid
    private Usuario usuario;
}

/*
    * Diferencia entre @Valid y @Validated
    * @Valid es de Jakarta Bean Validation (JSR 380).
    * @Validated es una anotación de Spring que permite validaciones por grupo, y funciona con @Valid.
    * Ambas sirven para validar, pero @Validated tiene más control sobre los grupos de validación.

*/