/*
    * @AuthenticationPrincipal
    * Uso: Inyecta el objeto del usuario autenticado directamente en un método.
    * Pertenece a:
*/
org.springframework.security.core.annotation.AuthenticationPrincipal

/*
    * Claro, la anotación @AuthenticationPrincipal es parte de Spring Security y se utiliza para acceder
    * directamente al usuario autenticado en un controlador o componente dentro de una aplicación Spring Boot.
    * ¿ Qué es @AuthenticationPrincipal?
    * Es una anotación que permite inyectar directamente el principal (usuario autenticado) en un método
    * de un controlador o en otro componente gestionado por Spring.
    * Esta anotación es muy útil para acceder al usuario actual sin tener que trabajar manualmente con el
    * SecurityContextHolder.
    * Ejemplo básico
    * Supongamos que tienes una clase de usuario personalizada llamada UsuarioDetails que implementa UserDetails:
*/ 
public class UsuarioDetails implements UserDetails {
    private String username;
    private String nombreCompleto;

    //  * Métodos de UserDetails (getAuthorities, getPassword, etc.)

    public String getNombreCompleto() {
        return nombreCompleto;
    }
}

/*
    * Ahora en un controlador puedes hacer:
*/

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @GetMapping("/perfil")
    public ResponseEntity<String> obtenerPerfil(@AuthenticationPrincipal UsuarioDetails usuario) {
        return ResponseEntity.ok("Hola, " + usuario.getNombreCompleto());
    }
}
/*
    * ¿Qué inyecta @AuthenticationPrincipal?
    * Puede inyectar:
    * Un objeto que implementa UserDetails.
    * Un Principal estándar (java.security.Principal).
    * O cualquier objeto que hayas usado como principal en tu implementación de seguridad.
    * Comparación con SecurityContextHolder
    * Sin @AuthenticationPrincipal:
*/
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
UsuarioDetails usuario = (UsuarioDetails) auth.getPrincipal();

/*
    * Con @AuthenticationPrincipal:
*/
@GetMapping("/perfil")
public ResponseEntity<?> perfil(@AuthenticationPrincipal UsuarioDetails usuario) {
    return ResponseEntity.ok(usuario);
}
/*
    * Más limpio y directo.
    * Con Principal estándar
*/
@GetMapping("/nombre-usuario")
public ResponseEntity<String> getNombreUsuario(@AuthenticationPrincipal Principal principal) {
    return ResponseEntity.ok("Usuario: " + principal.getName());
}
/*
    * Extras
    * También puedes usar @AuthenticationPrincipal(expression = "...") para acceder directamente a una propiedad.
*/
@GetMapping("/nombre")
public String nombre(@AuthenticationPrincipal(expression = "nombreCompleto") String nombreCompleto) {
    return "Bienvenido, " + nombreCompleto;
}
/*
    * (Esto funciona gracias a Spring Expression Language (SpEL))
    ^ Entonces me estas diciendo que esta etiqueta sirve solo paara obtener datos de usuario
    * ¡Exactamente! 🎯 La anotación @AuthenticationPrincipal sirve específicamente para acceder a los datos del usuario 
    * autenticado dentro del contexto de Spring Security.
    * Entonces, ¿para qué se usa?
    * Se usa únicamente para obtener información del usuario que ya inició sesión, 
    * y no tiene otro propósito que ese.
    * ¿Por qué es útil?
    * Porque te evita tener que acceder manualmente al SecurityContextHolder, y te da directamente el
    * objeto que representa al usuario.
    * Por ejemplo, en lugar de hacer esto:
*/
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
MiUsuario usuario = (MiUsuario) auth.getPrincipal();

/*
    * Puedes simplemente hacer esto:
*/
@GetMapping("/perfil")
public ResponseEntity<?> perfil(@AuthenticationPrincipal MiUsuario usuario) {
    return ResponseEntity.ok(usuario);
}
/*
    * ¿Qué hace?                                    ¿Para qué sirve?
    * Inyecta el "principal" (usuario)              Obtener el usuario autenticado en tus controladores
    * Soporta clases que implementen UserDetails    Acceder a propiedades del usuario como email, nombre, roles, etc.
    * Se puede usar con SpEL (expression)           Obtener propiedades específicas directamente
*/