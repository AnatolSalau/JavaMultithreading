package itvdn._7_combine_completablefuture.combine_sample_1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
      public static void main(String[] args) throws ExecutionException, InterruptedException {
            long start = System.currentTimeMillis();
            System.out.println("Weight calculating...");
            CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
                  try {
                        Thread.sleep(1000);
                  } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                  }
                  return 65.0;
            });
            System.out.println("Height calculating...");
            CompletableFuture<Double> heightInSmFuture = CompletableFuture.supplyAsync(() -> {
                  try {
                        Thread.sleep(1000);
                  } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                  }
                  return 177.0;
            });
            System.out.println("Index body calculating...");
            CompletableFuture<Double> indexBodyFuture = weightInKgFuture
                  .thenCombine(heightInSmFuture, (weightInKg, heightInSm) -> {
                        Double heightInMeter = heightInSm / 100;
                        return weightInKg / (heightInMeter * heightInMeter);
                  });
            System.out.println("Index body future : " + indexBodyFuture.get());
            System.out.println("Time : " + (start - System.currentTimeMillis()));
      }

}
