package itvdn._10_producer_consumer;

import java.util.concurrent.*;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class Runner {
      final LinkedBlockingQueue<String> queue;
      final AtomicBoolean stop;

      public Runner() {
            this.queue = new LinkedBlockingQueue<>();
            this.stop = new AtomicBoolean(false);
      }

      public void start() {
            for (int i = 0; i < 3; i++) {
                  Producer producer = new Producer(queue, stop, "Producer" + i);
                  Consumer consumer = new Consumer(queue, stop, "Consumer" + i);
                  producer.produce();
                  consumer.consume();
            }

            Runnable runnable = () -> stop.set(true);
            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.schedule(runnable, 10, TimeUnit.SECONDS);
            scheduledExecutorService.shutdown();
      }

      public static void main(String[] args) {
            Runner runner = new Runner();
            runner.start();
      }
}
