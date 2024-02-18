package metanit;

public class JoinSample {
      public static void main(String[] args) throws InterruptedException {
            System.out.println("Main thread started...");
            Runnable r = ()->{
                  System.out.printf("%s started... \n", Thread.currentThread().getName());
                  try{
                        Thread.sleep(500);
                  }
                  catch(InterruptedException e){
                        System.out.println("Thread has been interrupted");
                  }
                  System.out.printf("%s finished... \n", Thread.currentThread().getName());
            };
            Thread myThread = new Thread(r,"MyThread");
            myThread.start();
            myThread.join();// main thread wait when MyThread is finished
            System.out.println("Main thread finished...");
      }
}
