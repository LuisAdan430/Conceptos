/*
    TODO : Cuando usas la anotación @Component en Spring, el framework automáticamente detecta y gestiona el ciclo de vida del bean.
    TODO : Esto incluye la creación, inyección de dependencias y destrucción del bean cuando la aplicación se cierra.
    TODO : ¿Spring lo escanea automáticamente?
    TODO : Sí, Spring escanea automáticamente las clases anotadas con @Component (y otras como @Service,
    TODO : @Repository, @Controller) dentro de los paquetes especificados en la configuración. Esto lo hace a
    TODO : través de la anotación @ComponentScan o cuando usas @SpringBootApplication, que ya incluye un
    TODO : escaneo por defecto.
    * ¿Es obligatorio usar @PreDestroy?
    * No, @PreDestroy es opcional. Esta anotación sirve para ejecutar código justo antes de que el bean sea
    * destruido. Por ejemplo, si necesitas cerrar conexiones, liberar recursos o ejecutar alguna lógica de
    * limpieza antes de que el bean desaparezca, puedes usar @PreDestroy.
*/
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class MiComponente {

    public MiComponente() {
        System.out.println("Bean creado: MiComponente");
    }

    @PreDestroy
    public void limpiar() {
        System.out.println("Bean destruido: Liberando recursos...");
    }
}
/*
    * Ejemplo de uso de @PreDestroy:
*/
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class MiComponente {

    public MiComponente() {
        System.out.println("Bean creado: MiComponente");
    }

    @PreDestroy
    public void limpiar() {
        System.out.println("Bean destruido: Liberando recursos...");
    }
}
/*
    * Si tu bean no necesita una limpieza especial antes de ser destruido, no es necesario que agregues
    * @PreDestroy. Spring se encargará automáticamente de la destrucción del bean cuando la aplicación termine.
    * Resumen:
    * @Component permite que Spring escanee y administre el bean automáticamente.
    * No es obligatorio usar @PreDestroy, pero es útil si necesitas liberar recursos antes de la destrucción del bean.
    * Spring maneja el ciclo de vida del bean sin necesidad de configuraciones adicionales, pero puedes personalizarlo si lo requieres.
    * Si no necesitas hacer limpieza específica, puedes omitir @PreDestroy sin problema. 🚀
*/