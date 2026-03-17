package dto;

public class UsuarioDTO {

    private String nombre;
    private double saldo;

    public UsuarioDTO(String nombre, double saldo) {
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSaldo() {
        return saldo;
    }
}