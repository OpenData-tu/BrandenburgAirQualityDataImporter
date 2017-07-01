package de.tu_berlin.open_data.airquality.brandenburgairqualitydata.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by ahmadjawid on 6/13/17.
 */
@Configuration
@EnableConfigurationProperties({KafkaProperties.class})
public class KafkaConfiguration {

    @Autowired
    KafkaProperties kafkaProperties;

    @Bean
    Producer producer() {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", kafkaProperties.getBootstrapServers());
        properties.put("metadata.broker.list", kafkaProperties.getMetadataBrokerList());
        properties.put("serializer.class", kafkaProperties.getSerializerClass());
        properties.put("key.serializer", kafkaProperties.getKeySerializer());
        properties.put("value.serializer", kafkaProperties.getValueSerializer());

        return new KafkaProducer(properties);
    }


}
