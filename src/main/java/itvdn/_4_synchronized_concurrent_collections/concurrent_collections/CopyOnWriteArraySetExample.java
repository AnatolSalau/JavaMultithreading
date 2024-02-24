package itvdn._4_synchronized_concurrent_collections.concurrent_collections;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArraySetExample {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArraySet<String> arrayList = new CopyOnWriteArraySet<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");

        Runnable runnable1 = () -> {
            Iterator<String> iterable = arrayList.iterator();
            while (iterable.hasNext()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(iterable.next());
            }
        };

        Runnable runnable2 = () -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arrayList.remove("5");
            arrayList.add("6");
            System.err.println(arrayList);
        };

        System.out.println(arrayList);
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(arrayList);
        thread1 = new Thread(runnable1);
        thread1.start();
        thread1.join();
    }
}
