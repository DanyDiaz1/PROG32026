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
            System.out.println("Se esta creando la instancia por primera vez");
            instancia = new Configuracion();
        }else {
            System.out.println("La instancia ya se creo, se va a devolver esa conexion");
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
