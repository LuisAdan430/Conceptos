/*
    * La anotación @Inject se usa para la inyección de dependencias y proviene de la especificación Jakarta
    * Dependency Injection (Jakarta DI), antes conocida como Java Dependency Injection (JSR-330). Puede
    * ser utilizada en proyectos de Spring o Jakarta EE.
    * Uso de @Inject en Spring
    * Aunque Spring tiene su propia anotación @Autowired, también soporta @Inject de Jakarta DI de
    * manera transparente. Ambas anotaciones funcionan de manera similar, pero @Inject es parte del
    * estándar Jakarta y no depende de Spring.
    * Ejemplo en Spring Boot
*/
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class MiServicio {
    
    private final MiRepositorio miRepositorio;

    @Inject
    public MiServicio(MiRepositorio miRepositorio) {
        this.miRepositorio = miRepositorio;
    }

    public void ejecutar() {
        System.out.println("Ejecutando servicio...");
    }
}
/*
    *  Equivalente con @Autowired
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiServicio {

    private final MiRepositorio miRepositorio;

    @Autowired
    public MiServicio(MiRepositorio miRepositorio) {
        this.miRepositorio = miRepositorio;
    }
}
/*
    * Diferencias clave entre @Inject y @Autowired en Spring:
    * @Inject pertenece a Jakarta DI, mientras que @Autowired es de Spring.
    * @Autowired permite configurar el modo opcional (required = false), mientras que @Inject no.
    * @Autowired soporta calificación por nombre con @Qualifier, mientras que @Inject usa @Named.
    * Uso de @Inject en Jakarta EE
    * En Jakarta EE, @Inject se usa junto con CDI (Contexts and Dependency Injection) para inyectar bean
    * administrados.
    * Ejemplo en Jakarta EE
*/
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@ApplicationScoped
public class MiServicio {

    @Inject
    @Named("miRepositorio")
    private MiRepositorio miRepositorio;

    public void ejecutar() {
        System.out.println("Ejecutando servicio en Jakarta EE...");
    }
}
/*
    * Aquí @Inject se usa para inyectar un bean administrado por CDI, y @Named("miRepositorio") se usa para seleccionar un bean específico.
    * ¿Cuál deberías usar?
    * Si trabajas con Spring, @Autowired es la opción más utilizada.
    * Si prefieres una solución más estándar y portable, @Inject es una buena alternativa.
    *  En Jakarta EE, siempre usa @Inject, ya que @Autowired es específico de Spring.

*/

/*
    ^ SE PUEDEN MEZCLAR CON @AUTOWIRED.
    ^ Sí, puedes usar @Autowired y @Inject en diferentes instancias dentro de la misma clase sin ningún
    ^ problema. Cada anotación funcionará según su respectivo gestor de dependencias.
    ^ Ejemplo: Mezcla de @Autowired y @Inject
*/

import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiServicio {

    @Autowired
    private MiRepositorioSpring miRepositorioSpring; //  ^ Inyección con Spring

    @Inject
    private MiRepositorioJakarta miRepositorioJakarta; // ^ Inyección con Jakarta EE

    public void ejecutar() {
        System.out.println("Ejecutando servicio...");
        miRepositorioSpring.operacion();
        miRepositorioJakarta.operacion();
    }
}
/*
    ^  ¿Cuándo tiene sentido hacer esto?
    ^  Si trabajas en un proyecto híbrido, donde parte del código usa Spring y otra parte usa Jakarta EE/CDI.
    ^  Si estás en un proceso de migración, por ejemplo, moviendo un proyecto de Spring a Jakarta EE (o viceversa).
    ^  Si tienes dependencias externas que usan @Inject y otras que usan @Autowired.
    !  Posibles problemas
    !  Si ambos frameworks están activos en el mismo proyecto, es importante asegurarse de que no haya
    !  conflictos en la gestión de beans. Spring gestionará los @Autowired, mientras que Jakarta EE manejará
    !  los @Inject.

*/
