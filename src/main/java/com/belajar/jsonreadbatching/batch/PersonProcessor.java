package com.belajar.jsonreadbatching.batch;

import com.belajar.jsonreadbatching.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonProcessor implements ItemProcessor<Person, Person> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonProcessor.class);

    @Override
    public Person process(Person item) throws Exception {
        LOGGER.info("Processing person information: ", item.getUsername());
        //Capitalize the name
        item.setUsername(item.getUsername().toUpperCase());
        return item;
    }
}
