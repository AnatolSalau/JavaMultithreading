package metanit;

import java.util.concurrent.Phaser;

public class PhaserExample {

      public static void main(String[] args) {

            Phaser phaser = new Phaser(1);
            new Thread(new PhaseThread(phaser, "PhaseThread 1")).start();
            new Thread(new PhaseThread(phaser, "PhaseThread 2")).start();

            // wait end phase 0
            int phase = phaser.getPhase();
            phaser.arriveAndAwaitAdvance();
            System.out.println("Phase " + phase + " end");
            System.out.println("Unarrived parties : " + phaser.getUnarrivedParties());

            // wait end phase 1
            phase = phaser.getPhase();
            phaser.arriveAndAwaitAdvance();
            System.out.println("Phase " + phase + " end");
            System.out.println("Unarrived parties : " + phaser.getUnarrivedParties());

            // wait end phase 2
            phase = phaser.getPhase();
            phaser.arriveAndAwaitAdvance();
            System.out.println("Phase " + phase + " end");
            phaser.arriveAndDeregister();
            System.out.println("Arrived parties : " + phaser.getArrivedParties());
      }
}

class PhaseThread implements Runnable{

      Phaser phaser;
      String name;

      PhaseThread(Phaser p, String n){

            this.phaser=p;
            this.name=n;
            phaser.register();
      }
      public void run(){

            System.out.println(name + " perform phase " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance(); // message that phase 0 is finished
            try{
                  Thread.sleep(200);
            }
            catch(InterruptedException ex){
                  System.out.println(ex.getMessage());
            }
            System.out.println(name + " perform phase " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance(); // message that phase 1 is finished
            try{
                  Thread.sleep(200);
            }
            catch(InterruptedException ex){
                  System.out.println(ex.getMessage());
            }
            System.out.println(name + " perform phase " + phaser.getPhase());

            phaser.arriveAndDeregister(); //message that all phases are finished and delete all registrations
      }
}
