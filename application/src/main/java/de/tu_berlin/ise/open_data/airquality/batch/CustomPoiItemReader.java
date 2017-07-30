package de.tu_berlin.ise.open_data.airquality.batch;

import de.tu_berlin.ise.open_data.airquality.model.AirQuality;
import org.springframework.batch.item.excel.poi.PoiItemReader;

/**
 * Created by ahmadjawid on 7/1/17.
 * Reader which specifies how to read from the source
 *
 */
public class CustomPoiItemReader extends PoiItemReader<AirQuality> {


    /**
     * Read the items one by one and return them.
     * Stop when null is returned
     * @return AirQuality
     * */
    @Override
    public AirQuality read() throws Exception {

        AirQuality airQuality = super.read();
        try {

            //Stop if reading empty line
            if (airQuality.getMeasurementLocation().equals(" ") || airQuality.getMeasurementLocation().equals("")) {
                return null;

            }
        } catch (NullPointerException e) {
            return null;
        }

        return airQuality;
    }
}
