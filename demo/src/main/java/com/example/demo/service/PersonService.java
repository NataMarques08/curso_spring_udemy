package com.example.demo.service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.example.demo.model.Person;

@Service
public class PersonService {
    private final AtomicLong counter = new AtomicLong();   
    private Logger logger = Logger.getLogger(PersonService.class.getName());

        public Person findById(String id){
        logger.info("Finding one person");
        Person person =  new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Natã");
        person.setLastName("Molina");
        person.setAddress("São Carlos - SP");
        person.setGender("Male");
        return person; 
    }

}
