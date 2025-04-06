/*
    * @PostMapping
    * Qué es @PostMapping?
    * @PostMapping es una anotación de Spring MVC que se utiliza para mapear solicitudes HTTP POST a
    * métodos del controlador. Es una forma especializada de @RequestMapping(method =
    * RequestMethod.POST).
    
    * Sintaxis básica
*/
@PostMapping("/ruta")
public ResponseEntity<String> metodo(@RequestBody TuObjeto objeto) {
    //* lógica aquí
    return ResponseEntity.ok("¡Todo bien!");
}
/*
    * Características clave
    * Ruta del endpoint:
*/
@PostMapping("/usuarios")

// * Recibir datos del cuerpo (JSON, por ejemplo):
public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario)

// * Recibir parámetros por URL (query params):
@PostMapping("/buscar")
public String buscar(@RequestParam String nombre)

// * Usar con @PathVariable:
@PostMapping("/usuarios/{id}")
public String actualizar(@PathVariable Long id, @RequestBody Usuario u)

// * Especificar tipo de contenido (content-type):
@PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

/*
    * Retornar diferentes tipos de respuestas:
    *   String: nombre de una vista.
    *   ResponseEntity<T>: más control (códigos de estado, headers, etc.).
    *   @ResponseBody o métodos en clases anotadas con @RestController.
    *   Ejemplo completo
*/

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        // * Guardar el usuario...
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping("/buscar")
    public ResponseEntity<String> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok("Buscando usuario con nombre: " + nombre);
    }
}

/*
    *  Seguridad
    *  Con Spring Security, puedes restringir el acceso a ciertos @PostMapping:
*/
@PreAuthorize("hasRole('ADMIN')")
@PostMapping("/admin-only")
public ResponseEntity<?> accionPrivada() {
    return ResponseEntity.ok("Solo para admins");
}

