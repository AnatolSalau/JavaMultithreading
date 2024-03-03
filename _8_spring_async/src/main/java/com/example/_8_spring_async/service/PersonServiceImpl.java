package com.example._8_spring_async.service;

import com.example._8_spring_async.model.ContactInfo;
import com.example._8_spring_async.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService{

      @Override
      public List<Person> getAllPersons() {
            log.info("Get all Person : start");

            Person address1 = new Person("firstName1", "secondName2", "10");
            Person address2 = new Person("firstName2", "secondName3", "20");
            Person address3 = new Person("firstName2", "secondName3", "30");

            List<Person> resultList = new ArrayList<>(List.of(address1, address2, address3));

            log.info("Get all Person : finish");
            return resultList;
      }
}
