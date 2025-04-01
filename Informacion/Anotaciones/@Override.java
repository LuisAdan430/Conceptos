/*
    * La anotación @Override en Java no pertenece específicamente a Spring, sino que es una anotación de Java 
    * que forma parte del paquete java.lang. Su propósito es indicar que un método está sobrescribiendo un método de una clase padre o una interfaz.
    * Función de @Override
    *   Verificación en tiempo de compilación
    *       Asegura que el método sobrescrito existe en la superclase o interfaz.
    *       Evita errores al escribir mal el nombre del método o su firma.
    *   Mejor legibilidad del código
    *       Indica de manera explícita que el método sobrescribe uno de la superclase, facilitando la comprensión.
    *   Mantenimiento del código
    *       Si la firma del método padre cambia, el compilador generará un error, evitando posibles fallos en tiempo de ejecución.
    * Ejemplo en Java (sin relación con Spring)
*/
class Padre {
    void saludar() {
        System.out.println("Hola desde la clase Padre");
    }
}

class Hijo extends Padre {
    @Override
    void saludar() {
        System.out.println("Hola desde la clase Hijo");
    }
}
/*
    *  Si por error escribimos @Override void saludo() { ... }, el compilador lanzará un error porque no existe saludo() en Padre.
    *  Uso en Spring
    *  Si bien @Override no es exclusiva de Spring, se usa comúnmente en clases que implementan interfaces de Spring, 
    *  como Service, Repository, Controller, etc.
    *  Ejemplo en Spring Boot (Servicio con @Service)
*/
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return new Usuario(id, "Juan Pérez");
    }
}
/*
    * Aquí, @Override se usa porque UsuarioServiceImpl implementa la interfaz UsuarioService, 
    * asegurando que el método obtenerUsuarioPorId está correctamente sobrescrito.
    * Conclusión 
    * @Override no es específica de Spring, sino de Java.
    * Se usa para sobrescribir métodos de una superclase o una interfaz.
    * Ayuda a evitar errores de compilación y mejora la legibilidad del código.
    * En Spring, se usa en servicios, controladores y repositorios que implementan interfaces.
*/