package com.belajar.jsonreadbatching.batch;

import com.belajar.jsonreadbatching.model.Person;
import com.belajar.jsonreadbatching.service.person.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JpaPersonWriter implements ItemWriter<Person> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaPersonWriter.class);


    private PersonService personService;

    @Override
    public void write(List<? extends Person> list) throws Exception {
        for (Person data:list
             ) {
            LOGGER.info("Writing person : " + data.getUsername());
            Person person = personService.savePerson(data);
            LOGGER.info("Person : \n" + person.getUsername() + "\n");
        }
    }

}
