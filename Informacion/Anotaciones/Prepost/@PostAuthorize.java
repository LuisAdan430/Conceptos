/*
    * @PostAuthorize("returnObject.owner == authentication.name")
    * Uso: Evalúa la expresión después de la ejecución del método.
    * Pertenece a:
*/
org.springframework.security.access.prepost.PostAuthorize

/*
    * La anotación @PostAuthorize es parte de Spring Security y se utiliza para aplicar restricciones de
    * autorización después de que un método se ha ejecutado, es decir, evalúa la expresión de seguridad
    * después de que el método retorna un valor.
    
    * ¿Para qué sirve @PostAuthorize?
    * Permite controlar si el resultado devuelto por un método puede ser accedido por el usuario actual. Si la
    * expresión evaluada resulta en false, Spring lanzará una excepción de acceso denegado
    * (AccessDeniedException).
     
    * Ejemplo de uso
    * Supongamos que tienes un método que devuelve un objeto y quieres asegurarte de que el usuario sólo
    * pueda acceder al objeto si cumple cierta condición:
*/
@PreAuthorize("hasRole('USER')")
@PostAuthorize("returnObject.owner == authentication.name")
public Documento getDocumento(Long id) {
    return documentoService.buscarPorId(id);
}
/*
    * Explicación:
    * @PreAuthorize("hasRole('USER')"): valida antes de ejecutar el método que el usuario tenga rol USER.}
    * @PostAuthorize("returnObject.owner == authentication.name"): después de ejecutar el método, verifica que el dueño del documento (owner)
    * sea el mismo que el usuario autenticado.
    * Sintaxis común en expresiones
    * returnObject: se refiere al objeto devuelto por el método.
    * authentication: representa la autenticación actual (usuario logueado).
    * principal: representa al objeto principal del usuario autenticado.
    * Métodos como hasRole('ROLE_ADMIN'), hasPermission(...), etc.
    * Cuándo usar @PostAuthorize
    * Cuando necesitas hacer validaciones basadas en el resultado del método.
    * Cuando el método devuelve datos sensibles que deben filtrarse o restringirse por usuario.
    * En consultas tipo “findById” donde se necesita chequear la propiedad del objeto.

*/