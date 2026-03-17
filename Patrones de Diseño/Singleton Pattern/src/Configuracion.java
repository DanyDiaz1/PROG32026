public class Configuracion {

    // instancia única
    private static Configuracion instancia;

    private String idioma;
    private String baseDatos;

    // constructor privado
    private Configuracion() {
        idioma = "es";
        baseDatos = "PostgreSQL";
    }

    // metodo de acceso global
    public static Configuracion getInstancia() {

        if (instancia == null) {
            instancia = new Configuracion();
        }

        return instancia;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getBaseDatos() {
        return baseDatos;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
