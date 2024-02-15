package itvdn._2_callable_future.sample;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class SampleFutureIsDone {
      private static final int POOL_SIZE = 1;
      public static void main(String[] args) {
           log.info("Main start work");
           try(ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE)) {

                 Callable<String> callable = () -> {
                       log.info("{} start work", Thread.currentThread().getName());
                       toWait(3000);
                       log.info("{} finis work", Thread.currentThread().getName());
                       return Thread.currentThread().getName();
                 };

                 Future<String> future = executorService.submit(callable);
                 log.info("Main continue work after run all futures");

                 while (!future.isDone()) {
                       log.info("Waiting, Future is executing...");
                       toWait(500);
                 }
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
