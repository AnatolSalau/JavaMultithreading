package metanit;

public class InterruptSample {
      public static void main(String[] args)  {
            System.out.println(Thread.currentThread().getName() + " start work");
            try {

                  Runnable myThread = () -> {
                        System.out.println(Thread.currentThread().getName() + " start work");
                        while (!Thread.currentThread().isInterrupted()) {
                              System.out.println(Thread.currentThread().getName());
                              System.out.println("Still work");
                              System.out.println();
/*                              try {
                                    Thread.sleep(500);
                              } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                              }*/
                        }
                        System.out.println(Thread.currentThread().getName() + " finish work");
                  };

                  Thread my_thread = new Thread(myThread, "My Thread");
                  my_thread.start();
                  Thread.sleep(2000);

                  my_thread.interrupt();

            } catch (InterruptedException ex) {
                  System.out.println("We catch exception");
            }
            System.out.println(Thread.currentThread().getName() + " finish work");
      }
}
