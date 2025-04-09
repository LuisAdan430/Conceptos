/*
    * La anotación @PathVariable en Spring se usa para extraer valores de la URL y asignarlos
    * a parámetros de un método en un controlador. Es muy útil cuando tienes partes variables
    * en la ruta de una solicitud HTTP.
    * Ejemplo básico:
    * Supongamos que tienes esta URL:
*/
GET /usuarios/15
/*
    * Y quieres capturar el 15 como el ID de usuario. Lo haces así:
*/
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping("/{id}")
    public String obtenerUsuarioPorId(@PathVariable Long id) {
        return "El ID del usuario es: " + id;
    }
}
/*
    * Explicación:
    * @PathVariable le dice a Spring que tome el valor de la ruta ({id}) y lo asigne al parámetro id.
    * El nombre entre llaves {id} debe coincidir con el nombre del parámetro, o puedes especificarlo manualmente.
    * Con nombre explícito:
*/
@GetMapping("/detalle/{codigo}")
public String obtenerDetalle(@PathVariable("codigo") String id) {
    return "Código recibido: " + id;
}

/*
    * Casos útiles:
    * Recursos REST como /productos/{id}, /clientes/{dni}, etc.
    * Rutas anidadas como /ordenes/{id}/detalle/{itemId}.
*/