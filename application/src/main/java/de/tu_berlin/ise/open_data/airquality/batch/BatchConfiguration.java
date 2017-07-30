package de.tu_berlin.ise.open_data.airquality.batch;

import de.tu_berlin.ise.open_data.airquality.model.AirQuality;
import de.tu_berlin.ise.open_data.airquality.util.ResourcePreprocessor;
import de.tu_berlin.ise.open_data.library.batch.JobCompletionNotificationListener;
import de.tu_berlin.ise.open_data.library.batch.JsonItemWriter;
import de.tu_berlin.ise.open_data.library.batch.StepProcessListener;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.excel.AbstractExcelItemReader;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.*;

import java.io.*;

/**
 * Created by ahmadjawid on 5/21/17.
 * Configurations including jobs, job steps and how to read, write and process
 */

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {


    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ResourcePreprocessor preprocessor;



    /**
     * Register a bean of {@link CustomPoiItemReader} which defines how to read data from the source
     * @return CustomPoiItemReader
     * */
    @Bean
    ItemReader reader() throws IOException, InvalidFormatException {
        AbstractExcelItemReader<AirQuality> reader = new CustomPoiItemReader();

        //Set the resource to parse
        reader.setResource(new InputStreamResource(new PushbackInputStream(new FileInputStream(preprocessor.preProcess().getProcessedFile()))));

        //Skip first 5 lines
        reader.setLinesToSkip(5);

        reader.setRowMapper(excelRowMapper());
        return reader;
    }

    /**
     * Excel document cannot be read using the AirQuality class.
     * custom row mapper to map columns of excel file to attributes of the class
     * @return AirQualityExcelRowMapper
     */
    private RowMapper<AirQuality> excelRowMapper() {
        return new AirQualityExcelRowMapper();
    }


    /**
     * Register a bean of {@link org.springframework.batch.item.ItemProcessor} which defines how to process individual objects
     * @return AirQualityItemProcessor
     * */
    @Bean
    ItemProcessor<AirQuality, String> processor() {
        return new AirQualityItemProcessor();
    }


    /**
     * Register a bean of {@link org.springframework.batch.item.ItemWriter} which defines how to write individual json objects to kafka queue
     * @return JsonItemWriter
     * */
    @Bean
    ItemWriter<String> writer() {
        return new JsonItemWriter();
    }


    /**
     * Register a bean of {@link SkipPolicy} to skip a defined amount of records when a particular exception occurs
     * @return RecordSkipper
     * */
    @Bean
    public SkipPolicy recordSkipper() {
        return new RecordSkipper();
    }


    /**
     * Register a bean of {@link org.springframework.batch.core.StepExecutionListener} which defines
     * methods for listening to the events of processing steps and chunks
     * @return StepProcessListener
     * */
    @Bean
    public StepProcessListener stepExecutionListener() {
        return new StepProcessListener();
    }



    /**
     * Registers a job named 'airQualityJob' that is finished in one step
     * @param listener
     * @return {@link Job}
     * */
    @Bean
    Job airQualityJob(JobCompletionNotificationListener listener) throws IOException, InvalidFormatException {
        return jobBuilderFactory.get("airQualityJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }



    /**
     * Registers a job step named 'step1' which defines how to read, process and write
     * @return {@link Job}
     * */
    @Bean
    Step step1() throws IOException, InvalidFormatException {

        return stepBuilderFactory.get("step1").listener(stepExecutionListener())
                .<AirQuality, String>chunk(100)
                .reader(reader()).faultTolerant().skipPolicy(recordSkipper())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
