package itvdn._7_combine_completablefuture.multiple_tasks_sample;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
      public static void main(String[] args) throws ExecutionException, InterruptedException {
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i <= 5; i++) {
                  list.add(i);
            }

            long start = System.currentTimeMillis();

            List<CompletableFuture<StoreDiscount>> storeDiscountFutureList = list.stream()
                  .map((e) -> getStoreDiscount(e))
                  .toList();

            CompletableFuture<Void> allDiscountFuture = CompletableFuture.allOf(
                  storeDiscountFutureList.toArray(new CompletableFuture[0])
            );

            CompletableFuture<List<StoreDiscount>> allStore = allDiscountFuture.thenApply(
                  sore -> {
                        return storeDiscountFutureList.stream()
                              .map(CompletableFuture::join)
                              .toList();
                  }
            );

            CompletableFuture<Integer> userDiscount = getUserFromRemote().thenCompose(
                  user -> getDiscountFromUser(user)
            );

            CompletableFuture<List<Integer>> resultFuture = userDiscount
                  .thenCombine(allStore,
                        (userDis, storeDisList) -> getListOfStoresWithUserDiscount(userDis, storeDisList));

            List<Integer> result = resultFuture.get();
            long end = System.currentTimeMillis();

            System.out.println("All  : " + allStore.get());
            System.out.println("User discount : " + userDiscount.get());
            System.out.println("Result : " + result);
            System.out.println("Time : " + (end - start));
      }

      static CompletableFuture<StoreDiscount> getStoreDiscount(Integer integer) {
            return CompletableFuture.supplyAsync(() -> {
                  Random random = new Random();
                  StoreDiscount storeDiscount = new StoreDiscount(integer, random.nextInt(10));
                  return storeDiscount;
            });
      }

      static CompletableFuture<User> getUserFromRemote() {
            return CompletableFuture.supplyAsync(UserService::getUserFromRemoteService);
      }
      static CompletableFuture<Integer> getDiscountFromUser(User user) {
            return CompletableFuture.supplyAsync(() -> UserService.getDiscount(user));
      }

      static List<Integer> getListOfStoresWithUserDiscount(Integer userDiscount, List<StoreDiscount> storeDiscounts) {
            List<Integer> list = storeDiscounts.stream()
                  .filter(storeDiscount -> storeDiscount.getSizeOfDiscount().equals(userDiscount))
                  .map(StoreDiscount::getNumberOfStore)
                  .toList();
            return list;
      }
}
