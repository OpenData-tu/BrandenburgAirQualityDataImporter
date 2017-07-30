package de.tu_berlin.ise.open_data.library.config;

import de.tu_berlin.ise.open_data.library.service.ApplicationServiceImpl;
import de.tu_berlin.ise.open_data.library.batch.StepProcessListener;
import de.tu_berlin.ise.open_data.library.batch.JobCompletionNotificationListener;
import de.tu_berlin.ise.open_data.library.batch.JsonItemWriter;
import de.tu_berlin.ise.open_data.library.service.JsonStringBuilder;
import de.tu_berlin.ise.open_data.library.service.KafkaRecordProducerImpl;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by ahmadjawid on 6/13/17.
 * Includes configuration for kafka.
 * All re-usable beans are registered here
 *
 */

@Configuration
@EnableConfigurationProperties({KafkaProperties.class})
public class ServiceConfiguration {


    @Autowired
    private KafkaProperties kafkaProperties;


    /**Set the properties of kafka producer
     * @return KafkaProducer
     * */
    @Bean
    Producer producer() {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", kafkaProperties.getBootstrapServers());
        properties.put("key.serializer", kafkaProperties.getKeySerializer());
        properties.put("value.serializer", kafkaProperties.getValueSerializer());
        properties.put("acks", kafkaProperties.getAcks());

        return new KafkaProducer(properties);
    }


    @Bean
    public ApplicationServiceImpl service() {
        return new ApplicationServiceImpl();
    }

    @Bean
    KafkaRecordProducerImpl kafkaRecordProducer() {
        return new KafkaRecordProducerImpl();
    }

    @Bean
    JsonItemWriter jsonItemWriter() {
        return new JsonItemWriter();
    }

    @Bean
    JsonStringBuilder jsonService() {
        return new JsonStringBuilder();
    }

    @Bean
    JobCompletionNotificationListener jobCompletionNotificationListener() {
        return new JobCompletionNotificationListener();
    }

    @Bean
    StepProcessListener customStepListener(){
        return new StepProcessListener();
    }


}
