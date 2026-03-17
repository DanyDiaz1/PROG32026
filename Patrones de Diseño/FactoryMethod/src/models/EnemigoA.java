package models;

import interfaces.Enemigo;

public class EnemigoA implements Enemigo {

    @Override
    public void atacar() {
        System.out.println("Enemigo A ataca con espada");
    }

    @Override
    public void moverse() {
        System.out.println("Enemigo A camina lentamente");
    }

}
