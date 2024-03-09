package com.example._10_scather_gather_data_service.controller;

import com.example._10_scather_gather_data_service.model.Product;
import com.example._10_scather_gather_data_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

      private final ProductService productService;

      @Autowired
      public DataController(ProductService productService) {
            this.productService = productService;
      }

      @GetMapping(value = "/product/amazon/{productId}")
      public Product getProductFromAmazon(@PathVariable("productId") Integer productId) {
            Product amazon = productService.getProductById(productId, "Amazon");
            return amazon;
      }

      @GetMapping(value = "/product/wallmart/{productId}")
      public Product getProductFromWallmart(@PathVariable("productId") Integer productId) {
            Product wallmart = productService.getProductById(productId, "Wallmart");
            return wallmart;
      }

      @GetMapping(value = "/product/ebay/{productId}")
      public Product getProductFromEbay(@PathVariable("productId") Integer productId) {
            Product ebay = productService.getProductById(productId, "Ebay");
            return ebay;
      }
}
