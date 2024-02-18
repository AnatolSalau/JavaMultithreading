package metanit;

import java.util.concurrent.Exchanger;

public class ExchangerSample {

      public static void main(String[] args) {

            Exchanger<String> ex = new Exchanger<String>();
            new Thread(new PutThread(ex)).start();
            new Thread(new GetThread(ex)).start();
      }
}

class PutThread implements Runnable{

      Exchanger<String> exchanger;
      String message;

      PutThread(Exchanger<String> ex){

            this.exchanger=ex;
            message = "Message from PutThread";
      }
      public void run(){

            try{
                  Thread.currentThread().setName("PutThread");
                  message=exchanger.exchange(message);
                  System.out.println(Thread.currentThread().getName() + "  has received: " + message);
            }
            catch(InterruptedException ex){
                  System.out.println(ex.getMessage());
            }
      }
}
class GetThread implements Runnable{

      Exchanger<String> exchanger;
      String message;

      GetThread(Exchanger<String> ex){

            this.exchanger=ex;
            message = "Message from GetThread";
      }
      public void run(){

            try{
                  Thread.currentThread().setName("GetThread");
                  message=exchanger.exchange(message);
                  System.out.println(Thread.currentThread().getName() + "  has received: " + message);
            }
            catch(InterruptedException ex){
                  System.out.println(ex.getMessage());
            }
      }
}
