package com.example._8_spring_async_client.service;


import com.example._8_spring_async_client.model.Address;
import com.example._8_spring_async_client.model.ContactInfo;
import com.example._8_spring_async_client.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface AsyncService {

      default void toSleep(int duration) {
            try {
                  Thread.sleep(duration);
            } catch (InterruptedException e) {
                  throw new RuntimeException(e);
            }
      }
      CompletableFuture<List<Address>> getAllAddresses();
      CompletableFuture<List<ContactInfo>> getAllContactInfos();
      CompletableFuture<List<Person>> getAllPersons();
}
