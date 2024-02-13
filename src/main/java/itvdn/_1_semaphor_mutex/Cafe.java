package itvdn._1_semaphor_mutex;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

@Slf4j
public final class Cafe {

      private final int TABLES = 3;
      private final Semaphore tables = new Semaphore(TABLES);

      private final int WAITERS = 1;
      private final Semaphore waiters = new Semaphore(WAITERS);

      private boolean isAvailableHours = true;

      private synchronized boolean isOpen() { return isAvailableHours; }

      private synchronized void close() { isAvailableHours = false; }

      private void startCafe() {
            Runnable cafe = () -> {
                  int peopleNumber = 0;
                  while (isOpen()) {
                       new Thread(new People(), "" + peopleNumber).start();
                       peopleNumber++;
                        try {
                              Thread.sleep(1000);
                        } catch (InterruptedException e) {
                              throw new RuntimeException(e);
                        }
                  }
            };

            Thread cafeThread = new Thread(cafe, "Cafe");
            cafeThread.start();

            try {
                  Thread.sleep(6000);
            } catch (InterruptedException e) {
                  throw new RuntimeException(e);
            }
            close();
      }
      class People implements Runnable {
            @Override
            public void run() {
                  log.info("Visitors from thread {} get into restaurant and searching table", Thread.currentThread().getName());

                  acquire(tables);

                  log.info("Visitors from thread {} sit down tp the table and call waiter", Thread.currentThread().getName());

                  acquire(waiters);

                  log.info("Waiter came up to visitors from thread {}", Thread.currentThread().getName());

                  toWait(2000);

                  log.info("Waiter get order from visitors from thread {}", Thread.currentThread().getName());

                  release(waiters);

                  toWait(4000);

                  acquire(waiters);

                  log.info("Waiter bring order to visitors from thread {}", Thread.currentThread().getName());
                  release(waiters);

                  toWait(4000);

                  log.info("Waiter from thread {} ate and left", Thread.currentThread().getName());

                  release(tables);

            }

            private void acquire(Semaphore semaphore) {
                  try {
                        semaphore.acquire();
                  } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                  }
            }

            private void release(Semaphore semaphore) {
                  semaphore.release();
            }

             private void toWait(int milliseconds) {
                  try {
                        Thread.sleep(milliseconds);
                  } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                  }
            }
      }

      public static void main(String[] args) {
            Cafe cafe = new Cafe();
            cafe.startCafe();
      }
}
