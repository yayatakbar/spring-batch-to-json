package com.belajar.jsonreadbatching.service.person;

import com.belajar.jsonreadbatching.model.Person;
import org.apache.ibatis.annotations.Select;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import java.util.List;
import java.util.Map;

public interface PersonService {
    Person savePerson(Person person);

    List<Person> getAllPerson();
    @Select("select * from eka.lst_user_simultaneous")
    List<Person> getUsername();

    Map executeBatch() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException;
}
