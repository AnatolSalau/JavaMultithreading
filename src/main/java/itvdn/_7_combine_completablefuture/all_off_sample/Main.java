package itvdn._7_combine_completablefuture.all_off_sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
      public static void main(String[] args) throws ExecutionException, InterruptedException {
            long start = System.currentTimeMillis();
            List<Integer> list = new ArrayList<>();

            for (int i = 1; i < 50; i++) {
                  list.add(i);
            }

            List<CompletableFuture<Integer>> futureList = list.stream()
                  .map(el -> getSqr(el))
                  .toList();

            CompletableFuture<Void> allSqr = CompletableFuture.allOf(
                  futureList.toArray(new CompletableFuture[0])
            );

            CompletableFuture<List<Integer>> allValues = allSqr.thenApply( sqr -> {
                  return futureList.stream()
                        .map(CompletableFuture::join)
                        .toList();
            });
            List<Integer> result = allValues.get();

            long end = System.currentTimeMillis();
            System.out.println("Time : " + (end - start));
            result.forEach(System.out::println);
      }

      public static CompletableFuture<Integer> getSqr(Integer integer) {
            CompletableFuture<Integer> sqrCompletableFuture = CompletableFuture.supplyAsync(() -> {
                  try {
                        Thread.sleep(1000);
                  } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                  }
                  return integer * integer;
            });
            return sqrCompletableFuture;
      }
}
