package com.example._9_schedule_service.service;

import com.example._9_schedule_service.model.Address;
import com.example._9_schedule_service.model.ContactInfo;
import com.example._9_schedule_service.model.Person;

import java.util.List;

public interface ScheduleService {
      List<Address> getAllAddresses();
      List<ContactInfo> getAllContactInfos();
      List<Person> getAllPersons();
}
