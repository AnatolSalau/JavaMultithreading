package itvdn._3_executor_service_fork_join.executor_service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

public class ExecutorServiceExample {
      public static void main(String[] args) throws InterruptedException {

            //testFixedThreadPoolExecutor();
            testFixedThreadPoolExecutorFiber();

      }

      private static void testFixedThreadPoolExecutor() throws InterruptedException {
            AtomicLong countThreads = new AtomicLong();
            AtomicLong countTasks = new AtomicLong();
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1_000_000; i++) {
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
            while (countTasks.getPlain() < 1_000_000) {
                  Thread.sleep(1);
            }
            System.out.println("Count tasks : " + countTasks.get());
            long end = System.currentTimeMillis();
            System.out.println("Test fixed thread pool executor time : " + (end - start));
            executorService.shutdown();
      }

      private static void testFixedThreadPoolExecutorFiber() throws InterruptedException {
            AtomicLong countTasks = new AtomicLong();
            ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1_000_000; i++) {
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
                        //System.out.println(Thread.currentThread().getName() + ", task :" + taskNumber + ", " + countTasks);
                  });
            }
            while (countTasks.getPlain() < 1_000_000) {
                  Thread.sleep(1);
            }
            System.out.println(countTasks.get());
            long end = System.currentTimeMillis();
            System.out.println("Test new virtual thread per task executor time : " + (end - start));
            executorService.shutdown();
      }
}
