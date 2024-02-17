package itvdn._2_callable_future.sample.load_files;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
      public static void main(String[] args) {
            //testSynchronous();
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
      private static void testAsynchronous() {
            Callable<Void> callable1 = () -> {
                  FileUtils.getStatistic("src/main/resources/result1.csv", "src/main/resources/file1.csv");
                  return null;
            };
            Callable<Void> callable2 = () -> {
                  FileUtils.getStatistic("src/main/resources/result2.csv", "src/main/resources/file2.csv");
                  return null;
            };
            Callable<Void> callable3 = () -> {
                  FileUtils.getStatistic("src/main/resources/result3.csv", "src/main/resources/file3.csv");
                  return null;
            };
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            long start = System.currentTimeMillis();

            executorService.submit(callable1);
            executorService.submit(callable2);
            executorService.submit(callable3);

            long end = System.currentTimeMillis();
            executorService.shutdown();
            System.out.println("Test asynchronous");
            System.out.println("Time for statistic : " + (end - start));
            System.out.println();
      }

}
