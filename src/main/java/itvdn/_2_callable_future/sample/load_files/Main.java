package itvdn._2_callable_future.sample.load_files;

import java.lang.management.ThreadInfo;
import java.util.concurrent.*;

public class Main {
      private final static int POOL_SIZE = Runtime.getRuntime().availableProcessors();
      public static void main(String[] args) throws InterruptedException, ExecutionException {
            testSynchronous();
            testAsynchronous();
      }

      private static void testSynchronous() {
            long start = System.currentTimeMillis();
            FileUtils.getStatistic("src/main/resources/result1.csv", "src/main/resources/file1.csv");
            FileUtils.getStatistic("src/main/resources/result2.csv", "src/main/resources/file2.csv");
            FileUtils.getStatistic("src/main/resources/result3.csv", "src/main/resources/file3.csv");
            long end = System.currentTimeMillis();

            System.out.println("Test synchronous");
            System.out.println("Time for statistic : " + (end - start));
            System.out.println();
      }
      private static void testAsynchronous() throws InterruptedException, ExecutionException {
            long start = System.currentTimeMillis();
            Callable<Void> callable1 = () -> {
                  FileUtils.getStatistic("src/main/resources/result10.csv", "src/main/resources/file1.csv");
                  return null;
            };
            Callable<Void> callable2 = () -> {
                  FileUtils.getStatistic("src/main/resources/result20.csv", "src/main/resources/file2.csv");
                  return null;
            };
            Callable<Void> callable3 = () -> {
                  FileUtils.getStatistic("src/main/resources/result30.csv", "src/main/resources/file3.csv");
                  return null;
            };
            ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);

            Future<Void> submit = executorService.submit(callable1);
            Future<Void> submit1 = executorService.submit(callable2);
            Future<Void> submit2 = executorService.submit(callable3);

            submit.get();
            submit1.get();
            submit2.get();
            if (submit.isDone() && submit1.isDone() && submit2.isDone()) {
                  System.out.println("All futures completed");
            }
            executorService.shutdown();

            long end = System.currentTimeMillis();

            System.out.println("Test asynchronous, processors : " + POOL_SIZE);
            System.out.println("Time for statistic : " + (end - start));
            System.out.println();
      }

}
