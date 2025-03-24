/*
    * Extiende @Controller e incluye @ResponseBody en todos los métodos.
    * Se usa en API REST para devolver JSON o XML.
 */

@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {
    @GetMapping
    public List<String> listarClientes() {
        return List.of("Cliente 1", "Cliente 2");
    }
}
