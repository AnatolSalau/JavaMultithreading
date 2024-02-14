package itvdn._1_semaphor_mutex;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * Задание

 * Создайте приложение, которое будет симулировать работу погрузочного порта.
 * У вас должно быть:
 * • 10 суден которые могут перевозить груз - Semaphore;
 * • 5 парковочных мест для суден - Semaphore;
 * • 3 команды грузчиков, которые погружают груз на судна - Threads.

 * Судно может парковаться только когда есть хотя-бы одно свободное место, и отплывать, когда
 * погрузчики закончили погрузку.
 * Погрузчики грузят судно 5 секунд, после чего оно может
 * отплывать.
 * Одна команда грузчиков, может грузить только одно судно одновременно.
 */
@Slf4j
public class Port {

      private final int SHIPS = 10;
      private final int FREE_PLACES = 2;
      private final int DOCKERS = 3;

      private final Semaphore ships = new Semaphore(SHIPS);
      private final Semaphore free_places = new Semaphore(FREE_PLACES);
      private final Semaphore dockers = new Semaphore(DOCKERS);

      private boolean areUnloadedShips = true;

      private class Docker implements Runnable {

            @Override
            public void run() {
                  log.info("Docker team number : {} start work day", Thread.currentThread().getName());
                  while (ships.availablePermits() > 0) {

                        if (free_places.availablePermits() > 0) {
                              int shipNumber = ships.availablePermits();
                              log.info("Docker team number : {} start unloads ship number : {}", Thread.currentThread().getName(), shipNumber);
                              startWork(ships);
                              startWork(free_places);

                              toWait(5000);

                              log.info("Docker finish unloads ship number : {},", shipNumber);
                              finishWork(free_places);
                        } else {

                              log.error("Docker team number : {} wait when place will be free  ", Thread.currentThread().getName());

                              startWork(free_places);

                              log.error("Docker team number : {} finish wait  ", Thread.currentThread().getName());

                              int shipNumber = ships.availablePermits();
                              log.info("Docker team number : {} start unloads ship number : {}", Thread.currentThread().getName(), shipNumber);
                              startWork(ships);
                              toWait(5000);

                              log.info("Docker finish unloads ship number : {},", shipNumber);
                              finishWork(free_places);
                        }
                  }

                  log.info("Docker team number : {} finish work day because of there aren't ships", Thread.currentThread().getName());
                  log.info("Ships left in port : {}", ships.availablePermits());
            }

            private void startWork(Semaphore semaphore) {
                  try {
                        semaphore.acquire();
                  } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                  }

            }

            private void finishWork(Semaphore semaphore) {
                        semaphore.release();
            }

            private void toWait(int milliseconds) {
                  try {
                        Thread.sleep(milliseconds);
                  } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                  }
            }
      }

      private void startApp() {
            //run dockers
            for (int i = 1; i <= DOCKERS; i++) {
                  new Thread(new Docker(), String.valueOf(i)).start();
                  try {
                        Thread.sleep(1000);
                  } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                  }
            }


      }

      public static void main(String[] args) {
            Port port = new Port();
            port.startApp();
      }
}
