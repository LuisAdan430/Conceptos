/*
    * MVC (Model-View-Controller)
    * Patrón: MVC
    * Descripción: Divide la aplicación en tres capas: Modelo, Vista y Controlador.
    * Ejemplo en Spring Boot:
*/
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }
}
//  * Uso en Spring: @Controller, @RestController, @Service, @Repository.