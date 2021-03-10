package com.soongjamm.startboot.helloSQL;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HelloController {

    @Autowired
    DatabaseSeeder seeder;

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {

        seeder.insertData();
//
//        List<Person> persons = personRepository.findByFirstName("Victor");
//        System.out.println(persons.get(0).getLastName());
//
//        personRepository.updateByFirstName(1L, "cow");
//
//        personRepository.save(new Person("kim", "seungtae"));

        return "hello world";
    }


}
