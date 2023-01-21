package com.belajar.jsonreadbatching.batch;

import com.belajar.jsonreadbatching.model.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JsonPersonBatchConfig {

    //Check application.properties for api.url property, which contains the URL that gives dummy json
    private static final String dummyJsonUrl = "api.url";

    @Bean
    ItemReader<Person> restPersonReader(Environment environment, RestTemplate restTemplate) {
        return new JsonPersonReader(environment.getRequiredProperty(dummyJsonUrl), restTemplate);
    }

    @Bean
    ItemProcessor<Person, Person> restPersonProcessor() {
        return new PersonProcessor();
    }

    @Bean
    ItemWriter<Person> restPersonWriter() {
        return new JpaPersonWriter();
    }

    @Bean
    Step jsonPersonStep(ItemReader<Person> restPersonReader,
                        ItemProcessor<Person, Person> restPersonProcessor,
                        ItemWriter<Person> restStudentWriter,
                        StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("jsonPersonStep")
                .<Person, Person>chunk(10)
                .reader(restPersonReader)
                .processor(restPersonProcessor)
                .writer(restStudentWriter)
                .build();
    }

    @Bean
    Job jsonPersonJob(JobBuilderFactory jobBuilderFactory,
                      @Qualifier("jsonPersonStep") Step jsonPersonStep) {
        return jobBuilderFactory.get("jsonPersonJob")
                .incrementer(new RunIdIncrementer())
                .flow(jsonPersonStep)
                .end()
                .build();
    }

}
