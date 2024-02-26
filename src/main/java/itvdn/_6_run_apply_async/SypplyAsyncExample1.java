package itvdn._6_run_apply_async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SypplyAsyncExample1 {
      public static void main(String[] args) {
            try(ExecutorService executorService = Executors.newSingleThreadExecutor()) {
                  System.out.println("Main thread start work : " + Thread.currentThread().getName());

                  CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(()-> {
                        toSleep(5000);
                        System.out.println("Task work in thread : " + Thread.currentThread().getName());
                        return "Result from voidCompletableFuture";
                  }, executorService);

                  System.out.println("Asynchronous task was run");

                  System.out.println("Main thread continue work : " + Thread.currentThread().getName());

                  stringCompletableFuture.thenAccept(System.out::println).join(); //wait finis completable future
            }
      }

      public static void toSleep(int duration) {
            try {
                  Thread.sleep(duration);
            } catch (InterruptedException e) {
                  throw new RuntimeException(e);
            }
      }
}
