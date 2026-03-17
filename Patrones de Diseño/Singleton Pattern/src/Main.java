public class Main {

    public static void main(String[] args) {

        Sistema sistema1 = new Sistema();
        sistema1.mostrarConfiguracion();

        System.out.println();

        // cambiamos una configuración
        Configuracion config = Configuracion.getInstancia();
        config.setIdioma("en");

        Sistema sistema2 = new Sistema();
        sistema2.mostrarConfiguracion();


    }
}