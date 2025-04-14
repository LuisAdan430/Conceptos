/*
    * La anotación @Lock en Java es parte de Java Persistence API (JPA) y se utiliza para
    * controlar la concurrencia en el acceso a entidades. Esta anotación permite especificar el
    * tipo de bloqueo (lock) que debe aplicarse cuando se accede a una entidad en una
    * transacción.
    * Ubicación
    * Se usa en métodos de repositorios o servicios que trabajan con entidades gestionadas
    * por JPA (como Hibernate).
    * Sintaxis
*/
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT e FROM Entidad e WHERE e.id = :id")
Entidad findByIdConLock(@Param("id") Long id);
/*
    * Tipos de Lock disponibles (LockModeType)
    * Modo              	                        Descripción
    * LockModeType.OPTIMISTIC                       Usa versiones para manejar concurrencia. Ideal para aplicaciones con pocas colisiones.
    * LockModeType.OPTIMISTIC_FORCE_INCREMENT       Similar a OPTIMISTIC, pero fuerza el incremento de la versión.
    * LockModeType.PESSIMISTIC_READ                 Bloquea la entidad para lectura. Otros pueden leer, pero no escribir.
    * LockModeType.PESSIMISTIC_WRITE                Bloquea la entidad completamente. Nadie más puede leer o escribir hasta que se libere.
    * LockModeType.PESSIMISTIC_FORCE_INCREMENT      Igual que PESSIMISTIC_WRITE, pero también incrementa la versión.
    * LockModeType.NONE                             No se aplica ningún lock.
    * Ejemplo de uso práctico
*/
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Producto p WHERE p.id = :id")
    Producto findByIdForUpdate(@Param("id") Long id);
}
/*
    * Este método busca un producto y aplica un bloqueo de escritura para que ninguna otra
    * transacción pueda modificar esa entidad hasta que termine la actual.
    * ¿Cuándo usar cada uno?
    * Usa OPTIMISTIC si esperas pocas colisiones y quieres evitar bloqueos duros.
    * Usa PESSIMISTIC_WRITE si necesitas consistencia fuerte y no puedes permitir que
    * otros procesos modifiquen el dato mientras lo usas.
*/