package itvdn._10_producer_consumer;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Producer {
      private final LinkedBlockingQueue<String> queue;
      private final AtomicBoolean stop;
      private final String producerName;
      private final ExecutorService executorService = Executors.newSingleThreadExecutor();

      public Producer(LinkedBlockingQueue<String> queue, AtomicBoolean stop, String producerName) {
            this.queue = queue;
            this.stop = stop;
            this.producerName = producerName;
      }

      public void produce() {
            final Runnable runnable = () -> {
                 while (!stop.get()) {
                       queue.add("Producer : " + producerName +  " at  " +  new Date());
                       try {
                             Thread.sleep(500);
                       } catch (InterruptedException e) {
                             throw new RuntimeException(e);
                       }
                 }
                 executorService.shutdown();
            };
            executorService.submit(runnable);
      }
}
