/*
    * @WithMockUser
    * Uso: Para pruebas, simula un usuario autenticado.
    * Pertenece a:
*/
org.springframework.security.test.context.support.WithMockUser
/*
    * La anotación @WithMockUser es una utilidad que proporciona Spring Security para facilitar
    * las pruebas de seguridad en tests unitarios o tests de integración, especialmente cuando
    * se usan frameworks como JUnit junto con Spring Test.
    * ¿Qué hace @WithMockUser?
    * Simula un usuario autenticado en el contexto de seguridad de Spring para que puedas
    * probar métodos o endpoints protegidos sin tener que hacer un login real.
    * Uso típico
*/
@Test
@WithMockUser(username = "admin", roles = {"ADMIN"})
public void testMetodoConUsuarioAdmin() {
    // * aquí puedes probar métodos o endpoints como si estuvieras logueado como 'admin'
}
/*
    * Atributos principales 
    * Atributo              Descripción
    * username              Nombre de usuario simulado (por defecto es "user")
    * password              No tiene impacto en la autenticación simulada, pero puedes asignarle uno
    * roles                 Roles del usuario simulado (no debes incluir el prefijo ROLE_) 
    * authorities           Autoridades explícitas si quieres usar estas en lugar de roles
    * Nota: roles y authorities no deben usarse juntos, elige uno u otro.
    * Ejemplo con @WebMvcTest
*/
@WebMvcTest(MyController.class)
public class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "usuario", roles = {"USER"})
    public void testAccesoConUsuarioAutenticado() throws Exception {
        mockMvc.perform(get("/ruta-protegida"))
               .andExpect(status().isOk());
    }
}
/*
    * ¿Cuándo usarla?
    * Cuando pruebas controladores REST protegidos por Spring Security.
    * Cuando necesitas verificar la autorización sin hacer un login real.
    * Para evitar la complejidad de configurar un sistema de autenticación real durante los tests.
*/