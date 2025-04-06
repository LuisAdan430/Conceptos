/*
    ^ @EnabledGlobalMethodSecurity es una anotación de Spring Security que se utiliza para habilitar la
    ^ seguridad a nivel de métodos en una aplicación Spring. Esta anotación permite aplicar restricciones de
    ^ seguridad directamente sobre los métodos usando anotaciones como @PreAuthorize, @PostAuthorize,
    ^ @Secured, etc.
    
    ^ IMPORTANTE: A partir de Spring Security 6 / Spring Boot 3, esta anotación ha sido
    ^ reemplazada por otras configuraciones, ya que se migró a una API basada en
    ^ SecurityFilterChain. Pero aún se usa en muchos proyectos que usan versiones anteriores.
    
    ^ Ubicación:
    ^ Se coloca en una clase de configuración (@Configuration), generalmente acompañada de
    ^ @EnableWebSecurity
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // ^ tu configuración de seguridad aquí
}

/*
    ^  ¿Qué habilita?
    ^  Atributo             	¿Qué permite?
    ^  prePostEnabled=true      Activa @PreAuthorize y @PostAuthorize
    ^  securedEnabled=true      Activa @Secured
    ^  jsr250Enabled=true       Activa @RolesAllowed (de JSR-250)
    ^  Anotaciones relacionadas
    ^  @PreAuthorize y @PostAuthorize
*/
@PreAuthorize("hasRole('ADMIN')")
public void metodoSoloParaAdmin() { ... }

@PostAuthorize("returnObject.owner == authentication.name")
public Cuenta obtenerCuenta() { ... }

/*
    *  @Secured
*/
@Secured("ROLE_ADMIN")
public void metodoProtegido() { ... }

/*
    * @RolesAllowed (requiere jsr250Enabled=true)
*/
@RolesAllowed("ADMIN")
public void soloAdmin() { ... }

// *  Ejemplo completo
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true, 
    securedEnabled = true, 
    jsr250Enabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin();
    }
}

/*
    *  A partir de Spring Security 6
    * @EnableGlobalMethodSecurity fue reemplazada por @EnableMethodSecurity.
    *  Esto se debe a que WebSecurityConfigurerAdapter también fue deprecated.
*/

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    // *  define tu SecurityFilterChain bean aquí
}
