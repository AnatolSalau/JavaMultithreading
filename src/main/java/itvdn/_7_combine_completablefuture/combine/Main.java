package itvdn._7_combine_completablefuture.combine;

import java.util.concurrent.CompletableFuture;

public class Main {
      public static void main(String[] args) {
            // 7:24
      }
      public static CompletableFuture<User> getUserFromRemote() {
            return CompletableFuture.supplyAsync(UserService::getUserFromRemoteService);
      }

      public static CompletableFuture<Integer> getDiscountFromUser(User user) {
            return CompletableFuture.supplyAsync(() -> UserService.getDiscount(user));
      }
}
