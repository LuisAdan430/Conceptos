package com.lacc.springbatchestudio.controllers;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    /*
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("ExtraccionUser")
    private Job job;


    @GetMapping("/start")
    public String startBatch() {
        try {
            JobParameters jobParameters = new JobParametersBuilder().addLong("startAt", System.currentTimeMillis()).toJobParameters();
            jobLauncher.run(job, jobParameters);
            return "Batch job '" + job.getName() + "'started successfully!";
        } catch (Exception e) {
            return "Error starting batch job: " + e.getMessage();
        }
    } */

}
