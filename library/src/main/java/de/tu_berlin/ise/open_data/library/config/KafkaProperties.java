package de.tu_berlin.ise.open_data.library.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ahmadjawid on 6/13/17.
 * Properties of kafka server are provided here.
 * These values are overridden by application.properties overridden by environment variables
 *
 */
@Configuration
@ConfigurationProperties("kafka")
public class KafkaProperties {




    /**
     * Number of replicas required to acknowledge the writing of data to the queue before proceeding.
     * Defaults to all
     */
    private String acks = "all";

    /**
     * defaults to localhost:9092
     */
    private String bootstrapServers = "localhost:9092";
    /**
     * defaults to org.apache.kafka.common.serialization.StringSerializer
     */
    private String keySerializer = "org.apache.kafka.common.serialization.StringSerializer";
    /**
     * defaults to org.apache.kafka.common.serialization.StringSerializer
     */
    private String valueSerializer = "org.apache.kafka.common.serialization.StringSerializer";

    /**
     * defaults to topic
     */
    private String topic = "topic";

    public String getAcks() {
        return acks;
    }

    public void setAcks(String acks) {
        this.acks = acks;
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getKeySerializer() {
        return keySerializer;
    }

    public void setKeySerializer(String keySerializer) {
        this.keySerializer = keySerializer;
    }

    public String getValueSerializer() {
        return valueSerializer;
    }

    public void setValueSerializer(String valueSerializer) {
        this.valueSerializer = valueSerializer;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

