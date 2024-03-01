package itvdn._7_combine_completablefuture.compose_sample;

import java.util.Date;

public class UserService {
      public static User getUserFromRemoteService() {
            return new User();
      }

      public static Integer getDiscount(User user) {
            // 86400000ms - 1 day ago
            if (user.registrationDate .before(new Date(System.currentTimeMillis() - 86400000))) {
                  if (user.countOfOrders > 5) {
                        if (user.sumOfOrders > 1000) {
                              if (user.referralFriends > 10) {
                                    return 10;
                              }
                              return 5;
                        }
                        return 3;
                  }
                  return 1;
            }
            return 0;
      }
}
