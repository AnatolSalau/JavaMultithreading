package itvdn._8_scheduled_executor_service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.*;


public class Main {
      public static void main(String[] args) {
            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

            Runnable task2Runnable = () -> System.out.println("Running task2...");

            task1();
            scheduledExecutorService.schedule(task2Runnable, 5, TimeUnit.SECONDS);
            task3();

            scheduledExecutorService.shutdown();
      }

      public static void task1() {
            System.out.println("Running task1...");
      }

      public static void task3() {
            System.out.println("Running task3...");
      }
}
