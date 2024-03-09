package com.example._10_scather_gather_data_service.service;

import com.example._10_scather_gather_data_service.model.Product;

public interface ProductService {
      Product getProductById(Integer productId, String shop);
}
