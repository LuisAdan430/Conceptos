/*
    * La anotación @RequestAttribute en Spring Framework se utiliza para acceder a atributos
    * que ya han sido agregados al objeto HttpServletRequest por algún filtro, interceptor o
    * middleware antes de que se ejecute el controlador.
    * ¿Para qué sirve?
    * Cuando otro componente (como un filtro, interceptor, etc.) ha colocado datos en el request usando:
*/
request.setAttribute("usuario", usuario);
/*
    * Entonces, puedes acceder a ese atributo directamente en tu controlador con:
*/
@GetMapping("/perfil")
public ResponseEntity<?> obtenerPerfil(@RequestAttribute("usuario") Usuario usuario) {
    return ResponseEntity.ok(usuario);
}
/*
    * Características principales:
    * No extrae datos del body, query params o path variables, solo del HttpServletRequest.setAttribute().
    * Se usa típicamente junto con filtros/interceptores para inyectar información común (como el usuario autenticado, tokens decodificados, etc.).
    * Puede ser obligatorio o no:
*/
@RequestAttribute(name = "usuario", required = false) Usuario usuario
/*
    * Diferencia con otras anotaciones:
    * @RequestParam             Query parameters (?id=5)
    * @PathVariable             Variables en la URL (/usuarios/{id})
    * @RequestBody              Contenido del cuerpo de la solicitud (JSON, etc)
    * @RequestAttribute	        Atributos del objeto HttpServletRequest
    * @ModelAttribute           Datos form-data o atributos para binding

*/