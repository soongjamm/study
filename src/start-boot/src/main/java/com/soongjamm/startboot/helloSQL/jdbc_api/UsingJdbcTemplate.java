package com.soongjamm.startboot.helloSQL.jdbc_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UsingJdbcTemplate {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create() {
        jdbcTemplate.execute("create table mytable (id int, name varchar(255))");
    }

    @Transactional
    public void get() {
        List<Person> persons = jdbcTemplate.query("select * from mytable where id=1",
                (resultSet, i) -> new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
        System.out.println(persons.get(0));
    }
}
