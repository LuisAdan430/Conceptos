/*
    * Especialización de @Component.
    * Se usa en controladores MVC que manejan peticiones HTTP.
*/
@Controller
public class ClienteController {
    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", List.of("Cliente 1", "Cliente 2"));
        return "clientes";
    }
}
