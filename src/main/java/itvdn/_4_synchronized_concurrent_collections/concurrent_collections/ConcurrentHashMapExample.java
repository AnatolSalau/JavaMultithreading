package itvdn._4_synchronized_concurrent_collections.concurrent_collections;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
      public static void main(String[] args) throws InterruptedException {
            ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
            map.put(1, "Java");
            map.put(2, "C#");
            map.put(3, "C++");
            map.put(4, "Python");
            map.put(5, "Go");

            Runnable runnable1 = () -> {
                  System.out.println(map);
                  Iterator<Integer> iterator = map.keySet().iterator();
                  while (iterator.hasNext()) {
                        try {
                              Thread.sleep(1000);
                        } catch (InterruptedException e) {
                              e.printStackTrace();
                        }
                        Integer i = iterator.next();
                        System.out.println(i + " : " + map.get(i));
                  }
            };

            Runnable runnable2 = () -> {
                  System.err.println(map);
                  try {
                        Thread.sleep(2000);
                  } catch (InterruptedException e) {
                        e.printStackTrace();
                  }
                  map.putIfAbsent(6, "JavaScript");
                  System.err.println(map);
            };

            Thread thread1 = new Thread(runnable1);
            Thread thread2 = new Thread(runnable2);
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
      }
}
