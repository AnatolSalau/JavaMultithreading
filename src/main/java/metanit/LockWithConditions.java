package metanit;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockWithConditions {

      public static void main(String[] args) {

            StoreLocked store=new StoreLocked();
            ProducerLocked producer = new ProducerLocked(store);
            ConsumerLocked consumer = new ConsumerLocked(store);
            new Thread(producer).start();
            new Thread(consumer).start();
      }
}
// Класс Магазин, хранящий произведенные товары
class StoreLocked{
      private int product=0;
      ReentrantLock locker;
      Condition condition;

      StoreLocked(){
            locker = new ReentrantLock(); // создаем блокировку
            condition = locker.newCondition(); // получаем условие, связанное с блокировкой
      }

      public void get() {

            locker.lock();
            try{
                  // пока нет доступных товаров на складе, ожидаем
                  while (product<1)
                        condition.await();

                  product--;
                  System.out.println("Покупатель купил 1 товар");
                  System.out.println("Товаров на складе: " + product);

                  // сигнализируем
                  condition.signalAll();
            }
            catch (InterruptedException e){
                  System.out.println(e.getMessage());
            }
            finally{
                  locker.unlock();
            }
      }
      public void put() {

            locker.lock();
            try{
                  // пока на складе 3 товара, ждем освобождения места
                  while (product>=3)
                        condition.await();

                  product++;
                  System.out.println("Производитель добавил 1 товар");
                  System.out.println("Товаров на складе: " + product);
                  // сигнализируем
                  condition.signalAll();
            }
            catch (InterruptedException e){
                  System.out.println(e.getMessage());
            }
            finally{
                  locker.unlock();
            }
      }
}
// класс Производитель
class ProducerLocked implements Runnable{

      StoreLocked store;
      ProducerLocked(StoreLocked store){
            this.store=store;
      }
      public void run(){
            for (int i = 1; i < 6; i++) {
                  store.put();
            }
      }
}
// Класс Потребитель
class ConsumerLocked implements Runnable{

      StoreLocked store;
      ConsumerLocked(StoreLocked store){
            this.store=store;
      }
      public void run(){
            for (int i = 1; i < 6; i++) {
                  store.get();
            }
      }
}
