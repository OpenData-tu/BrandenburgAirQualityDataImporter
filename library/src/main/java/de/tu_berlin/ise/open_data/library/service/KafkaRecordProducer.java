package de.tu_berlin.ise.open_data.library.service;

import java.util.concurrent.ExecutionException;

/**
 * Created by ahmadjawid on 7/12/17.
 * Produces json messages for kafka queue
 */
public interface KafkaRecordProducer {


    /**
     * Sends the json object to the kafka queue
     * @param jsonObject
     * */
    void produce(String jsonObject) throws ExecutionException, InterruptedException;
}
