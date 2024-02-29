package itvdn._7_combine_completablefuture.combine;

import java.util.Date;
import java.util.Random;

public class User {
      Date registrationDate;
      int countOfOrders;
      double sumOfOrders;
      int referralFriends;

      public User() {
            Random random = new Random();
            registrationDate = new Date(System.currentTimeMillis() - random.nextInt(1_000_000_000));
            countOfOrders = random.nextInt(50);
            sumOfOrders = random.nextDouble() * random.nextInt(1000);
            referralFriends = random.nextInt(30);
      }
}
