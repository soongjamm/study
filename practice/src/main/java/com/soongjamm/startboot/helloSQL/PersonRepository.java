package com.soongjamm.startboot.helloSQL;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select * from person where first_name=:firstName")
    List<Person> findByFirstName(@Param("firstName") String firstName);

    @Modifying
    @Query("update person set first_name=:firstName where id=:id")
    boolean updateByFirstName(@Param("id") Long id, @Param("firstName") String firstName);
}
