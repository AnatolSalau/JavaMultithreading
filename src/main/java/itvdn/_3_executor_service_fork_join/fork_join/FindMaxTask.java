package itvdn._3_executor_service_fork_join.fork_join;

import java.io.Serial;
import java.util.concurrent.RecursiveTask;

public class FindMaxTask extends RecursiveTask<Integer> {
      @Serial
      private static final long serialVersionUID = 2069504954718300799L;

      private  int[] numbers;
      private int lowIndex;
      private int highIndex;

      public FindMaxTask(int[] numbers, int lowIndex, int highIndex) {
            this.numbers = numbers;
            this.lowIndex = lowIndex;
            this.highIndex = highIndex;
      }

      // THREESHOLD - value when we split tasks between different threads
      @Override
      protected Integer compute() {
            if (highIndex - lowIndex < FindMaxForkJoin.THRESHOLD) {
                  return findMaxNumber(); // if tasks quantity less than THREESHOLD we don't split them
            } else {
                  int middleIndex = (lowIndex + highIndex) / 2;

                  FindMaxTask findMaxTaskLeft = new FindMaxTask(numbers, lowIndex, middleIndex);
                  FindMaxTask findMaxTaskRight = new FindMaxTask(numbers, middleIndex + 1, highIndex);

                  //run left and right threads
                  invokeAll(findMaxTaskLeft, findMaxTaskRight);
                  //wait when we get max values from left and right part
                  int max = Math.max(findMaxTaskLeft.join(), findMaxTaskRight.join());

                  return max;
            }
      }

      private int findMaxNumber() {//
            int max = numbers[lowIndex];

            for (int i = lowIndex + 1; i < highIndex; i++) {
                  if (numbers[i] > max) {
                        max = numbers[i];
                  }
            }

            return max;
      }
}
