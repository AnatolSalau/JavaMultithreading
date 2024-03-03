package com.example._8_spring_async.service;

import com.example._8_spring_async.model.Address;
import com.example._8_spring_async.model.ContactInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ContactInfoServiceImpl implements ContactInfoService{


      @Override
      public List<ContactInfo> getAllContactsInfos() {
            log.info("Get all ContactInfo : start");

            ContactInfo address1 = new ContactInfo("number1", "email1@mail.ru");
            ContactInfo address2 = new ContactInfo("number2", "email2@mail.ru");
            ContactInfo address3 = new ContactInfo("number3", "email3@mail.ru");

            List<ContactInfo> resultList = new ArrayList<>(List.of(address1, address2, address3));

            log.info("Get all ContactInfo : finish");
            return resultList;
      }
}
