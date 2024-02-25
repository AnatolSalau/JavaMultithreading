package itvdn._5_completable_future.completeable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
      public static void main(String[] args) throws ExecutionException, InterruptedException {
            //stop main thread and wait when complete
            //CompletableFuture<String> completableFuture = new CompletableFuture<>();

            //CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("Result");

            //CompletableFuture<String> completableFuture = CompletableFuture.failedFuture(new RuntimeException());

            CompletionStage<String> completionStage = CompletableFuture.completedStage("Result from completion stage");
            CompletableFuture<String> completableFuture = completionStage.toCompletableFuture();

            System.out.println("Completable future is " + completableFuture.isDone());
            System.out.println("Completable future get " + completableFuture.get());
      }
}
