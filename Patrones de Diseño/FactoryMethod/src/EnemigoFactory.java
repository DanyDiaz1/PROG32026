import interfaces.Enemigo;
import models.EnemigoA;
import models.EnemigoB;
import models.EnemigoC;

public class EnemigoFactory {

    public static Enemigo crearEnemigo(String tipo) {

        switch (tipo.toUpperCase()) {

            case "A":
                return new EnemigoA();

            case "B":
                return new EnemigoB();

            case "C":
                return new EnemigoC();

            default:
                throw new IllegalArgumentException("Tipo de enemigo no válido");
        }
    }

}
