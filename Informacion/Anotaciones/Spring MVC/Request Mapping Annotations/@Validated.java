/*
    * La anotación @Validated en Spring se utiliza para activar la validación de beans
    * utilizando el API de validación de Java (JSR-303 / JSR-380, como Hibernate Validator) en
    * combinación con las anotaciones de validación (@NotNull, @Size, @Email, etc.).
    
    * ¿Dónde se usa @Validated?
    * Se puede usar en:
    * Controladores (@Controller, @RestController)
    * Servicios (@Service)
    * Clases de configuración
    
    * Ejemplo básico en un controlador

*/
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PostMapping
    public ResponseEntity<String> crearUsuario(@RequestBody @Validated UsuarioDTO usuario) {
        return ResponseEntity.ok("Usuario creado");
    }
}
public class UsuarioDTO {
    @NotBlank
    private String nombre;

    @Email
    private String correo;
    
    // *  Getters y setters
}

// * Ejemplo en un servicio
@Service
@Validated
public class UsuarioService {

    public void registrarUsuario(@Valid UsuarioDTO usuario) {
        //  * lógica de negocio
    }
}

/*
    * El @Validated en el servicio activa la validación de parámetros (como @Valid,
    * @NotNull, etc.) cuando se llama desde otros beans de Spring.
    * Diferencia entre @Validated y @Valid
    * Característica                @Validated (Spring)                                     @Valid (JSR-303 estándar)
    * Paquete                       org.springframework.validation.annotation.Validated     jakarta.validation.Valid
    * Agrupaciones de validación    ✅ Soporta grupos (groups)                              ❌ No soporta por defecto
    * Uso                           En clases o métodos                                     En parámetros o atributos
    
    * Ejemplo de grupos de validación con @Validated
*/

public interface Crear {}
public interface Actualizar {}

public class UsuarioDTO {
    @NotNull(groups = Actualizar.class)
    private Long id;

    @NotBlank(groups = {Crear.class, Actualizar.class})
    private String nombre;
}

@PostMapping("/crear")
public ResponseEntity<?> crear(@RequestBody @Validated(Crear.class) UsuarioDTO usuario) {
    // ...
}
