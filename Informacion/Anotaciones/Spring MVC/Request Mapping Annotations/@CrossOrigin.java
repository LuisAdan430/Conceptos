/*
    * La anotación @CrossOrigin en Spring Framework (especialmente en
    * aplicaciones Spring Boot o Spring MVC) se utiliza para habilitar CORS
    * (Cross-Origin Resource Sharing), es decir, permite que un cliente web (como
    * una aplicación Angular, React, etc.) que se ejecuta en un dominio diferente al
    * del backend pueda realizar peticiones HTTP al servidor.
     
    * ¿Qué es CORS?
    * Por defecto, los navegadores bloquean las peticiones HTTP que se hacen
    * desde un origen (dominio) diferente al del servidor, por razones de
    * seguridad. Esto se llama restricción del mismo origen.
    * Con @CrossOrigin, puedes especificar qué orígenes están permitidos para
    * hacer solicitudes a tu API.
    
    * Uso básico de @CrossOrigin
*/
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class MiControlador {
    
    @GetMapping("/datos")
    public List<String> obtenerDatos() {
        return List.of("Dato 1", "Dato 2");
    }
}

/*
    * En este ejemplo, solo el frontend que corre en http://localhost:4200 (por
    * ejemplo, un proyecto Angular) podrá acceder al endpoint /api/datos.
    * Parámetros más comunes
    * Parámetro            Descripción
    * origins              Lista de orígenes permitidos (ej. "http://localhost:4200").
    * methods              Métodos HTTP permitidos (ej. GET, POST, PUT, etc.).
    * allowedHeaders       Encabezados HTTP que se permiten en la solicitud. 
    * exposedHeaders       Encabezados que el cliente puede leer desde la respuesta
    * maxAge               Tiempo en segundos que el navegador puede cachear la respuesta de preflight.
    * allowCredentials     Si se permite el uso de credenciales (cookies, tokens, etc.).
    * Ejemplo más completo
*/
@CrossOrigin(
    origins = "http://localhost:4200",
    methods = {RequestMethod.GET, RequestMethod.POST},
    allowedHeaders = "*",
    allowCredentials = "true"
)
/*
    * Nivel de aplicación
    * Además de usar @CrossOrigin a nivel de controlador o método, también
    * puedes configurar CORS globalmente:
*/
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:4200")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*")
            .allowCredentials(true);
    }
}
