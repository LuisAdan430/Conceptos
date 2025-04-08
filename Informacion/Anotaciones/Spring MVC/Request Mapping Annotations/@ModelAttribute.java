/*
    * La anotación @ModelAttribute en Spring Framework se utiliza para vincular
    * datos entre el modelo y la vista, o para inyectar datos en un controlador
    * antes de que se ejecute un método handler (@RequestMapping,
    * @GetMapping, etc.). Aquí te explico los usos más comunes de @ModelAttribute:
    * Vincular datos del formulario a un objeto Java
    * Cuando recibes datos de un formulario HTML, puedes usar @ModelAttribute para mapearlos a un objeto.
*/
@PostMapping("/guardar")
public String guardarPersona(@ModelAttribute Persona persona) {
    // * Aquí 'persona' tendrá los datos del formulario
    personaService.save(persona);
    return "resultado";
}
/*
    *  Si los nombres de los campos del formulario coinciden con los atributos del objeto, Spring los mapea automáticamente.
    *  Cargar datos previos en el modelo
    *  Puedes usar @ModelAttribute en métodos separados para agregar atributos al modelo antes de que se invoque un método controlador: 
*/
@ModelAttribute("paises")
public List<String> cargarPaises() {
    return List.of("México", "Colombia", "Argentina");
}
/*
    * Esto añade un atributo llamado "paises" al modelo en todas las peticiones a ese controlador.
    * Inicializar un objeto antes de procesar una solicitud
    * Si necesitas preparar un objeto antes de usarlo en otros métodos:
*/

@ModelAttribute
public void prepararModelo(Model model) {
    model.addAttribute("titulo", "Registro de Persona");
}

/*
    * Cosas importantes a saber
    * @ModelAttribute se puede usar a nivel de método o como parámetro.
    * Se puede usar en combinación con vistas Thymeleaf, JSP, etc.
    * Se ejecuta antes del método handler (como @GetMapping, etc).

*/