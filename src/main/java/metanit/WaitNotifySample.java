package metanit;

public class WaitNotifySample {
      public static void main(String[] args) {

            Store store = new Store();
            Producer producer = new Producer(store);
            Consumer consumer = new Consumer(store);
            new Thread(producer).start();
            new Thread(consumer).start();
      }
}

// Class Store keeps goods
class Store {
      private int product = 0;

      public synchronized void get() {
            while (product < 1) {
                  try {
                        System.out.println(Thread.currentThread().getName() + " is wait");
                        wait();
                  } catch (InterruptedException e) {
                  }
            }
            product--;
            System.out.println(Thread.currentThread().getName() + " : Consumer buy 1 good");
            System.out.println(Thread.currentThread().getName() + " : QTY goods on warehouse : " + product);
            notify();
      }

      public synchronized void put() {
            while (product >= 3) {
                  try {
                        System.out.println(Thread.currentThread().getName() + " is wait");
                        wait();
                  } catch (InterruptedException e) {
                  }
            }
            product++;
            System.out.println(Thread.currentThread().getName() + " : Producer add 1 good");
            System.out.println(Thread.currentThread().getName() + " : QTY goods on warehouse : " + product);
            notify();
      }
}

// class Producer
class Producer implements Runnable {

      Store store;

      Producer(Store store) {
            this.store = store;
      }

      public void run() {
            System.out.println(Thread.currentThread().getName() + " start");
            for (int i = 1; i < 6; i++) {
                  store.put();
            }
            System.out.println(Thread.currentThread().getName() + " finish");
      }
}

// class Consumer
class Consumer implements Runnable {

      Store store;

      Consumer(Store store) {
            this.store = store;
      }

      public void run() {
            System.out.println(Thread.currentThread().getName() + " start");
            for (int i = 1; i < 6; i++) {
                  store.get();
            }
            System.out.println(Thread.currentThread().getName() + " finish");
      }
}
