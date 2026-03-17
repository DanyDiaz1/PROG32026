package model.service;

import model.entity.Cuenta;

public class CuentaService {

    public boolean depositar(Cuenta cuenta, double monto){

        if(monto <= 0){
            return false;
        }

        cuenta.setSaldo(cuenta.getSaldo() + monto);
        return true;
    }

    public boolean retirar(Cuenta cuenta, double monto){

        if(monto <= 0){
            return false;
        }

        if(monto > cuenta.getSaldo()){
            return false;
        }

        cuenta.setSaldo(cuenta.getSaldo() - monto);
        return true;
    }
}
