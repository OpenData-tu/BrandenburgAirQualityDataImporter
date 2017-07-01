package de.tu_berlin.open_data.airquality.brandenburgairqualitydata.batch;

import de.tu_berlin.open_data.airquality.brandenburgairqualitydata.model.AirQuality;
import de.tu_berlin.open_data.airquality.brandenburgairqualitydata.service.ApplicationService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.excel.AbstractExcelItemReader;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.*;

import javax.sql.DataSource;
import java.io.*;
import java.net.URL;


/**
 * Created by ahmadjawid on 5/21/17.
 */

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {


    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public ApplicationService applicationService;

    @Autowired
    @Qualifier("dataSource")
    public DataSource dataSource;



    private static final String PROPERTY_EXCEL_SOURCE_FILE_PATH = "excel.to.database.job.source.file.path";


    @Bean
    ItemReader<AirQuality> reader(Environment environment) throws IOException {
        AbstractExcelItemReader<AirQuality> reader = new CustomPoiItemReader();

      URL url = new URL("https://luftdaten.brandenburg.de/home/-/bereich/datenexport/Montag.xls");
        //reader.setMaxItemCount(10);
       // reader.setResource((new InputStreamResource(new PushbackInputStream(url.openStream()))));
        //reader.setResource((new ClassPathResource("Montag.xls")));
        reader.setResource(new InputStreamResource(new PushbackInputStream(new FileInputStream(new File("Montag-work.xls")))));
        reader.setLinesToSkip(5);

       // reader.setMaxItemCount(20);
       // reader.setMaxItemCount(10);


//        reader.setRowMapper(new BeanWrapperRowMapper<AirQuality>() {{
//            setTargetType(AirQuality.class);
//        }});

       reader.setRowMapper(excelRowMapper());
        return reader;
    }

//    private RowMapper<AirQuality> excelRowMapper() {
//        BeanWrapperRowMapper<AirQuality> rowMapper = new BeanWrapperRowMapper<>();
//        rowMapper.setTargetType(AirQuality.class);
//        return rowMapper;
//    }

    /**
     * excel document cannot be read using the class.
     * custom row mapper to map columns of excel file to attributes of the class
     */
    private RowMapper<AirQuality> excelRowMapper() {
       return new AirQualityExcelRowMapper();
    }

    @Bean
    ItemProcessor<AirQuality, String> processor() {
        return new AirQualityItemProcessor();
    }

    @Bean
    ItemWriter<String> writer() {
        return new AirQualityItemWriter();
    }

    @Bean
    public SkipPolicy recordSkipper() {
        return new RecordSkipper();
    }

    @Bean
    Step step1(ItemReader<AirQuality> itemReader,
               ItemProcessor itemProcessor,
               ItemWriter<String> itemWriter,
               StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("step1")
                .<AirQuality, AirQuality>chunk(1)
                .reader(itemReader).faultTolerant().skipPolicy(recordSkipper())
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    Job airQualityJob(JobBuilderFactory jobBuilderFactory,
                               @Qualifier("step1") Step excelStudentStep) {
        return jobBuilderFactory.get("airQualityJob")
                .incrementer(new RunIdIncrementer())
                .flow(excelStudentStep)
                .end()
                .build();
    }

    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcess(JobRegistry jobRegistry) {
        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
        jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
        return jobRegistryBeanPostProcessor;
    }
}
