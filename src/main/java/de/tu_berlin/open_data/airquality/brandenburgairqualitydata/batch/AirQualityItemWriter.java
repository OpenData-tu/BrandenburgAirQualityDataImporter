package de.tu_berlin.open_data.airquality.brandenburgairqualitydata.batch;

import de.tu_berlin.open_data.airquality.brandenburgairqualitydata.model.AirQuality;
import de.tu_berlin.open_data.airquality.brandenburgairqualitydata.service.KafkaServiceRecordProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ahmadjawid on 7/1/17.
 */

public class AirQualityItemWriter implements ItemWriter<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirQualityItemWriter.class);

    @Autowired
    KafkaServiceRecordProducer kafkaServiceRecordProducer;

    @Override
    public void write(List<? extends String> items) throws Exception {

        for (String jsonObject : items){
            kafkaServiceRecordProducer.produce(jsonObject);
        }

    }
}
