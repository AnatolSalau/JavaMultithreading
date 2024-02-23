package itvdn._3_executor_service_fork_join.executor_service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

public class ExecutorServiceShutdownNowExample {
      public static void main(String[] args) throws InterruptedException {
            AtomicLong countTasks = new AtomicLong();
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                  int taskNumber = i;
                  executorService.submit(() -> {
                        Function<Long, List<Long>> findFibonacci = (Long maxNumber) -> {
                              long previousNumber = 0, nextNumber = 1;

                              List<Long> result = new LinkedList<>();

                              long j = 1;
                              while (j <= maxNumber) {
                                    result.add(previousNumber);
                                    long sum = previousNumber + nextNumber;
                                    previousNumber = nextNumber;
                                    nextNumber = sum;
                                    j++;
                              }
                              return result;
                        };
                        List<Long> fibonacciList = findFibonacci.apply(100l);
                        countTasks.getAndIncrement();
                        //System.out.println(Thread.currentThread().getName() + ", task :" + taskNumber + ", " + count);
                  });
            }
                  Thread.sleep(3);
            List<Runnable> isNotCompletedRunnables = executorService.shutdownNow();
            System.out.println("Count completed tasks : " + countTasks.get());
            System.out.println("Count are not completed runnables : " + isNotCompletedRunnables.size());
            long end = System.currentTimeMillis();
            System.out.println("Test fixed thread pool executor time : " + (end - start));
            executorService.shutdown();

      }


}
