import controller.CuentaControlador;
import model.entity.Cuenta;
import model.service.CuentaService;
import view.CuentaVista;

public class Main {

    public static void main(String[] args) {

        Cuenta cuenta = new Cuenta(1000);

        CuentaVista vista = new CuentaVista();

        CuentaService service = new CuentaService();

        CuentaControlador controlador =
                new CuentaControlador(cuenta, vista, service);

        controlador.iniciar();
    }
}