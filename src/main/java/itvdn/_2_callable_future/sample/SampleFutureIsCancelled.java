package itvdn._2_callable_future.sample;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class SampleFutureIsCancelled {
      private static final int POOL_SIZE = 1;
      public static void main(String[] args) {
           log.info("Main start work");
           try(ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE)) {

                 Callable<String> callable = () -> {
                       log.info("{} start work", Thread.currentThread().getName());
                       toWait(5000);
                       log.info("{} finis work", Thread.currentThread().getName());
                       return Thread.currentThread().getName();
                 };

                 Future<String> future = executorService.submit(callable);
                 log.info("Main continue work after run all futures");
                 toWait(1000);
                 if (!future.isDone()) {
                       future.cancel(true);
                 }
                 if (future.isCancelled()) {
                       log.info("Async task was cancelled");
                 } else {
                       log.info("Async task result : " + future.get());
                 }
                 log.info("Main end");
           } catch (ExecutionException | InterruptedException e) {
                 throw new RuntimeException(e);
           }
      }


      private static void toWait(int milliseconds) {
            try {
                  Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                  e.printStackTrace();
            }
      }
}
