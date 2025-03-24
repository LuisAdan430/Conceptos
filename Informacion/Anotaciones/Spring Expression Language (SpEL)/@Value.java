/*
    TODO: Uso de la etiqueta  @Value() en spring y a que tipo de anotaciones pertenece
    * En Spring, la anotación @Value() se usa para inyectar valores en campos, métodos o parámetros de
    * constructor. Se puede utilizar para asignar valores desde el archivo de propiedades
    * (application.properties o application.yml), variables de entorno, o incluso valores literales.
    ^ Tipo de anotación
    ^ @Value pertenece a las anotaciones de inyección de dependencias en Spring Framework,
    ^ específicamente en el contexto de Spring Expression Language (SpEL).
    TODO: Ejemplos de uso de @Value()
    * Inyectar valores desde application.properties
    * Si tienes un archivo application.properties con:
*/
app.name=Mi Aplicación
app.version=1.0.0
/*
    * Puedes inyectar estos valores en una clase de servicio:
*/
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigService {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    public void mostrarInfo() {
        System.out.println("Nombre: " + appName + ", Versión: " + appVersion);
    }
}
/*
    * Asignar un valor predeterminado
    * Si la propiedad no está definida, puedes asignar un valor por defecto: 
*/
@Value("${app.descripcion:Aplicación por defecto}")
private String descripcion;

/*
    * Si app.descripcion no está en application.properties, se usará "Aplicación por defecto".
    * Inyectar listas o arrays
    * Puedes inyectar una lista separada por comas:
*/
app.tipos=ADMIN,USER,GUEST

@Value("#{'${app.tipos}'.split(',')}")
private List<String> tiposUsuarios;

/*
    *  Inyectar valores desde variables de entorno
    *  Si deseas obtener una variable de entorno:
*/
@Value("${HOME}") //^  Obtiene el valor de la variable de entorno HOME
private String homeDirectory;

/*
    *  Usar expresiones de Spring (SpEL)
    *  Puedes hacer cálculos o concatenaciones:
*/
@Value("#{2 * 5}") // & Evalúa la expresión y asigna 10
private int resultado;

@Value("#{'${app.name}' + ' - ' + '${app.version}'}")
private String nombreCompleto;
/*
    * Diferencia con @ConfigurationProperties
    * Si necesitas inyectar múltiples valores relacionados, en lugar de usar @Value en cada uno, 
    * es mejor usar @ConfigurationProperties:
*/

@ConfigurationProperties(prefix = "app")
@Component
public class AppConfig {
    private String name;
    private String version;

    //  *Getters y Setters
}

/*
    ^ Esto evita repetir muchas veces @Value.
    ^ Conclusión
    ^ @Value se usa para inyectar valores desde propiedades, variables de entorno o valores literales.
    ^ Es parte de Spring Expression Language (SpEL) y se usa dentro del mecanismo de inyección de  dependencias.
    ^ Para muchos valores agrupados, es mejor usar @ConfigurationProperties.

*/