package itvdn._5_completable_future.function_interfaces;

import java.util.Random;
import java.util.function.*;

public class FunctionInterfacesExample {
      public static void main(String[] args) {
            //Consumer doesn't return anything only do something;
            Consumer<String> helloConsumer = str -> System.out.println("Hello " + str);
            helloConsumer.accept(" people");

            // Function change one to another
            Function<Integer, String> getStringWithNumber = integer -> "String number " + integer;
            System.out.println(getStringWithNumber.apply(1));

            // Supplier create new value
            Supplier<String> randomSupplier = () -> {
                  Random random = new Random();
                  return "my random value : " + random.nextInt();
            };
            System.out.println(randomSupplier.get());

            //Bi function consume Supplier and consumer and return value
            BiFunction<Consumer<String>, Supplier<String>, Boolean> biFunction = ((stringConsumer, stringSupplier) -> {
                  String supplierStr = stringSupplier.get();
                  stringConsumer.accept(supplierStr);
                  return true;
            });
            Boolean biFunctionIsDone = biFunction.apply(helloConsumer, randomSupplier);
            System.out.println("Bi function is done : " + biFunctionIsDone);

            //Bi consumer only consume supplier and consumer
            BiConsumer<Consumer<String>, Supplier<String>> biConsumer = ((stringConsumer, stringSupplier) -> {
                  System.out.println("BiConsumer start work");
                  String supplierStr = stringSupplier.get();
                  stringConsumer.accept(supplierStr);
                  System.out.println("BiConsumer finish work");
            });
            biConsumer.accept(helloConsumer, randomSupplier);
      }
}
