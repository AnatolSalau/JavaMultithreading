package com.example._10_scather_gather_client_service;

import com.example._10_scather_gather_client_service.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootApplication
public class Application {

      public static void main(String[] args) throws ExecutionException, InterruptedException {
            ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);

            ExecutorService executorService = Executors.newFixedThreadPool(3);

            List<String> shops = List.of("amazon", "ebay", "wallmart");
            List<Future<Product>> products = new ArrayList<>();

            for (int i = 0; i < shops.size(); i++) {
                  products.add(executorService.submit(new GetProductTask(shops.get(i), i)));
            }

            StringBuffer stringBuffer = new StringBuffer();

            for (Future<Product> productFuture : products) {
                  Product product = productFuture.get();
                  stringBuffer.append(product.getProductName()).append(" : ").append(product.getProductPrice()).append("\n");
            }
            System.out.println(stringBuffer);
            executorService.shutdown();

            Thread.sleep(2000);
            SpringApplication.exit(applicationContext, () -> 0);
      }

}
