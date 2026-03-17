package models;

import interfaces.Enemigo;

public class EnemigoC implements Enemigo {

    @Override
    public void atacar() {
        System.out.println("Enemigo C lanza magia");
    }

    @Override
    public void moverse() {
        System.out.println("Enemigo C se teletransporta");
    }

}