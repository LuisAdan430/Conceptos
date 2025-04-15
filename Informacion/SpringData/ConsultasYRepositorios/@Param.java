/*
    * La anotación @Param en Spring se utiliza principalmente en el contexto de Spring Data JPA, especialmente
    * dentro de interfaces de repositorios (Repository o JpaRepository). Sirve para vincular los parámetros de
    * un método con los parámetros nombrados dentro de una consulta JPQL o SQL definida mediante @Query.
    * Uso básico de @Param
*/
@Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
List<Usuario> buscarPorNombre(@Param("nombre") String nombre);
/*
    * En este ejemplo:
    * :nombre es un parámetro nombrado dentro de la consulta JPQL.
    * @Param("nombre") vincula el parámetro del método al parámetro nombrado.
    * ¿Cuándo se necesita?
    * Cuando defines consultas personalizadas con @Query.
    * Es obligatorio si usas parámetros nombrados (:param) en la consulta.
    * No es necesario en métodos con consultas derivadas (por convención de nombres), como findByNombre.
    * Ejemplo con múltiples parámetros
*/
@Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.edad > :edad")
List<Usuario> buscarPorNombreYEdad(@Param("nombre") String nombre, @Param("edad") int edad);

/*
    * Buenas prácticas
    * El nombre en @Param("...") debe coincidir exactamente con el usado en la consulta JPQL.
    * Si usas una consulta nativa (nativeQuery = true), también puedes usar @Param.
*/
@Query(value = "SELECT * FROM usuarios WHERE correo = :correo", nativeQuery = true)
Usuario buscarPorCorreo(@Param("correo") String correo);
