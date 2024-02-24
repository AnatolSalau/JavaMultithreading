package itvdn._4_synchronized_concurrent_collections.concurrent_linked_queue;

import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ConcurrentLinkedDequeExample {
    public static void main(String[] args) throws InterruptedException {
        Deque<String> queue = new ConcurrentLinkedDeque<>();
        queue.add("String 0");

        Runnable runnable1 = () -> {
            Iterator<String> iterator = queue.iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(200);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println(iterator.next());
                queue.poll();
            }
        };

        Runnable runnable2 = () -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    String str = "String " + i;
                    queue.addFirst(str);
                    System.out.println("Added : " + str);
                    Thread.sleep(200);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };


        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(queue);
    }
}