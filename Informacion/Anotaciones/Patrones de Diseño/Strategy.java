/*
    * Strategy
    * Patrón: Strategy
    * Descripción: Define una familia de algoritmos, los encapsula y los hace intercambiables.
    * Ejemplo en Spring Boot: Uso de Spring Security
*/
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .build();
    }
}
/*
    * Uso en Spring: AuthenticationProvider, SecurityFilterChain.
*/