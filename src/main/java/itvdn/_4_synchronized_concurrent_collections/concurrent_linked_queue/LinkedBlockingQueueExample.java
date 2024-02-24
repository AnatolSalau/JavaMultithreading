package itvdn._4_synchronized_concurrent_collections.concurrent_linked_queue;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class LinkedBlockingQueueExample {
    /*public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        Runnable runnable1 = () -> {
            try {
//                System.out.println(queue.take());
                String textFromQueue = queue.poll(2, TimeUnit.SECONDS);

                if (textFromQueue != null) {
                    System.out.println(textFromQueue);
                } else {
                    System.out.println("Thread cant wait element from poll");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable runnable2 = () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.add("String from runnable 2");
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(queue);
    }*/
}
