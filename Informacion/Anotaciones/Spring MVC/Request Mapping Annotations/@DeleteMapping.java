/*
    * La anotación @DeleteMapping en Spring se utiliza para manejar solicitudes HTTP del tipo DELETE en
    * controladores REST. Forma parte del paquete org.springframework.web.bind.annotation y es una de
    * las anotaciones especializadas que Spring ofrece para mapear rutas HTTP a métodos de controlador.
    * ¿Qué hace @DeleteMapping?
    * Asocia una URL (endpoint) con un método Java que se ejecutará cuando se realice una petición HTTP DELETE a dicha URL.
    * Sintaxis básica
*/
@DeleteMapping("/recurso/{id}")
public ResponseEntity<Void> eliminarRecurso(@PathVariable Long id) {
    servicio.eliminar(id);
    return ResponseEntity.noContent().build();
}

/*
    * Explicación de elementos clave:
    * Elemento                      	Descripción
    * @DeleteMapping("/recurso/{id}")   Asocia el método con la URL /recurso/{id} para peticiones DELETE.
    * @PathVariable                     Captura el valor {id} de la URL y lo pasa como argumento al método.
    * ResponseEntity<Void>              Devuelve una respuesta HTTP sin cuerpo, común en operaciones DELETE exitosas (código 204 No Content).
    * Ejemplo completo
*/
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build(); // *  204 No Content
    }
}

/*
    * ¿Qué pasa cuando llamas este endpoint?
    * Si haces una petición DELETE a /api/usuarios/5, Spring ejecuta eliminarUsuario(5). El servicio puede
    * eliminar al usuario con ID 5, y si todo va bien, responde con un 204 No Content, indicando que la
    * eliminación fue exitosa pero no hay contenido que devolver.
*/
