package de.tu_berlin.ise.open_data.airquality.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ahmadjawid on 6/13/17.
 */
@Configuration
@ConfigurationProperties("kafka")
public class KafkaProperties {

    /**
     * defaults to localhost:9092
     */
    private String metadataBrokerList = "localhost:9092";
    /**
     * defaults to kafka.serializer.StringEncoder
     */
    private String serializerClass = "kafka.serializer.StringEncoder";
    private String partitionerClass;
    private String requestRequiredAcks;

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
     * defaults to weatherData
     */

    private String topic = "weatherData";

    public String getMetadataBrokerList() {
        return metadataBrokerList;
    }

    public void setMetadataBrokerList(String metadataBrokerList) {
        this.metadataBrokerList = metadataBrokerList;
    }

    public String getSerializerClass() {
        return serializerClass;
    }

    public void setSerializerClass(String serializerClass) {
        this.serializerClass = serializerClass;
    }

    public String getPartitionerClass() {
        return partitionerClass;
    }

    public void setPartitionerClass(String partitionerClass) {
        this.partitionerClass = partitionerClass;
    }

    public String getRequestRequiredAcks() {
        return requestRequiredAcks;
    }

    public void setRequestRequiredAcks(String requestRequiredAcks) {
        this.requestRequiredAcks = requestRequiredAcks;
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

