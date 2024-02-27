package itvdn._6_run_apply_async.run_async;

import java.util.concurrent.*;

public class RunAsyncExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executor = Executors.newFixedThreadPool(3);
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Runnable runnable = () -> {
                toSleep(5000);
            System.out.printf("Я работал в %s потоке\n", Thread.currentThread().getName());
//            throw new RuntimeException("Ошибка в асинхронной задаче");
        };

        System.out.printf("Поток %s начал работу\n", Thread.currentThread().getName());

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(runnable, executor);
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(runnable, executor);
        CompletableFuture<Void> completableFuture3 = CompletableFuture.runAsync(runnable, executor);

//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(runnable, executor);

        System.out.println("Асинхронная задача запущена");

        System.out.printf("Поток %s продолжил работу\n", Thread.currentThread().getName());

        completableFuture.get();
        completableFuture2.get();
        completableFuture3.get();

        executor.shutdown();

        System.out.printf("Поток %s завершил работу\n", Thread.currentThread().getName());
    }

    public static void toSleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
