public class Sistema {

    public void mostrarConfiguracion() {

        Configuracion config = Configuracion.getInstancia();

        System.out.println("Idioma: " + config.getIdioma());
        System.out.println("Base de datos: " + config.getBaseDatos());
    }

}