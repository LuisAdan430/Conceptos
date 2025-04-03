/*
    TODO: Factory Method
    TODO: Patrón: Factory Method 
    TODO: Descripción: Se usa para delegar la creación de objetos a una fábrica en lugar de crearlos directamente.
    TODO: Ejemplo en Spring Boot:
*/
@Configuration
public class AppConfig {
    @Bean
    public MiServicio miServicio() {
        return new MiServicio();
    }
}

/*
    TODO: Uso en Spring: @Bean, FactoryBean.
*/