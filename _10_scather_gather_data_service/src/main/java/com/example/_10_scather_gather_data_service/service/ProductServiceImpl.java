package com.example._10_scather_gather_data_service.service;

import com.example._10_scather_gather_data_service.model.Product;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {
      @Override
      public Product getProductById(Integer productId, String shop) {
            Random random = new Random();
            Product product = new Product(
                  productId,
                  "TestProductName from " + shop,
                  random.nextDouble(10.0) + 400,
                  "TestProductDescription from " + shop
            );
            return product;
      }
}
