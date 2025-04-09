/*
    * La anotación @RequestHeader en Spring se utiliza para acceder a valores de las cabeceras HTTP
    * (headers) en una petición entrante dentro de un controlador (@RestController o @Controller).
    * ¿Para qué sirve @RequestHeader?
    * Cuando un cliente (como Postman, un navegador, o una app frontend) envía una solicitud HTTP, puede
    * incluir información adicional en las cabeceras. @RequestHeader permite que tu controlador obtenga esos
    * valores directamente.

    * Ejemplo básico:
*/
@GetMapping("/saludo")
public String saludar(@RequestHeader("User-Agent") String userAgent) {
    return "Tu User-Agent es: " + userAgent;
}
/*
    * Si haces una petición a /saludo, esta función devolverá el valor del header User-Agent, que normalmente contiene información sobre el navegador o cliente.
    * Parámetros comunes:
    * Parámetro                 Descripción
    * value o name              El nombre del header HTTP.
    * required                  Si es obligatorio o no. Por defecto es true.
    * defaultValue              Valor por defecto si el header no está presente.
    * Ejemplo con opciones:
*/
@GetMapping("/auth")
public String authHeader(
    @RequestHeader(name = "Authorization", required = false, defaultValue = "N/A") String auth
) {
    return "Authorization header: " + auth;
}

/*
    *  Este método:
    *  Intenta leer el header Authorization.
    *  Si no viene en la solicitud, devuelve "N/A" como valor por defecto
    *  ¿Cuándo usar @RequestHeader?
    *  Para obtener tokens de autenticación (ej. Authorization).
    *  Para personalizar comportamiento según el cliente (User-Agent, Accept-Language).
    *  Para obtener cabeceras personalizadas que tú definas (ej. X-Custom-Header).
*/