package itvdn._3_executor_service_fork_join.fork_join;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class FindMaxForkJoin {
      private static final int QUANTITY_TASKS = 100_000_000;
      public static int THRESHOLD = QUANTITY_TASKS / Runtime.getRuntime().availableProcessors();; //value after that we split tasks between threads
      private static int[] numbersQuickSort = initNumbersByRandom(QUANTITY_TASKS);

      private static int[] numbersForkJoin = Arrays.copyOf(numbersQuickSort, numbersQuickSort.length);


      public static void main(String[] args) {

            long start = System.currentTimeMillis();

            System.out.println("Max number naive : " + findMaxNumber(numbersQuickSort, numbersQuickSort.length));
            System.out.println("Time : " + (System.currentTimeMillis() - start));
            System.out.println();

            ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
            FindMaxTask task = new FindMaxTask(numbersForkJoin, 0, numbersForkJoin.length);

            start = System.currentTimeMillis();

            System.out.println("Max number fork-join : " + pool.invoke(task));
            System.out.println("Time : " + (System.currentTimeMillis() - start));
      }

      private static  int[] initNumbersByRandom(int arraySize) {
            Random random = new Random();

            int[] numbers = new int[arraySize];

            for (int i = 0; i < arraySize; i++) {
                  numbers[i] = random.nextInt(arraySize);
            }
            return numbers;
      }

      private static int findMaxNumber(int[] numbers, int highIndex) {//
/*            Arrays.sort(numbersQuickSort);
            int max = numbersQuickSort[numbersQuickSort.length - 1];*/
            int max = numbers[0];
            for (int i = 0; i < numbers.length; i++) {
                 max = Math.max(numbers[i], max);
            }
            return max;
      }

}
