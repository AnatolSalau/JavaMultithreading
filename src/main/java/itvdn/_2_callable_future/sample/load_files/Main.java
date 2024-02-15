package itvdn._2_callable_future.sample.load_files;

public class Main {
      public static void main(String[] args) {
            long start = System.currentTimeMillis();
            FileUtils.getStatistic("src/main/resources/result1.csv", "src/main/resources/file1.csv");
            FileUtils.getStatistic("src/main/resources/result2.csv", "src/main/resources/file2.csv");
            FileUtils.getStatistic("src/main/resources/result3.csv", "src/main/resources/file3.csv");
            long end = System.currentTimeMillis();

            System.out.println("Time for statistic : " + (end - start));
      }
}
