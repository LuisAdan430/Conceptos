package com.lacc.springbatchestudio.Scheduleds;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

@Component
public class JobScheduler {

    @Autowired
    @Qualifier("ExtraccionUser")
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    @Value("${batch.input.file}")
    private String csvFilePath;

    private long lastCheckedTime = 0;

    @Scheduled(fixedDelay = 60000) // Se ejecuta cada minuto
    public void runJob() {
        try {
            File file = new File(csvFilePath);
            if (!file.exists()) {
                System.out.println("El archivo no existe.");
                return;
            }

            // Obtener la fecha de última modificación
            BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            long lastModified = attr.lastModifiedTime().toMillis();

            // Verificar si el archivo ha cambiado
            if (lastModified > lastCheckedTime) {
                lastCheckedTime = lastModified; // Actualizar el último tiempo verificado

                System.out.println("El archivo ha cambiado, ejecutando el job...");

                JobParameters jobParameters = new JobParametersBuilder()
                        .addString("input.file", file.getAbsolutePath())
                        .addLong("startAt", System.currentTimeMillis())
                        .toJobParameters();

                JobExecution jobExecution = jobLauncher.run(job, jobParameters);

                System.out.println("Batch Job '" + job.getName() + "' ejecutado con estado: " + jobExecution.getStatus());
            } else {
                System.out.println("No hay cambios en el archivo, no se ejecuta el job.");
            }
        } catch (Exception e) {
            System.out.println("Error al ejecutar el batch job: " + e.getMessage());
        }
    }

}
