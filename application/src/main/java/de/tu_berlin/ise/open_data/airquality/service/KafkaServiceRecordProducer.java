package de.tu_berlin.ise.open_data.airquality.service;

/**
 * Created by ahmadjawid on 6/13/17.
 */

public interface KafkaServiceRecordProducer {
    void produce(String jsonObject);
}
