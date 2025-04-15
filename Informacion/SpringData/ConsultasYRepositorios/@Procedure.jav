/*
    * La anotación @Procedure en Spring Data JPA se utiliza para invocar procedimientos almacenados (stored
    * procedures) definidos en la base de datos directamente desde un repositorio.
    * ¿Qué es un procedimiento almacenado?
    * Es una función en SQL que se guarda en la base de datos y que puede ser ejecutada con parámetros. Por ejemplo:
*/
CREATE PROCEDURE contar_empleados(IN depto_id INT, OUT total INT)
BEGIN
  SELECT COUNT(*) INTO total FROM empleados WHERE departamento_id = depto_id;
END;

/*
    * ¿Cómo usar @Procedure?
    * Supón que tienes una base de datos con un procedimiento almacenado llamado contar_empleados. Así lo usarías en Spring:
    * Define el repositorio
*/
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    @Procedure(procedureName = "contar_empleados")
    int contarEmpleados(@Param("depto_id") Integer deptoId);
}
/*
    * procedureName: es el nombre real del procedimiento en la base de datos.
    * @Param: enlaza el parámetro Java con el parámetro del procedimiento.
    * Cosas importantes
    * Si el procedimiento está mapeado como parte de una entidad, puedes usar:
*/
@Procedure(name = "Empleado.contarEmpleados")
int contarEmpleados(@Param("depto_id") Integer deptoId);
// * Y en la entidad:
@NamedStoredProcedureQuery(
    name = "Empleado.contarEmpleados",
    procedureName = "contar_empleados",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "depto_id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "total", type = Integer.class)
    }
)
@Entity
public class Empleado {
    // *  ...
}
/*
    * ¿Cuándo usar @Procedure?
    * Cuando tienes lógica compleja en SQL que prefieres mantener en la base de datos.
    * Cuando necesitas optimizar rendimiento o encapsular reglas de negocio específicas del DBMS.
    * Cuando trabajas con procedimientos ya existentes en sistemas legados.
*/