package de.tu_berlin.ise.open_data.airquality.config;

import de.tu_berlin.ise.open_data.airquality.util.NumberToGermanDaysOfWeek;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * Created by ahmadjawid on 6/30/17.
 *Properties of resource are provided here.
 * These values are overridden by application.properties overridden by environment variables
 */

@Configuration
@ConfigurationProperties("resource")
public class ResourceProperties {

    private String url;

    public ResourceProperties() {

        setUrl(getPreviousDayUrl());
    }

    public String getUrl() {

        if (url == null) {
            setUrl(getPreviousDayUrl());
        }

        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Generate the url for importing data of last day
     * */
    private String getPreviousDayUrl() {

        LocalDate localDate = LocalDate.now();

        //Manually set the time (this is default to our importers which do not have time)
        LocalTime localTime = LocalTime.of(22, 00, 00);

        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        int dayOfWeek = localDateTime.getDayOfWeek().getValue();


        //Properly set the day of week
        dayOfWeek--;
        if (dayOfWeek <= 0) {
            dayOfWeek = 7;
        }
        //Generate url for the last day according to the day of week
        String url = "https://luftdaten.brandenburg.de/home/-/bereich/datenexport/" + NumberToGermanDaysOfWeek.dayNumberToGermanDayOfWeek.get(dayOfWeek) + ".xls";

        return url;
    }

}
