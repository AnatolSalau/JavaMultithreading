package itvdn._4_synchronized_concurrent_collections.concurrent_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteListExample {

      public static void main(String[] args) throws InterruptedException {
                  CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(List.of(1, 2, 3, 4, 5));

                  System.out.println("Start program : " + list);

                  Runnable printRunnable = () -> {
                        Iterator<Integer> iterator = list.iterator();
                        while (iterator.hasNext()) {
                              try {
                                    Thread.sleep(600);
                              } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                              }
                              System.out.println(iterator.next() + " : " + list);
                        }

                        iterator = list.iterator();
                        while (iterator.hasNext()) {
                              try {
                                    Thread.sleep(700);
                              } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                              }
                              System.out.println(iterator.next() + " : " + list);
                        }
                  };

                  Runnable addRunnable = () -> {

                        for (int i = 6; i < 11; i++) {
                              try {
                                    Thread.sleep(500);
                              } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                              }
                              list.add(i);
                              System.err.println(list);
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
