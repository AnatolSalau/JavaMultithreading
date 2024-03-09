package com.example._10_scather_gather_client_service;

import com.example._10_scather_gather_client_service.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.util.concurrent.Callable;

public class GetProductTask implements Callable<Product> {
      private final String shop;
      private final Integer id;

      public GetProductTask(String shop, Integer id) {
            this.shop = shop;
            this.id = id;
      }

      @Override
      public Product call() throws Exception {
            URI uri = new URI("http://127.0.0.1:8080/product/" + shop + "/" + id );
            URL url = uri.toURL();
            ObjectMapper objectMapper = new ObjectMapper();

            Product product = objectMapper.readValue(url, Product.class);
            System.out.println(product);
            return product;
      }
}
