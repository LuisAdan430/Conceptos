/*
    * La anotación @RequestParam en Spring se usa para vincular parámetros de una solicitud HTTP (por ejemplo,
    * de un formulario o de la URL) a parámetros de un método en un controlador. Es comúnmente usada con
    * solicitudes GET y POST.
    * Sintaxis básica
*/
@GetMapping("/saludo")
public String saludar(@RequestParam String nombre) {
    return "Hola, " + nombre;
}
// * Si haces una petición como:
GET /saludo?nombre=Juan
/* 
    * Spring vinculará el valor Juan al parámetro nombre.
    * Detalles de la anotación @RequestParam
    * Atributo                  Descripción
    * value o name              El nombre del parámetro en la solicitud (ambos son equivalentes).
    * required                  Si el parámetro es obligatorio (true por defecto).
    * defaultValue              Valor por defecto si no se proporciona el parámetro (entonces no es requerido).
    
    
    * Ejemplos:
    * Parámetro obligatorio:
*/
@GetMapping("/buscar")
public String buscar(@RequestParam String query) {
    return "Buscando: " + query;
}
// * Requiere que se envíe ?query=algo en la URL.
// * Parámetro opcional con valor por defecto:

@GetMapping("/pagina")
public String pagina(@RequestParam(defaultValue = "1") int numero) {
    return "Página número: " + numero;
}

// * Si no se envía numero, se usará 1.
// * Parámetro no requerido:

@GetMapping("/filtro")
public String filtro(@RequestParam(required = false) String categoria) {
    if (categoria != null) {
        return "Filtrando por: " + categoria;
    }
    return "Mostrando todos los resultados";
}

// * Múltiples valores (listas):
@GetMapping("/productos")
public String productos(@RequestParam List<String> ids) {
    return "IDs recibidos: " + ids;
}

// * Petición: /productos?ids=1&ids=2&ids=3