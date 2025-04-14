/*
    * La anotación @Modifying en Spring Data JPA se utiliza junto con @Query para ejecutar
    * operaciones de modificación en la base de datos, como INSERT, UPDATE o DELETE.
    * ¿Cuándo usar @Modifying?
    * Spring Data JPA asume que todas las consultas definidas con @Query son consultas de
    * solo lectura (tipo SELECT). Si deseas realizar una modificación, debes anotar el método
    * con @Modifying para indicarle a Spring que la consulta cambiará el estado de la base de
    * datos.
    * Ejemplo básico: UPDATE
*/
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Modifying
    @Query("UPDATE Usuario u SET u.activo = false WHERE u.fechaUltimoAcceso < :fechaLimite")
    int desactivarUsuariosInactivos(@Param("fechaLimite") LocalDate fechaLimite);
}
/*
    * Este método marcará como inactivos a los usuarios que no han accedido desde fechaLimite.
    * Consideraciones:
    * Debe estar dentro de una transacción: Necesitas usar @Transactional en el método o a nivel de clase/repo.
*/

@Transactional
@Modifying
@Query("DELETE FROM Usuario u WHERE u.estado = 'INACTIVO'")
void eliminarUsuariosInactivos();

/*
    * Retorno: Puedes retornar void o int. El int indica cuántas filas fueron afectadas.
    * No se puede usar para SELECT: Solo para INSERT, UPDATE y DELETE.
    * Opcional: clearAutomatically
    * Puedes usar @Modifying(clearAutomatically = true) para limpiar el contexto de
    * persistencia (evita errores de sincronización entre la base de datos y el EntityManager).
*/
@Modifying(clearAutomatically = true)
@Query("UPDATE Producto p SET p.stock = p.stock - :cantidad WHERE p.id = :id")
void descontarStock(@Param("id") Long id, @Param("cantidad") int cantidad);
