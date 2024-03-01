package itvdn._7_combine_completablefuture.compose_sample;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
      public static void main(String[] args) throws ExecutionException, InterruptedException {
            CompletableFuture<User> remoteUser = getUserFromRemote();
            System.out.println("Remote user : " + remoteUser.get().toString());
            CompletableFuture<Integer> discount = remoteUser.thenCompose(user -> getDiscountFromUser(user));
            System.out.println("Discount : " + discount.get());
      }
      public static CompletableFuture<User> getUserFromRemote() {
            return CompletableFuture.supplyAsync(UserService::getUserFromRemoteService);
      }

      public static CompletableFuture<Integer> getDiscountFromUser(User user) {
            return CompletableFuture.supplyAsync(() -> UserService.getDiscount(user));
      }
}
