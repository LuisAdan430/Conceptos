/*
    * @PatchMapping
    * la anotación @PatchMapping se usa en Spring MVC (Spring Boot) para mapear solicitudes HTTP
    * PATCH a métodos de controlador en una aplicación REST. Es parte de las anotaciones que Spring
    * proporciona para manejar los diferentes tipos de métodos HTTP: @GetMapping, @PostMapping,
    * @PutMapping, @DeleteMapping, y @PatchMapping.
    
    * ¿Qué es PATCH? 
    * El método HTTP PATCH se utiliza para actualizaciones parciales de un recurso. A diferencia de PUT (ques
    * suele reemplazar completamente el recurso), PATCH solo modifica algunos campos.
     
    * Ejemplo simple de uso
*/

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> actualizarParcialmenteUsuario(
            @PathVariable Long id,
            @RequestBody Map<String, Object> camposActualizados) {
        
        // * Aquí se buscaría el usuario por ID, y se aplicarían solo los cambios necesarios
        // * usando los datos del mapa `camposActualizados`.

        return ResponseEntity.ok(usuarioActualizado);
    }
}
/*
    * ¿Cuándo usar @PatchMapping?
    * Usa @PatchMapping cuando:
    * Quieres permitir que un cliente actualice solo ciertos campos de un recurso.
    * No deseas que el cliente tenga que enviar todo el objeto.
    * Notas adicionales
    * Internamente, @PatchMapping es una especialización de @RequestMapping(method = RequestMethod.PATCH).
    * Puedes usar librerías como Jackson o MapStruct para aplicar los cambios a tu entidad.
    * Es común manejar este tipo de lógica con DTOs o incluso con Map<String, Object> para mayor flexibilidad.
    * Supongamos que tienes una entidad Usuario
*/
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private Integer edad;

    // * Getters y setters
}
// * Repositorio
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
// * Controlador con @PatchMapping
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> actualizarParcialmenteUsuario(
            @PathVariable Long id,
            @RequestBody Map<String, Object> campos) {

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = optionalUsuario.get();

        // * Usamos reflexión para aplicar solo los campos enviados
        campos.forEach((clave, valor) -> {
            Field campo = ReflectionUtils.findField(Usuario.class, clave);
            if (campo != null) {
                campo.setAccessible(true);
                ReflectionUtils.setField(campo, usuario, valor);
            }
        });

        Usuario actualizado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(actualizado);
    }
}

// *  Ejemplo de solicitud PATCH (desde Postman o curl)
// * URL:

PATCH http://localhost:8080/usuarios/1

// * Cuerpo JSON:
{
  "nombre": "Nuevo Nombre",
  "edad": 30
}

/*
    * Solo se actualizarán los campos "nombre" y "edad" del usuario con ID 1.
    * Consideraciones de seguridad y validación
    * Valida los campos antes de aplicar cambios (ej., usar DTOs o validaciones con @Valid).
    * Revisa que no se puedan modificar campos sensibles como id, password, etc.
    * Opcionalmente, puedes filtrar campos permitidos o usar un servicio para encapsular la lógica.
*/