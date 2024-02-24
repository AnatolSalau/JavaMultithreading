package itvdn._4_synchronized_concurrent_collections.synchronized_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SynchronizedCollectionExample {
      public static void main(String[] args) throws InterruptedException {
            List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));
            List<Integer> synchronizedList = Collections.synchronizedList(list);

            System.out.println("Start program : " + list);

            Runnable printRunnable = () -> {
                  synchronized (synchronizedList) {
                        Iterator<Integer> iterator = synchronizedList.iterator();
                        while (iterator.hasNext()) {
                              try {
                                    Thread.sleep(1000);
                              } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                              }
                              System.out.println(iterator.next());
                        }
                  }
            };

            Runnable addRunnable = () -> {
                  synchronized (synchronizedList) {
                        for (int i = 6; i < 11; i++) {
                              try {
                                    Thread.sleep(500);
                              } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                              }
                              synchronizedList.add(i);
                              System.err.println(synchronizedList);
                        }
                  }
            };

            Thread print = new Thread(printRunnable);
            Thread add = new Thread(addRunnable);
            print.start();
            add.start();

            print.join();
            add.join();

            System.out.println("End program : " + list);
      }
}
