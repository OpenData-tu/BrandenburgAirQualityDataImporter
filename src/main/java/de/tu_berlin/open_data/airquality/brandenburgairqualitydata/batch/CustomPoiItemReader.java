package de.tu_berlin.open_data.airquality.brandenburgairqualitydata.batch;

import de.tu_berlin.open_data.airquality.brandenburgairqualitydata.model.AirQuality;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.excel.poi.PoiItemReader;

/**
 * Created by ahmadjawid on 7/1/17.
 */
public class CustomPoiItemReader extends PoiItemReader<AirQuality> {


    public CustomPoiItemReader(){
       // this.setLinesToSkip(1);
    }

    @Override
    public AirQuality read() throws Exception {

        AirQuality airQuality = super.read();
        try {
            if (airQuality.getMeasurementLocation().equals(" ") || airQuality.getMeasurementLocation().equals("")) {
                return null;

            }
        } catch (NullPointerException e) {
            return null;
        }

        return airQuality;
    }
}
