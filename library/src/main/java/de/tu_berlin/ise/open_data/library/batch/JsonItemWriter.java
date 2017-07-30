package de.tu_berlin.ise.open_data.library.batch;


import de.tu_berlin.ise.open_data.library.service.KafkaRecordProducer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ahmadjawid on 5/28/17.
 * Shared ItemWriter class across all importers
 */



public class JsonItemWriter implements ItemWriter<String> {

    @Autowired
    private KafkaRecordProducer kafkaRecordProducer;



    /**
     * @param items
     *Get the next list of items chunk and send to kafka producer one by one
     * */
    @Override
    public void write(List<? extends String> items) throws Exception {

        for (String jsonObject : items) {
            kafkaRecordProducer.produce(jsonObject);
        }

    }
}
