/*
    * @RequestMapping
    * La anotación @RequestMapping se usa en Spring Framework para mapear solicitudes HTTP a
    * métodos de controladores (controllers). Es una de las anotaciones fundamentales en el desarrollo de
    * aplicaciones web con Spring MVC.
    * ¿Qué hace @RequestMapping?
    * Vincula una URL o patrón de URL con un método del controlador que debe manejar esa solicitud.
    * Sintaxis básica:
*/
@RequestMapping("/saludo")
public String saludar() {
    return "Hola Mundo";
}
/*
    * Cuando un cliente accede a /saludo, este método será ejecutado.
    * Atributos comunes:
    * Atributo      Descripción
    * value o path  La URL que se desea mapear.
    * method        El tipo de método HTTP (GET, POST, PUT, DELETE, etc.).
    * params        Filtra por parámetros específicos en la solicitud.
    * headersq      Filtra por cabeceras específicas en la solicitud.
    * produces      Define el tipo MIME que el método puede producir (como application/json).
    * consumes      Define el tipo MIME que el método puede consumir.
    * Ejemplo completo:
*/
@RequestMapping(
    value = "/usuarios",
    method = RequestMethod.GET,
    produces = "application/json"
)
public List<Usuario> listarUsuarios() {
    return usuarioService.obtenerTodos();
}
