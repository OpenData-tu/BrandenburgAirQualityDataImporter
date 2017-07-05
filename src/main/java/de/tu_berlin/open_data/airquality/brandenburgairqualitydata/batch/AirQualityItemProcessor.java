package de.tu_berlin.open_data.airquality.brandenburgairqualitydata.batch;

import de.tu_berlin.open_data.airquality.brandenburgairqualitydata.model.AirQuality;
import de.tu_berlin.open_data.airquality.brandenburgairqualitydata.service.ApplicationService;
import de.tu_berlin.open_data.airquality.brandenburgairqualitydata.service.JsonSchemaCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

/**
 * Created by ahmadjawid on 7/1/17.
 */

public class AirQualityItemProcessor implements ItemProcessor<AirQuality, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirQualityItemProcessor.class);

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private JsonSchemaCreator jsonSchemaCreator;


    @Override

    public String process(AirQuality item) throws Exception {

        LocalDate yesterday = LocalDate.now().minusDays(1);

        item.setTimestamp(applicationService.toISODateFormat(yesterday.toString()));

       // yesterday.minusDays(1);

       // item.setTimestamp(applicationService.toISODateFormat(LocalDateTime.now(ZoneId.of("Europe/Berlin")).toString()));

        return jsonSchemaCreator.create(item);
    }
}
