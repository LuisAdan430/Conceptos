
/*
 * Los JSON Web Tokens (JWT) están conformados por tres partes principales, separadas por puntos (`.`):
        ^ xxxxx.yyyyy.zzzzz
    * Header (Encabezado)
        * Es un objeto JSON que describe el tipo de token (`JWT`) y el algoritmo de firma (como `HS256`, `RS256`, etc.).
   * Ejemplo:
     ^ {
        ^ "alg": "HS256",
        ^ "typ": "JWT"
     ^ }
     
   * Se codifica en Base64Url para formar la primera parte (`xxxxx`).

   *  2. Payload (Carga útil)
    *  Contiene los claims (afirmaciones), que son declaraciones sobre el usuario y metadatos.
        *  Tipos de claims:
        *  Registrados : Estándar (ej. 'iss', 'exp', 'sub').
        *  Públicos : Definidos por la comunidad.
        *  Privados : Datos personalizados.
    ^ Ejemplo:
     ^ json
     ^ {
        ^ "sub": "1234567890",
        ^ "name": "John Doe",
        ^ "admin": true
     ^ }
    * Se codifica en Base64Url para formar la segunda parte (`yyyyy`).

    *  3. Signature (Firma)
    * Se genera firmando el header + payload (codificados) con un algoritmo (ej. `HMAC-SHA256`) y una clave secreta (`secret`).
    * Fórmula:
     
     * HMACSHA256(
       * mbase64UrlEncode(header) + "." + base64UrlEncode(payload),
       * secret
     * )
    
   * La firma se codifica en Base64Url para formar la tercera parte (`zzzzz`).

^ Ejemplo de JWT completo:
^ eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
^ ¿Para qué sirve cada parte?
^ Header : Indica cómo validar el token.
^ Payload : Contiene la información útil (ej. ID de usuario, roles).
^ Signature : Garantiza que el token no ha sido alterado.

    * Los JWT se usan comúnmente en autenticación (como tokens de acceso) y intercambio de datos seguro.
*/