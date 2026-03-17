import interfaces.Enemigo;

public class Juego {

    public static void main(String[] args) {

        Enemigo enemigo1 = EnemigoFactory.crearEnemigo("A");
        enemigo1.atacar();
        enemigo1.moverse();

        System.out.println();

        Enemigo enemigo2 = EnemigoFactory.crearEnemigo("C");
        enemigo2.atacar();
        enemigo2.moverse();

    }

}
