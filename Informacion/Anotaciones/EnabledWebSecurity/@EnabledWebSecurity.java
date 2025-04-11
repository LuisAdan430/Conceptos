/*
    * @EnableWebSecurity
    * Uso: Habilita la configuración de seguridad web personalizada.
    * Pertenece a:
*/
org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

/*
    * La anotación @EnableWebSecurity se utiliza en aplicaciones Spring Boot o Spring Framework para activar la
    * configuración de seguridad web proporcionada por Spring Security.
    * ¿Qué hace @EnableWebSecurity?
    * Cuando colocas @EnableWebSecurity en una clase, estás indicando a Spring que deseas habilitar la
    * seguridad web y personalizarla mediante una clase que extiende WebSecurityConfigurerAdapter (en
    * versiones antiguas) o que implementa SecurityFilterChain (en versiones recientes como Spring Security
    * 5.7+).
    
    * ¿Dónde se coloca?
    * Normalmente se pone en una clase de configuración, por ejemplo:
    * En versiones antiguas (hasta Spring Security 5.6):
*/
@Configuration
@EnableWebSecurity
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
    * En versiones nuevas (Spring Security 5.7 en adelante):
    * Spring recomienda usar SecurityFilterChain:
*/

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .anyRequest().authenticated()
            )
            .formLogin();
        return http.build();
    }
}

/*
    * ¿Qué hace internamente?
    * Registra los filtros de seguridad de Spring Security.
    * Activa la configuración automática de seguridad.
    * Permite que Spring detecte tus clases de configuración personalizada de seguridad.
    * ¿Es obligatorio usarla?
    * Sí, si deseas definir tu propia configuración de seguridad.
    * Si no defines @EnableWebSecurity, Spring Boot aplicará una configuración de seguridad por defecto.
*/