package com.example._9_schedule_service.service;

import com.example._9_schedule_service.model.Address;
import com.example._9_schedule_service.model.ContactInfo;
import com.example._9_schedule_service.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j

@Service
public class ScheduleServiceImpl implements ScheduleService {
      private final RestTemplate restTemplate;


      @Autowired
      public ScheduleServiceImpl(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
      }

      //@Scheduled(initialDelay = 3000, fixedRate = 1000)
      @Scheduled(cron = "* 36 18 ? * MON-SUN")
      @Override
      public List<Address> getAllAddresses() {
            log.info("getAllAddresses started");

            List<Address>  addresses= restTemplate
                  .getForObject("http://127.0.0.1:8081/addresses", List.class);
            log.info("Addresses: {}", addresses);
            log.info("getAllAddresses finished");
            return addresses;
      }

      //@Scheduled(cron = "* * * * *")

      //@Scheduled(cron = "* 10-11 5 * ?") // 5 day every month from 10 to 11 a.m every day of week

      //@Scheduled(cron = "5/15 10-11 5 * ?") // start in 5 min from 10 to 11 a.m every day of week every month a
      // and next will start in 20 min(after15min) from 10 to 11 a.m every day of week every month

      //@Scheduled(cron = "5/15 10-11 L * ?") // start in 5 min from 10 to 11 a.m, last day of month  every month

      //@Scheduled(cron = "5/15 10-11 10W * 6#3") // 6#3 - 6(friday), 3 (third friday of month)

      @Scheduled(cron = "1 30 9 ? * MON-FRI")
      @Override
      public List<ContactInfo> getAllContactInfos() {
            log.info("getAllContactInfos started");

            List<ContactInfo>  contactInfos= restTemplate
                  .getForObject("http://127.0.0.1:8081/contactinfos", List.class);
            log.info("contactInfos: {}", contactInfos);
            log.info("getAllContactInfos finished");
            return contactInfos;
      }

      @Scheduled(cron = " 1 30 9 ? * MON-FRI")
      @Override
      public List<Person> getAllPersons() {
            log.info("getAllPersons started");

            List<Person>  personList= restTemplate
                  .getForObject("http://127.0.0.1:8081/persons", List.class);
            log.info("personList: {}", personList);
            log.info("getAllPersons finished");
            return personList;
      }
}
