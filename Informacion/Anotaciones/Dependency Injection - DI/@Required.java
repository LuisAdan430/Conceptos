/*
    * La anotación @Required proviene del framework Spring y se usaba en versiones anteriores para indicar
    * que una propiedad inyectada en un bean de Spring era obligatoria. Esta anotación pertenece al paquete:
*/
import org.springframework.beans.factory.annotation.Required;
/*
    * Origen y Propósito
    * La anotación @Required se aplicaba sobre métodos setter en un bean de Spring para indicar que la
    * propiedad correspondiente debía ser obligatoriamente establecida en el contexto de Spring. Si la
    * dependencia no se proporcionaba en el archivo de configuración, se generaba un error en tiempo de ejecución.
    * Ejemplo de Uso (Obsoleto)
*/ 
import org.springframework.beans.factory.annotation.Required;

public class Cliente {
    private Servicio servicio;

    @Required
    public void setServicio(Servicio servicio) { //* El servicio es obligatorio
        this.servicio = servicio;
    }
}
/*
    * En este caso, si Spring no encontraba un Servicio inyectado en Cliente, lanzaba una excepción.
    * Estado Actual de @Required
    * A partir de Spring 5, la anotación @Required fue obsoleta y eliminada, ya que Spring recomienda
    * utilizar inyección por constructor en lugar de inyección por setter. En su lugar, se recomienda el uso de
    * @Autowired (o incluso @Inject) para indicar dependencias requeridas.
    ^ ¿De qué tipo de etiquetas proviene?
    ^ @Required pertenece a las anotaciones de inyección de dependencias dentro del ecosistema de
    ^ Spring Framework, específicamente en el módulo de Spring Beans. Estas anotaciones facilitan la
    ^ gestión de dependencias en aplicaciones basadas en Spring.
    ^ Alternativa Recomendada (Usando Constructor Injection con @Autowired)
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Cliente {
    private final Servicio servicio;

    @Autowired
    public Cliente(Servicio servicio) {  // ^ Se recomienda usar inyección por constructor
        this.servicio = servicio;
    }
}

/*
    ^ Conclusión 
    ^ @Required era una anotación de Spring utilizada para obligar la inyección de dependencias en setters.
    ^ Fue obsoleta desde Spring 5 debido a mejores prácticas como inyección por constructor.
    ^ Se recomienda usar @Autowired en constructores en su lugar.
*/