package itvdn._8_scheduled_executor_service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Main {
      private static AtomicInteger count = new AtomicInteger(0);
      public static void main(String[] args) {
            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

            Runnable task1 = () -> {
                  int i = count.incrementAndGet();
                  log.info("Running task1 - count : " + i);
            };

            ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(task1, 0, 1, TimeUnit.SECONDS);



            scheduledExecutorService.shutdown();
      }
}
