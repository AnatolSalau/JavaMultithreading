package com.example._10_scather_gather_client_service.model;

import lombok.Data;

@Data
public class Product {
      private final Integer productId;
      private final String productName;
      private final Double productPrice;
      private final String productDescription;
}
