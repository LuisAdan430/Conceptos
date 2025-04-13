/*
    * @RolesAllowed("ROLE_USER")
    * Uso: Similar a @Secured, pero compatible con JSR-250.
    * Pertenece a: 
*/
javax.annotation.security.RolesAllowed


/*
    * Claro, la anotación @RolesAllowed se utiliza en aplicaciones Java para restringir 
    * el acceso a métodos o clases según los roles de los usuarios autenticados.
    * Es parte del estándar de seguridad JSR 250 y funciona tanto en entornos Java EE /
    * Jakarta EE como en proyectos Spring
    * (aunque en Spring se recomienda usar otras anotaciones como @PreAuthorize).

    * ¿Qué hace @RolesAllowed?
    * Restringe el acceso a los métodos o clases a los usuarios que tengan alguno de los roles especificados.
*/
import jakarta.annotation.security.RolesAllowed;
@RolesAllowed("ADMIN")
public void deleteUser(Long id) {
    //* Solo accesible para usuarios con rol "ADMIN"
}
/*
    *¿Dónde se puede usar?
    *    A nivel de clase: Afecta todos los métodos.
    *    A nivel de método: Afecta solo ese método.
    *    Ejemplo en una aplicación Jakarta EE
*/

import jakarta.ejb.Stateless;
import jakarta.annotation.security.RolesAllowed;

@Stateless
public class ProductService {

    @RolesAllowed({"ADMIN", "MANAGER"})
    public void createProduct(Product p) {
        // * Solo ADMIN o MANAGER pueden crear productos
    }

    @RolesAllowed("USER")
    public List<Product> listProducts() {
        // * Cualquier usuario con rol USER puede ver productos
        return ...
    }
}

/*
    * ¿Cómo funciona con Spring?
    * Spring no la soporta de forma predeterminada. Si quieres usar @RolesAllowed en Spring, 
    * debes habilitar el soporte a través de @EnableMethodSecurity(jsr250Enabled = true):

*/

@Configuration
@EnableMethodSecurity(jsr250Enabled = true)
public class MethodSecurityConfig {
}
 // * Y luego puedes usarlo como:


@RolesAllowed("ROLE_ADMIN")
public void deleteAll() {
    // * Lógica protegida
}

// * OJO: En Spring los roles deben comenzar con ROLE_ si estás usando @RolesAllowed.
// * Alternativas comunes en Spring
@Secured("ROLE_ADMIN")

@PreAuthorize("hasRole('ADMIN')")

// * Estas dos son más comunes en proyectos Spring modernos.
