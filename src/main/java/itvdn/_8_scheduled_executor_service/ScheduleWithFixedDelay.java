package itvdn._8_scheduled_executor_service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Slf4j
public class ScheduleWithFixedDelay {
      public static void main(String[] args) {
            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                  @Override
                  public void run() {
                        log.info("scheduleAtFixedRate" + new Date());
                        try {
                              Thread.sleep(1000);
                        } catch (InterruptedException e) {
                              throw new RuntimeException(e);
                        }
                  }
            }, 0, 3, TimeUnit.SECONDS);

            scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
                  @Override
                  public void run() {
                        log.info("scheduleWithFixedDelay" + new Date());
                        try {
                              Thread.sleep(1000);
                        } catch (InterruptedException e) {
                              throw new RuntimeException(e);
                        }
                  }
            }, 0, 3, TimeUnit.SECONDS);

            scheduledExecutorService.awaitTermination(1, TimeUnit.HOURS);
            scheduledExecutorService.shutdown();
      }
}
