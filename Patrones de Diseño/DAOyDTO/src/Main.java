import dto.UsuarioDTO;
import services.UsuarioService;

public class Main {

    public static void main(String[] args) {

        UsuarioService service = new UsuarioService();

        UsuarioDTO usuario = service.obtenerUsuarioPublico(1);

        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("Saldo: $" + usuario.getSaldo());
    }
}
