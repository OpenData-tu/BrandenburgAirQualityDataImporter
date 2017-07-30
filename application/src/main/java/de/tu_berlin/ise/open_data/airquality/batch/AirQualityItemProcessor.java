package de.tu_berlin.ise.open_data.airquality.batch;

import de.tu_berlin.ise.open_data.airquality.model.AirQuality;
import de.tu_berlin.ise.open_data.library.service.ApplicationService;
import de.tu_berlin.ise.open_data.library.service.JsonSchemaCreator;
import org.json.JSONException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by ahmadjawid on 7/1/17.
 * Processing includes converting Java Objects to json string objects according our defined schema
 */

public class AirQualityItemProcessor implements ItemProcessor<AirQuality, String> {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private JsonSchemaCreator jsonSchemaCreator;

    @Override

    public String process(AirQuality item) throws JSONException {

        LocalDate yesterday = LocalDate.now().minusDays(1);


        //Set isUTC as true if you want the time to be considered as UTC time
        item.setTimestamp(applicationService.toISODateTimeFormat(yesterday.toString(), "22:00:00", true));

        //A valid json objects is created and returned
        return jsonSchemaCreator.create(item);
    }
}
