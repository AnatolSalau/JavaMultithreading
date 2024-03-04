package com.example._8_spring_async_client.service;

import com.example._8_spring_async_client.model.Address;
import com.example._8_spring_async_client.model.ContactInfo;
import com.example._8_spring_async_client.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {

      private final RestTemplate restTemplate;

      @Autowired
      public AsyncServiceImpl(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
      }

      @Async(value = "ourCustomExecutor")
      @Override
      public CompletableFuture<List<Address>> getAllAddresses() {
            log.info("getAllAddresses : started");

            List<Address> addressList = restTemplate.getForObject("http://127.0.0.1:8081/addresses", List.class);
            log.info("Address list : {}", addressList);
            toSleep(1000);
            log.info("getAllAddresses : finished");
            return CompletableFuture.completedFuture(addressList);
      }
      @Async(value = "ourCustomExecutor")
      @Override
      public CompletableFuture<List<ContactInfo>> getAllContactInfos() {
            log.info("getAllContactInfos : started");

            List<ContactInfo> contactInfoList = restTemplate.getForObject("http://127.0.0.1:8081/contactinfos", List.class);
            log.info("ContactIndo list : {}", contactInfoList);
            toSleep(1000);
            log.info("getAllContactInfos : finished");
            return CompletableFuture.completedFuture(contactInfoList);
      }
      @Async(value = "ourCustomExecutor")
      @Override
      public CompletableFuture<List<Person>> getAllPersons() {
            log.info("getAllPersons : started");

            List<Person> personList = restTemplate.getForObject("http://127.0.0.1:8081/persons", List.class);
            log.info("Person list : {}", personList);
            toSleep(1000);
            log.info("getAllPersons : finished");
            return CompletableFuture.completedFuture(personList);
      }
}
