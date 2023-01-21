package com.belajar.jsonreadbatching.batch;

import com.belajar.jsonreadbatching.model.Person;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class JsonPersonReader implements ItemReader<Person> {

    private final String dummyJsonUrl;
    private final RestTemplate restTemplate;

    private int nextPersonIndex;
    private List<Person> personList;

    JsonPersonReader(String dummyJsonUrl, RestTemplate restTemplate) {
        this.dummyJsonUrl = dummyJsonUrl;
        this.restTemplate = restTemplate;
        nextPersonIndex = 0;
    }

    @Override
    public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (personDataIsNotInitialized()) {
            personList = fetchPersonDataFromApi();
        }

        Person nextPerson = null;

        if (nextPersonIndex < personList.size()) {
            nextPerson = personList.get(nextPersonIndex);
            nextPersonIndex++;
        }

        return nextPerson;
    }

    private boolean personDataIsNotInitialized() {
        return this.personList == null;
    }

    private List<Person> fetchPersonDataFromApi() {
        ResponseEntity<Person[]> response = restTemplate.getForEntity(
                dummyJsonUrl,
                Person[].class
        );
        Person[] personData = response.getBody();
        return Arrays.asList(personData);
    }

}
