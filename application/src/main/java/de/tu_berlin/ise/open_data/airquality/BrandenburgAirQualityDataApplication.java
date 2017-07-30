package de.tu_berlin.ise.open_data.airquality;

import de.tu_berlin.ise.open_data.airquality.util.NumberToGermanDaysOfWeek;
import de.tu_berlin.ise.open_data.library.config.ServiceConfiguration;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * Created by ahmadjawid on 7/1/17.
 */

@SpringBootApplication
@EnableTask
@Import(ServiceConfiguration.class)
public class BrandenburgAirQualityDataApplication implements CommandLineRunner {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(BrandenburgAirQualityDataApplication.class, args);
    }

    //Get an instance from Spring Bean Factory to execute jdbc queries
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * Executes a simple query to make unexpectedly failed jobs restartable that is to start them exactly from where they failed.
     * In order for the jobs to be restartable you should use a persistent database (not in memory).
     */
    @Override
    public void run(String... strings) throws Exception {



        jdbcTemplate.execute("UPDATE BATCH_JOB_EXECUTION a SET a.VERSION=a.VERSION + 1, END_TIME=LAST_UPDATED," +
                " STATUS='FAILED', EXIT_CODE='FAILED' WHERE STATUS='STARTED'" +
                " AND a.JOB_INSTANCE_ID = (SELECT JOB_INSTANCE_ID FROM BATCH_JOB_INSTANCE WHERE JOB_INSTANCE_ID = a.JOB_INSTANCE_ID)" +
                " AND END_TIME IS NULL");
    }




}
