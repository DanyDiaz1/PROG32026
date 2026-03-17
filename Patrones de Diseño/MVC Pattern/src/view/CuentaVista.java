package view;

import java.util.Scanner;

public class CuentaVista {

    Scanner scanner = new Scanner(System.in);

    public int mostrarMenu(){

        System.out.println("\n--- MENU CUENTA ---");
        System.out.println("1. Ver saldo");
        System.out.println("2. Depositar");
        System.out.println("3. Retirar");
        System.out.println("4. Salir");

        System.out.print("Opcion: ");
        return scanner.nextInt();
    }

    public double pedirMonto(){

        System.out.print("Ingrese monto: ");
        return scanner.nextDouble();
    }

    public void mostrarSaldo(double saldo){
        System.out.println("Saldo actual: $" + saldo);
    }

    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }
}