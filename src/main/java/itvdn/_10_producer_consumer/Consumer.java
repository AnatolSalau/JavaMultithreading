package itvdn._10_producer_consumer;

import ch.qos.logback.core.joran.conditional.ThenAction;

import java.util.concurrent.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer {
      private final LinkedBlockingQueue<String> queue;
      private final AtomicBoolean stop;
      private final String consumerName;
      private final ExecutorService executorService = Executors.newSingleThreadExecutor();

      public Consumer(LinkedBlockingQueue<String> queue, AtomicBoolean stop, String consumerName) {
            this.queue = queue;
            this.stop = stop;
            this.consumerName = consumerName;
      }

      public void consume() {
            final Runnable runnable = () -> {
                  while (!stop.get()) {
                        try {
                              Thread.sleep(500);
                              System.out.println("Consumer : " + consumerName + " : " + queue.poll(1000, TimeUnit.SECONDS));
                        } catch (InterruptedException e) {
                              throw new RuntimeException(e);
                        }
                  }
                  executorService.shutdown();
            };
            executorService.submit(runnable);
      }
}
