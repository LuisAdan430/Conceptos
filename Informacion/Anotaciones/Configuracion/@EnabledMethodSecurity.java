/*
    * @EnableMethodSecurity (nuevo en Spring Security 6)
    * Uso: Activa la seguridad basada en métodos, reemplazo moderno de @EnableGlobalMethodSecurity.
    * Pertenece a:
*/
org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

/*
    * La anotación @EnableMethodSecurity en Spring Framework (particularmente en Spring Security 6+,
    * parte de Spring Boot 3.x) se utiliza para habilitar la seguridad a nivel de método dentro de una
    * aplicación.
    * ¿Qué hace @EnableMethodSecurity?
    * Permite que se apliquen restricciones de seguridad en métodos específicos de tus servicios (o
    * controladores) mediante anotaciones como:
    * @PreAuthorize
    * @PostAuthorize
    * @Secured
    * @RolesAllowed
    * Estas restricciones se evalúan antes o después de que se invoque un método.
    * Ejemplo básico de uso
*/
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    // *  Configuración adicional si es necesario
}
/*
    * Opciones disponibles
    * La anotación tiene algunos parámetros que puedes ajustar:
*/
@EnableMethodSecurity(
    prePostEnabled = true,     // * Habilita @PreAuthorize y @PostAuthorize (por defecto true)
    securedEnabled = true,     // * Habilita @Secured
    jsr250Enabled = true       // * Habilita @RolesAllowed (JSR-250)
)
/*
    * prePostEnabled
    * Activa el soporte para @PreAuthorize y @PostAuthorize.
    * securedEnabled
    * Activa @Secured, una forma antigua de restringir métodos por roles.
    * jsr250Enabled
    * Activa @RolesAllowed, una anotación estandarizada por JSR-250.
    * Ejemplos de anotaciones compatibles
*/
@PreAuthorize("hasRole('ADMIN')")
public void metodoAdmin() {
    // * Solo accesible por ADMIN
}

@Secured("ROLE_USER")
public void metodoUsuario() {
    // * Solo accesible por USER
}

@RolesAllowed("ROLE_MANAGER")
public void metodoGerente() {
    // * Solo accesible por MANAGER
}
/*
    * Requisitos
    * Spring Security 6 (Spring Boot 3+ usa Spring Security 6)
    * Si estás en versiones anteriores (Spring Security 5 o menos), se usaba @EnableGlobalMethodSecurity en lugar de @EnableMethodSecurity.
*/

// * Versión anterior (Spring Security 5 o menor)
@EnableGlobalMethodSecurity(prePostEnabled = true)

/*
    * ¿Dónde se usa comúnmente?
    * En servicios (@Service)
    * En controladores (@RestController) si deseas aplicar seguridad directamente a métodos
    * Útil para proteger lógica crítica más allá de los filtros o controladores HTTP
*/
