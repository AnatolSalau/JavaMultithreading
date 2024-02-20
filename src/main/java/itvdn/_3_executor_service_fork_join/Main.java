package itvdn._3_executor_service_fork_join;

public class Main {
      public static void main(String[] args) {
            ThreadPool threadPool = new ThreadPool(3,10);

            for (int i = 0; i < 10; i++) {
                  int taskNumber = i;
                  threadPool.execute(() -> {
                        System.out.println(Thread.currentThread().getName() + "Task " + taskNumber);
                  });
            }

            threadPool.waitUntilAllTasksFinish();
            threadPool.stop();
      }
}
