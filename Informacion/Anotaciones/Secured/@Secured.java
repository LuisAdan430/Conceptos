/*
    * @Secured("ROLE_ADMIN")
    * Uso: Define roles necesarios para acceder al método.
    * Pertenece a:
*/
org.springframework.security.access.annotation.Secured

/*
    * La anotación @Secured en Spring Security se utiliza para restringir el acceso a métodos o
    * clases específicas según los roles del usuario autenticado. Es una forma sencilla de aplicar
    * control de acceso declarativo en métodos de servicios, controladores, etc.
    * ¿Cómo funciona?
    * @Secured permite especificar uno o varios roles autorizados que pueden acceder al
    * método. Por ejemplo:
*/
@Secured("ROLE_ADMIN")
public void metodoSoloParaAdmins() {
    // * código restringido a administradores
}
// * También puedes permitir múltiples roles:
@Secured({"ROLE_ADMIN", "ROLE_USER"})
public void metodoParaAdminsYUsuarios() {
    // * código accesible para ADMIN y USER
}
/*
    * Requisitos para usar @Secured
    * Habilitar la seguridad con anotaciones en tu clase de configuración de seguridad:   
*/
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // * configuración de seguridad
}

/*
    * Si estás usando Spring Boot 3 y Spring Security 6, la clase ya no extiende
    * WebSecurityConfigurerAdapter, pero la anotación
    * @EnableMethodSecurity(securedEnabled = true) sigue aplicando.
    * Diferencia con otras anotaciones
    * Anotación               Característica
    * @Secured                Usa roles tipo "ROLE_ADMIN". No soporta expresiones más complejas.
    * @PreAuthorize           Permite usar expresiones SpEL (hasRole('ADMIN'), hasAuthority(...), etc.).
    * @RolesAllowed           Similar a @Secured, pero forma parte de JSR-250.
    * Dependencia
    * Si estás usando Spring Boot Starter Security, ya viene incluida. Si no, asegúrate de tener:
*/
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
