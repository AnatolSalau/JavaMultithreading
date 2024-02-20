package itvdn._3_executor_service_fork_join;

import java.util.concurrent.BlockingQueue;

/**
 * Create threads ana hold them in pull
 */
public class RunnablesPool implements Runnable {
      private Thread thread = null;
      private BlockingQueue<Runnable> blockingQueue;

      private boolean isStopped = false;

      public RunnablesPool(BlockingQueue<Runnable> blockingQueue) {
            this.blockingQueue = blockingQueue;
      }

      @Override
      public void run() {
            this.thread = Thread.currentThread();
            while (!isStopped()) {
                  try {
                        if (!blockingQueue.isEmpty()) {
                              Runnable runnable = blockingQueue.take();
                              runnable.run();
                        }
                  } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                  }
            }
      }

      public synchronized void doStop() {
            isStopped = true;
            this.thread.interrupt();
      }
      public void setStopped(boolean stopped) {
            isStopped = stopped;
      }
      public synchronized boolean isStopped() {
            return isStopped;
      }
}
