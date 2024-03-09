package com.example._10_scather_gather_client_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
      private  Integer productId;
      private  String productName;
      private  Double productPrice;
      private  String productDescription;
}
