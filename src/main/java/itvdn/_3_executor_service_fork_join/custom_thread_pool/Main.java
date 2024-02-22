package itvdn._3_executor_service_fork_join.custom_thread_pool;

public class Main {
      public static void main(String[] args) {
            ThreadPool threadPool = new ThreadPool(4,10);

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
