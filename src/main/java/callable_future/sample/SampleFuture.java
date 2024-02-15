package callable_future.sample;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class SampleFuture {
      private static final int POOL_SIZE = 3;
      public static void main(String[] args) {
           log.info("Main start work");
           try(ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE)) {

                 Callable<String> callable = () -> {
                       log.info("{} start work", Thread.currentThread().getName());
                       toWait(1000);
                       return Thread.currentThread().getName();
                 };

                 List<Future<String>> futures = new ArrayList<>();

                 // put all callable in executorService and start their execution

                 for (int i = 0; i < POOL_SIZE; i++) {
                       Future<String> future = executorService.submit(callable);
                       futures.add(future);
                 }
                 log.info("Main continue work after run all futures");

                 for (Future<String> future : futures) {
                       log.info("Result from : {}", future.get());
                 }
           } catch (ExecutionException | InterruptedException executionException) {
                 log.error(executionException.getMessage());
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
