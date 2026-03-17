package models;

public class UsuarioDAO {

    private int id;
    private String nombre;
    private String dni;
    private String password;
    private double saldo;

    public UsuarioDAO(int id, String nombre, String dni, String password, double saldo) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.password = password;
        this.saldo = saldo;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    public String getPassword() { return password; }
    public double getSaldo() { return saldo; }
}
