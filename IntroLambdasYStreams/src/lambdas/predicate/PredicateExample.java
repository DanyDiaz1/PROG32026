package predicate;

import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {
        /*
         * ✅ Predicate
         * 💡 Recibe un valor y devuelve un booleano (true o false).
         */

        Predicate<Integer> predicate = (num) -> {return num>5;};
        System.out.println(predicate.test(3));


    }
}
