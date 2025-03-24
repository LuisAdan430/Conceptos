package com.lacc.springbatchestudio.configuratios;

import com.lacc.springbatchestudio.entities.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job ExtraccionUser(JobRepository jobRepository,Step step){
        return new JobBuilder("ExtraccionUser", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository ,
                     PlatformTransactionManager transactionManager,
                     ItemReader<Person> reader,
                     ItemProcessor<Person, Person> processor ,
                     ItemWriter<Person> writer){
        return new StepBuilder("step", jobRepository)
                .<Person, Person>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Person> reader(@Value("#{jobParameters['input.file']}") String filePath) {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(filePath));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("firstName", "lastName", "age");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return reader;
    }


    @Bean
    public ItemProcessor<Person, Person> processor(){
        return person ->{
            person.setAge(person.getAge());
            return person;
        };
    }

    @Bean
    public ItemWriter<Person> writer(){
        return items -> {
            for (Person person : items) {
                System.out.println("Writing" + person);
            }
        };
    }

}
