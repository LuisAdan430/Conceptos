/*
    * @PutMapping
    * @PutMapping es parte del framework Spring (Spring MVC) y se utiliza para manejar
    * solicitudes HTTP PUT en controladores REST. Sirve para actualizar recursos existentes en el servidor.
    * Sintaxis básica:
*/
@PutMapping("/ruta")
public ResponseEntity<TipoDeRespuesta> metodo(@RequestBody TipoDeEntrada entrada) {
    // *  lógica de actualización
}

/*
    * ¿Qué hace exactamente?
    * Mapea una solicitud HTTP PUT a un método en un controlador.
    * Se usa típicamente para reemplazar o actualizar un recurso completo.
    * Ejemplo práctico:
    * Supón que tienes una API para manejar usuarios, y quieres actualizar los datos de un usuario:
*/

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody Usuario usuarioActualizado) {

        // * Aquí iría la lógica para buscar al usuario por ID,
        // * actualizarlo con los nuevos datos y devolver el resultado.

        Usuario usuario = servicioUsuario.actualizar(id, usuarioActualizado);
        return ResponseEntity.ok(usuario);
    }
}
/*
    *  Características clave:
    *  Usa @RequestBody para obtener los datos enviados en el cuerpo de la petición.
    *  Usa @PathVariable si necesitas capturar partes dinámicas de la URL (como un ID).
    *  Puedes devolver un ResponseEntity para personalizar la respuesta (estado HTTP, cabeceras, etc).
    *  PUT vs. PATCH
    *  @PutMapping: actualiza el recurso completo.
    *  @PatchMapping: actualiza solo parte del recurso (actualización parcial).
*/