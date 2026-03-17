package models;

import interfaces.Enemigo;

public class EnemigoB implements Enemigo {

    @Override
    public void atacar() {
        System.out.println("Enemigo B dispara flechas");
    }

    @Override
    public void moverse() {
        System.out.println("Enemigo B corre rápido");
    }

}
