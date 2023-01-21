package com.belajar.jsonreadbatching.service.person.implementation;

import com.belajar.jsonreadbatching.model.Person;
import com.belajar.jsonreadbatching.repository.PersonRepository;
import com.belajar.jsonreadbatching.service.person.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job jsonPersonJob;
    @Autowired
    private JobParameters personJobParameters;

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> getUsername() {
        return personRepository.getUsername();
    }

    @Override
    public Map executeBatch() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        LOGGER.info("job started!");
        jobLauncher.run(jsonPersonJob, personJobParameters);
        LOGGER.info("job stopped!");
        return Collections.singletonMap("SPRING BATCH", "OKE");
    }
}
