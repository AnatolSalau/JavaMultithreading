package itvdn._4_synchronized_concurrent_collections.email_service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class EmailService {
      private final static ConcurrentLinkedQueue<Mail> queue = new ConcurrentLinkedQueue<Mail>();

      private final static AtomicBoolean isRun = new AtomicBoolean(true);

      public static void main(String[] args) throws InterruptedException {
            System.out.println("Start email service");

            Runnable addEmailsRun = () -> {
                  long id = 1;
                  while (isRun.get()) {
                        Mail mail = new Mail(id, "Text " + id);
                        queue.add(mail);
                        System.out.println(" Email was added : " + mail);
                        id++;
                        try {
                              Thread.sleep(200);
                        } catch (InterruptedException e) {
                              throw new RuntimeException(e);
                        }
                  }
            };

            Runnable readEmailsRun = () -> {
                  while (isRun.get()) {
                        if (!queue.isEmpty()) {
                              Mail poll = queue.poll();
                              System.out.println(" Email was received from queue : " + poll);
                        }
                        try {
                              Thread.sleep(200);
                        } catch (InterruptedException e) {
                              throw new RuntimeException(e);
                        }
                  }
            };

            Thread addEmailThread = new Thread(addEmailsRun);
            Thread readEmailThread = new Thread(readEmailsRun);
            addEmailThread.start();
            readEmailThread.start();

            Thread.sleep(10_000);

            isRun.set(false);
            System.out.println("Finish email service");
      }


}
