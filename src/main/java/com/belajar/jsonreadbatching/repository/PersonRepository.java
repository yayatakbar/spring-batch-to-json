package com.belajar.jsonreadbatching.repository;

import com.belajar.jsonreadbatching.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    @Query(value = "select * from eka.lst_user_simultaneous", nativeQuery = true)
    List<Person> getUsername();;

}
