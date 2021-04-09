package com.soongjamm.startboot;


import com.soongjamm.startboot.json_practice.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    DatabaseSeeder seeder;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/")
    public String hello() {

        seeder.insertData();

        List<Person> persons = personRepository.findByFirstName("Victor");
        System.out.println(persons.get(0).getLastName());

        personRepository.updateByFirstName(1L, "cow");

        personRepository.save(new Person("kim", "seungtae"));

        return "hello world";
    }

}
