package com.soongjamm.startboot.json_practice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class Person {

    @Id
    @JsonIgnore
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime localDateTime;
    private Date date;
    @JsonFormat(pattern = "yyyy MM dd - a hh mm ss")
    private Timestamp timestamp;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.localDateTime = LocalDateTime.now();
        this.date = new Date();
        this.timestamp = Timestamp.valueOf("2020-01-23 12:34:56");
    }

}
