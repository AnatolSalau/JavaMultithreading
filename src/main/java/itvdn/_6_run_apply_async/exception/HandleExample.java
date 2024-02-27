package itvdn._6_run_apply_async.exception;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HandleExample {
      public static void main(String[] args) throws ExecutionException, InterruptedException {
            CompletableFuture<Boolean> completableFuture = CompletableFuture.supplyAsync(() -> {
                  int[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};

                  for (int i = 0; i < /*array.length*/ 100; i++) { // java.lang.ArrayIndexOutOfBoundsException: Index 19 out of bounds for length 19
                        System.out.println(array[i]);
                  }
                  return true;
            }).handle((result, throwable) -> {
                  if (throwable != null) {
                        System.out.println(throwable.getMessage());
                        System.out.println("Check your code");
                        return false;
                  }
                  return result;
            });

            System.out.println("Result : " + completableFuture.get());
      }
}
