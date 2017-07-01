package de.tu_berlin.open_data.airquality.brandenburgairqualitydata.batch;

import de.tu_berlin.open_data.airquality.brandenburgairqualitydata.model.AirQuality;
import de.tu_berlin.open_data.airquality.brandenburgairqualitydata.service.ApplicationService;
import de.tu_berlin.open_data.airquality.brandenburgairqualitydata.service.JsonSchemaCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class AirQualityItemProcessor implements ItemProcessor<AirQuality, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirQualityItemProcessor.class);

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private JsonSchemaCreator jsonSchemaCreator;


    @Override

    public String process(AirQuality item) throws Exception {

        item.setTimestamp("2099-11-11");


        return jsonSchemaCreator.create(item);
    }
}
