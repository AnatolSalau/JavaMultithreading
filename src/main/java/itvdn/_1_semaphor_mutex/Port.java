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
      private final int FREE_PLACES = 5;
      private final int DOCKERS = 3;

      private final Semaphore ships = new Semaphore(SHIPS);
      private final Semaphore free_places = new Semaphore(FREE_PLACES);
      private final Semaphore dockers = new Semaphore(DOCKERS);

      private boolean areUnloadedShips = true;

      private class Docker implements Runnable {

            @Override
            public void run() {
                  log.info("Docker thread {}", Thread.currentThread());
                  startWork(free_places);
                  toWait(5000);
                  finishWork(free_places);

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

      private void start() {
            int teamDockersNumber = 1;
            while (areUnloadedShips) {

                  new Thread(new Docker(), "" + teamDockersNumber).start();

                  teamDockersNumber++;
            }
      }

      public static void main(String[] args) {
            Port port = new Port();
            port.start();
      }
}
