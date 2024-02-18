package metanit;

import java.util.List;

public class SynchronizedSample {
      private final Counter counter = new Counter();

      public static void main(String[] args) throws InterruptedException {
            System.out.println("Main thread started...");

            SynchronizedSample synchronizedSample = new SynchronizedSample();

            System.out.println("Counter : " + synchronizedSample.counter.value);
            List<Thread> threads = synchronizedSample.testSynchronized();

            threads.get(0).join();
            threads.get(1).join();

            System.out.println("Counter : " + synchronizedSample.counter.value);

            System.out.println("Main thread finished...");
      }

      static class Counter {
            int value = 0;

            public synchronized void increase() {
                  value += 1;
            }
      }

      public List<Thread> testSynchronized() {
            Runnable r = ()->{
                  System.out.printf("%s started... \n", Thread.currentThread().getName());
                  try{
                        for (int i = 0; i < 10; i++) {
                              synchronized (counter) {
                                    System.out.println(Thread.currentThread().getName() + " increase value");
                                    counter.value +=1;
                                    Thread.sleep(500);
                              }
/*                              System.out.println(Thread.currentThread().getName() + " increase value");
                              Thread.sleep(500);
                              counter.increase();*/
                        }
                  }
                  catch(InterruptedException e){
                        System.out.println("Thread has been interrupted");
                  }
                  System.out.printf("%s finished... \n", Thread.currentThread().getName());
            };
            Thread thread1 = new Thread(r,"Thread 1");
            Thread thread2 = new Thread(r,"Thread 2");

            thread1.start();
            thread2.start();

            return List.of(thread1, thread2);
      }
}
