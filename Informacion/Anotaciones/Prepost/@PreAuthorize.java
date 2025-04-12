/*
    * @PreAuthorize("hasRole('ADMIN')")
    * Uso: Restringe el acceso a un método antes de su ejecución.
    * Pertenece a:
*/
org.springframework.security.access.prepost.PreAuthorize


/*
    * La anotación @PreAuthorize es una herramienta muy poderosa en Spring Security que se utiliza para aplicar
    * reglas de autorización antes de que se ejecute un método. Básicamente, sirve para controlar el acceso a
    * métodos de forma declarativa utilizando expresiones en el lenguaje SpEL (Spring Expression Language). A
    * continuación, te explico en detalle sus características y cómo se utiliza:
    
    * Función Principal
    *   Control de Acceso Declarativo:
    *   Permite especificar, a nivel de método, quién o qué condiciones se deben cumplir para que el método
    *   sea ejecutado. Esto se logra añadiendo la anotación directamente sobre el método o a nivel de clase, y
    *   estableciendo una expresión que será evaluada antes de la ejecución del método.
    * 
    *   Verificación Previas a la Ejecución:
    *   Al usarse la anotación @PreAuthorize, la verificación se realiza antes de que el método se ejecute. Si la
    *   expresión no se cumple (por ejemplo, el usuario no tiene el rol o permiso requerido), se produce una
    *   excepción de autorización (generalmente un AccessDeniedException) y el método no se ejecuta.
    * 
    * Lenguaje SpEL
    * Expresiones en SpEL:
    * La anotación utiliza el SpEL para definir condiciones de acceso. Por ejemplo:
    * @PreAuthorize("hasRole('ADMIN')"): Solo permite el acceso a usuarios con el rol ADMIN.
    * @PreAuthorize("hasAuthority('SCOPE_READ')"): Permite el acceso a usuarios con una autoridad específica, en este caso SCOPE_READ.
    * Se pueden crear expresiones más complejas combinando condiciones, por ejemplo:
*/
@PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #id == authentication.principal.id)")
/*
    * En este ejemplo, se permite el acceso si el usuario es ADMIN o, si es USER, siempre que el
    * identificador (id) de la solicitud sea el mismo que el del usuario autenticado.
    * Configuración y Uso
    * Habilitar las Anotaciones de Pre/Post Autorización:
    * Para que @PreAuthorize funcione, es necesario habilitar la anotación a nivel de configuración en el
    * contexto de Spring. Esto se hace generalmente en la clase de configuración de seguridad con:
*/
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // * configuración de seguridad
}

/*
    * Esto indica que Spring debe procesar y evaluar las anotaciones de pre/post autorización.
    * Importación de la Anotación:
    * La anotación se importa de la siguiente manera:
*/

import org.springframework.security.access.prepost.PreAuthorize;

/*
    * Aplicación en Métodos o Clases:
    * Puedes aplicar @PreAuthorize en métodos de controladores, servicios, o cualquier bean gestionado por
    * Spring. Si se aplica en la clase, se considera para todos sus métodos, aunque es común aplicarla a
    * métodos concretos para tener mayor control.
    * Ejemplos Prácticos
    * Ejemplo Simple: Control basado en Rol
*/
@PreAuthorize("hasRole('ADMIN')")
public void borrarUsuario(Long id) {
    // *  lógica para borrar un usuario
}
/*
    * Solo los usuarios con el rol ADMIN podrán ejecutar el método borrarUsuario.
    * Ejemplo con Variables y Parámetros: Supongamos que tienes un método para actualizar los datos de
    * un usuario y quieres permitir esta acción solamente si el usuario autenticado es el mismo que el usuario
    * a actualizar o si tiene el rol ADMIN:
*/
@PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
public void actualizarUsuario(Long id, Usuario usuario) {
    // *  lógica para actualizar el usuario
}
/*
    * En este caso, la expresión comprueba si el usuario tiene el rol ADMIN o si el id pasado como
    * parámetro es igual al id del usuario autenticado.
*/
