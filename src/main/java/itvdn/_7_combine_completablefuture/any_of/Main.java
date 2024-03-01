package itvdn._7_combine_completablefuture.any_of;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
      public static void main(String[] args) throws ExecutionException, InterruptedException {
            long start = System.currentTimeMillis();
            CompletableFuture<String> future1 = CompletableFuture.supplyAsync( () -> {
                        try {
                              Thread.sleep(10000);
                        } catch (InterruptedException e) {
                              throw new RuntimeException(e);
                        }
                        return "Future 1 result";
                  }
            );
            CompletableFuture<String> future2 = CompletableFuture.supplyAsync( () -> {
                        try {
                              Thread.sleep(2000);
                        } catch (InterruptedException e) {
                              throw new RuntimeException(e);
                        }
                        return "Future 2 result";
                  }
            );
            CompletableFuture<String> future3 = CompletableFuture.supplyAsync( () -> {
                        try {
                              Thread.sleep(3000);
                        } catch (InterruptedException e) {
                              throw new RuntimeException(e);
                        }
                        return "Future 3 result";
                  }
            );

            CompletableFuture<Object> resultAnyFuture = CompletableFuture.anyOf(future1, future2, future3);
            System.out.println(resultAnyFuture.get());
            System.out.println("Time : " + (System.currentTimeMillis() - start));
      }
}
