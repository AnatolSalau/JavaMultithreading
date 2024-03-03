package com.example._8_spring_async.controller;

import com.example._8_spring_async.model.Address;
import com.example._8_spring_async.model.ContactInfo;
import com.example._8_spring_async.model.Person;
import com.example._8_spring_async.service.AddressService;
import com.example._8_spring_async.service.ContactInfoService;
import com.example._8_spring_async.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {
      AddressService addressService;
      ContactInfoService contactInfoService;
      PersonService personService;

      @Autowired
      public DataController(AddressService addressService, ContactInfoService contactInfoService, PersonService personService) {
            this.addressService = addressService;
            this.contactInfoService = contactInfoService;
            this.personService = personService;
      }

      @GetMapping(value = "/addresses")
      public List<Address> getAddresses() {
            return addressService.getAllAddresses();
      }

      @GetMapping(value = "/contactinfos")
      public List<ContactInfo> getContactInfos() {
            return contactInfoService.getAllContactsInfos();
      }

      @GetMapping(value = "/persons")
      public List<Person> getPersons() {
            return personService.getAllPersons();
      }
}
