/*
    ! Especialización de @Component.
    * Se usa para definir clases de lógica de negocio o servicios.
*/

@Service
public class ClienteService {
    public List<String> obtenerClientes() {
        return List.of("Cliente 1", "Cliente 2");
    }
}