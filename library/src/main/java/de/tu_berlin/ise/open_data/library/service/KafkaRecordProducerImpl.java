package de.tu_berlin.ise.open_data.library.service;

import de.tu_berlin.ise.open_data.library.config.KafkaProperties;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;

/**
 * Created by ahmadjawid on 7/12/17.
 * * Produces json messages for kafka queue
 */
public class KafkaRecordProducerImpl implements KafkaRecordProducer {



    //Fetch a KafkaProducer object from spring beans factory
    @Autowired
    private Producer producer;

    //Fetch a KafkaProperties object from spring beans factory
    @Autowired
    private KafkaProperties kafkaProperties;


    /**
     * Sends the json object to the kafka queue.
     * An exception is thrown after 60000 ms if kafka is not available (the importer will stop)
     * @param jsonObject
     */
    @Override
    public void produce(String jsonObject) throws ExecutionException, InterruptedException {


        //Send to kafka queue, wait for acknowledgement. If the server does not respond after 60000 ms, throw an exception.
        producer.send(new ProducerRecord(kafkaProperties.getTopic(), jsonObject)).get();

    }
}
