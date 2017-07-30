package de.tu_berlin.ise.open_data.library.service;

import de.tu_berlin.ise.open_data.library.model.Schema;
import org.springframework.batch.item.file.LineMapper;

import java.time.*;

/**
 * Created by ahmadjawid on 7/12/17.
 * Re-usable services which could be used by the importer
 */
public interface ApplicationService {


    String[] getFields(Class<? extends Object> aClass);

    Double parseToDouble(String number);

    String toISODateTimeFormat(String date) throws IllegalArgumentException;

    /**
     * Covert to ISO DateTime format
     * @param localDate
     * @param localTime
     * @param isUTC should be 'true' if to consider the param 'localTime' as UTC
     * @return String
     * */
    String toISODateTimeFormat(String localDate, String localTime, boolean isUTC) throws IllegalArgumentException;


    /**
     * Convert to ISO DateTime format.
     * @param localDate
     * @param localTime
     * @param zoneOffsetHours offset hours compared to UTC DateTime.
     * */
    String toISODateTimeFormat(String localDate, String localTime, int zoneOffsetHours) throws IllegalArgumentException;

    String toISODateTimeFormat(LocalDate localDate);

    /**
     * Covert to ISO DateTime format
     * @param localDate
     * @param localTime
     * @param isUTC should be 'true' if to consider the param 'localTime' as UTC
     * @return String
     * */
    String toISODateTimeFormat(LocalDate localDate, LocalTime localTime, boolean isUTC);

    String toISODateTimeFormat(LocalDate localDate, LocalTime localTime, ZoneOffset zoneOffset);


    /**
     * Convert to ISO DateTime format.
     * @param localDate
     * @param localTime
     * @param zoneOffsetHours offset hours compared to UTC DateTime.
     * */
    String toISODateTimeFormat(LocalDate localDate, LocalTime localTime, int zoneOffsetHours);


    String toISODateTimeFormat(LocalDateTime localDateTime);

    /**
     * Is used to automatically translate rows of a flat file to Java Objects
     * @param aClass
     * @return LineMapper
     * */
    LineMapper createLineMapper(Class<? extends Schema> aClass) throws IllegalAccessException, InstantiationException;


}
