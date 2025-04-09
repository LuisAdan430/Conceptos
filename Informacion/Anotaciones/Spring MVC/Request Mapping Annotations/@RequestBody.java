/*
    * La anotación @RequestBody en Spring (Java) se usa en controladores para vincular
    * automáticamente el cuerpo de una petición HTTP (normalmente JSON o XML) a un objeto Java.
    * ¿Dónde se usa?
    * Dentro de un controlador (@RestController) en métodos que manejan solicitudes HTTP, como @PostMapping, @PutMapping, etc.

    * ¿Qué hace exactamente?
    * Cuando llega una solicitud HTTP con un cuerpo (body), Spring lo convierte
    * automáticamente (deserializa) a un objeto Java que tú defines.
    
    * Ejemplo práctico:
    * Supongamos que tienes un objeto Java:
*/
public class Usuario {
    private String nombre;
    private int edad;

    // * Getters y Setters
}
/*
    * Y un controlador:
*/
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @PostMapping
    public String crearUsuario(@RequestBody Usuario usuario) {
        return "Usuario recibido: " + usuario.getNombre();
    }
}
/*
    * Si tú haces una petición POST así:
*/
POST /api/usuarios
Content-Type: application/json

{
  "nombre": "Ana",
  "edad": 30
}

/*
    * Spring convierte ese JSON en un objeto Usuario automáticamente gracias a @RequestBody.
    * Requiere:
    * Que tengas Jackson (o alguna librería de serialización) en tu proyecto.
    * Que los campos del JSON coincidan con los del objeto Java.
    * En resumen:
    * @RequestBody:
    * Lee el cuerpo de la petición.
    * Lo convierte en un objeto Java.
    * Lo inyecta como parámetro en tu método.
*/