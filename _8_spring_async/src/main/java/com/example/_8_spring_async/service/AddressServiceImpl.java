package com.example._8_spring_async.service;

import com.example._8_spring_async.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService{

      @Override
      public List<Address> getAllAddresses() {
            log.info("Get all Address : start");

            Address address1 = new Address("Street1", "House1", "City1", 1001);
            Address address2 = new Address("Street2", "House2", "City2", 2002);
            Address address3 = new Address("Street3", "House3", "City3", 3003);

            List<Address> resultList = new ArrayList<>(List.of(address1, address2, address3));

            log.info("Get all Address : finish");
            return resultList;
      }
}
