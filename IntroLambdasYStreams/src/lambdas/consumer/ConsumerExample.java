package lambdas.consumer;

import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
        /*
         * 🛠️ Consumer
         * 💡 Recibe un valor y no retorna nada.
         */

        Consumer<String> consumer = (nombre)->nombre.toUpperCase();


        consumer.accept("Dany");

    }
}