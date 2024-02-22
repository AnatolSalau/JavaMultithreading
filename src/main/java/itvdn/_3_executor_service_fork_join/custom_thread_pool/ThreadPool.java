package itvdn._3_executor_service_fork_join.custom_thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {
      private final BlockingQueue<Runnable> blockingQueue;
      private final List<RunnablesPool> runnablesPoolsList = new ArrayList<>();
      private boolean isStopped = false;

      public ThreadPool(int numberOfThreads, int maxOfTasks) {
            blockingQueue = new ArrayBlockingQueue<>(maxOfTasks);

            for (int i = 0; i < numberOfThreads; i++) {
                  RunnablesPool runnablesPool = new RunnablesPool(blockingQueue);
                  runnablesPoolsList.add(runnablesPool);
            }

            for (RunnablesPool runnable : runnablesPoolsList ) {
                  new Thread(runnable).start();
            }
      }
      
      public synchronized  void execute(Runnable task) {
            if (this.isStopped) {
                  throw new IllegalStateException("Thread pool is stopped");
            }
            this.blockingQueue.add(task);
      }

      public synchronized void stop() {
            this.isStopped = true;
            for (RunnablesPool runnable : runnablesPoolsList) {
                  runnable.doStop();
            }
      }

      public synchronized void waitUntilAllTasksFinish() {
            while (this.blockingQueue.size() > 0) {
                  try {
                        Thread.sleep(1000);
                  } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                  }
            }
      }
}
