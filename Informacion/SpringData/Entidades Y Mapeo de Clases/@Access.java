/*
    * La anotación @Access en Java se utiliza en el contexto de JPA (Java Persistence API) para indicar cómo se
    * debe acceder a los campos persistentes de una entidad: si mediante los campos (field) o mediante los
    * métodos getter/setter (property).
    * ¿Dónde se usa @Access?
    * Principalmente sobre:
    * Clases de entidad (@Entity)
    * Propiedades o campos individuales
    * Sintaxis
*/
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;

@Access(AccessType.FIELD) // * o AccessType.PROPERTY

/*
    * Tipos de acceso (AccessType)
    * Valor                     Descripción
    * AccessType.FIELD          JPA accede directamente a los campos. No requiere métodos getter/setter.
    * AccessType.PROPERTY       JPA accede a través de los getters/setters. Los annotations como @Id deben ir en los métodos. 
    * Ejemplo de uso            
*/
@Entity
@Access(AccessType.FIELD) // *  JPA accederá directamente a los campos
public class Empleado {

    @Id
    private Long id;

    private String nombre;

    @Access(AccessType.PROPERTY) //  * Excepción: este campo se accede vía métodos
    private String salario;

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }
}

/*
    * En este ejemplo, toda la clase usa acceso por campo, excepto la propiedad salario, que explícitamente usa acceso por métodos.
    * ¿Cuándo usarlo?
    * Si estás combinando bibliotecas como JPA con otras como JAXB, y necesitas diferentes estilos de acceso.
    * Cuando quieras tener control detallado sobre cómo se persisten ciertos atributos.


*/