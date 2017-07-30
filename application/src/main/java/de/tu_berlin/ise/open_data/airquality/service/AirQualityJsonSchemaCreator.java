package de.tu_berlin.ise.open_data.airquality.service;

import de.tu_berlin.ise.open_data.airquality.batch.AirQualityItemProcessor;
import de.tu_berlin.ise.open_data.airquality.model.AirQuality;
import de.tu_berlin.ise.open_data.airquality.model.Location;
import de.tu_berlin.ise.open_data.airquality.util.LocationToCoordinates;
import de.tu_berlin.ise.open_data.library.model.Schema;
import de.tu_berlin.ise.open_data.library.service.JsonSchemaCreator;
import de.tu_berlin.ise.open_data.library.service.JsonStringBuilder;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * Created by ahmadjawid on 6/9/17.
 * Implementation of {@link JsonSchemaCreator}
 */
@Service
public class AirQualityJsonSchemaCreator implements JsonSchemaCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirQualityItemProcessor.class);


    /**
     * Get an objects which is extended from {@link Schema} class
     * and converts it to json
     * @param schema
     * @return String
     * */
    @Override
    public String create(Schema schema) throws JSONException {
        AirQuality item = (AirQuality) schema;

        JsonStringBuilder jsonBuilder = new JsonStringBuilder();

        //Manually map location names to an Object of Location
        Location locationCoordinates = LocationToCoordinates.locationNamesToCoordinates.get(item.getMeasurementLocation());

        jsonBuilder.setSourceId(item.getSourceId());
        jsonBuilder.setDevice(item.getMeasurementLocation());
        jsonBuilder.setTimestamp(item.getTimestamp());

        if (locationCoordinates != null)
            jsonBuilder.setLocation(locationCoordinates.getLat() + "", locationCoordinates.getLon() + "");
        else
            LOGGER.error("Couldn't map location to coordinates for '" + item.getMeasurementLocation() + "'");


        jsonBuilder.setLicense(item.getLicense());

        //Set sensors
        jsonBuilder.setSensor("NO2DailyAverage", "Nitrogen dioxide (NO₂)", item.getNO2DailyAverage());
        jsonBuilder.setSensor("NO2Max1hAverage", "Nitrogen dioxide (NO₂)", item.getNO2Max1hAverage());
        jsonBuilder.setSensor("NODailyAverage", "Nitric oxide (NO)", item.getNODailyAverage());
        jsonBuilder.setSensor("NOMax1hAverage", "Nitric oxide (NO)", item.getNOMax1hAverage());
        jsonBuilder.setSensor("CODailyAverage", "Carbon monoxide (CO)", item.getCODailyAverage());
        jsonBuilder.setSensor("COMax8hAverage", "Carbon monoxide (CO)", item.getCOMax8hAverage());
        jsonBuilder.setSensor("FineDustPM10DailyAverage", "Fine dust (PM10)", item.getFineDustPM10DailyAverage());
        jsonBuilder.setSensor("FineDustPM10Max1hAverage", "Fine dust (PM10)", item.getFineDustPM10Max1hAverage());
        jsonBuilder.setSensor("FineDustPM25DailyAverage", "Fine dust (PM2.5)", item.getFineDustPM25DailyAverage());
        jsonBuilder.setSensor("FineDustPM25Max1hAverage", "Fine dust (PM2.5)", item.getFineDustPM25Max1hAverage());
        jsonBuilder.setSensor("SO2DailyAverage", "Sulfur dioxide (SO₂)", item.getSO2DailyAverage());
        jsonBuilder.setSensor("SO2Max1hAverage", "Sulfur dioxide (SO₂)", item.getSO2Max1hAverage());
        jsonBuilder.setSensor("O3DailyAverage", "Ozone (O₃)", item.getO3DailyAverage());
        jsonBuilder.setSensor("O3Max8hAverage", "Ozone (O₃)", item.getO3Max8hAverage());
        jsonBuilder.setSensor("NO2DailyAverage", "Nitrogen dioxide (NO₂)", item.getNO2DailyAverage());
        jsonBuilder.setSensor("NO2DailyAverage", "Nitrogen dioxide (NO₂)", item.getNO2DailyAverage());


        return jsonBuilder.build();


    }
}
