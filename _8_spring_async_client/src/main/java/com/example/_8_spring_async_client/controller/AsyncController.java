package com.example._8_spring_async_client.controller;

import com.example._8_spring_async_client.model.Address;
import com.example._8_spring_async_client.model.ContactInfo;
import com.example._8_spring_async_client.model.Person;
import com.example._8_spring_async_client.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
public class AsyncController {

      private final AsyncService asyncService;

      @Autowired
      public AsyncController(AsyncService asyncService) {
            this.asyncService = asyncService;
      }

      @GetMapping(value = "/testAsync")
      public void testAsync() throws ExecutionException, InterruptedException {
            log.info("testAsync start");
            long start = System.currentTimeMillis();

            CompletableFuture<List<Address>> addresses = asyncService.getAllAddresses();
            CompletableFuture<List<ContactInfo>> contactInfos = asyncService.getAllContactInfos();
            CompletableFuture<List<Person>> persons = asyncService.getAllPersons();

            CompletableFuture.allOf(addresses, contactInfos, persons).join();
            log.info("Transaction time : " + (System.currentTimeMillis() - start));
            log.info("Addresses--> {}", addresses.get());
            log.info("ContactInfos--> {}", contactInfos.get());
            log.info("Persons--> {}", persons.get());
      }
}
