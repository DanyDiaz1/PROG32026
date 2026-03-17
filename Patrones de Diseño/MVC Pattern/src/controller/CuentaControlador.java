package controller;

import model.entity.Cuenta;
import model.service.CuentaService;
import view.CuentaVista;

public class CuentaControlador {

    private Cuenta cuenta;
    private CuentaVista vista;
    private CuentaService service;

    public CuentaControlador(Cuenta cuenta, CuentaVista vista, CuentaService service) {
        this.cuenta = cuenta;
        this.vista = vista;
        this.service = service;
    }

    public void iniciar(){

        int opcion;

        do{

            opcion = vista.mostrarMenu();

            switch(opcion){

                case 1:
                    vista.mostrarSaldo(cuenta.getSaldo());
                    break;

                case 2:
                    double deposito = vista.pedirMonto();

                    if(service.depositar(cuenta, deposito)){
                        vista.mostrarMensaje("Deposito realizado");
                    }else{
                        vista.mostrarMensaje("Monto invalido");
                    }

                    break;

                case 3:
                    double retiro = vista.pedirMonto();

                    if(service.retirar(cuenta, retiro)){
                        vista.mostrarMensaje("Retiro realizado");
                    }else{
                        vista.mostrarMensaje("No se pudo retirar");
                    }

                    break;

            }

        }while(opcion != 4);
    }
}
