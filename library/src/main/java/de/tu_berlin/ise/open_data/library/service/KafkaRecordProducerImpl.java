package de.tu_berlin.ise.open_data.library.service;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ExecutionException;

/**
 * Created by ahmadjawid on 7/12/17.
 */
public class KafkaRecordProducerImpl implements KafkaRecordProducer {


    @Autowired
    private Producer producer;

    //Take the topic from environment variables or application.properties
    @Value("${kafka.topic}")
    private String topic;


    /** Send the json object to kafka queue
     * an exception is thrown after 60000 ms if kafka is not available - the importer will stop
     */
    @Override
    public void produce(String jsonObject) throws ExecutionException, InterruptedException {

      producer.send(new ProducerRecord(topic, jsonObject)).get();

    }
}
