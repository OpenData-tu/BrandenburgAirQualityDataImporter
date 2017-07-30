package de.tu_berlin.ise.open_data.library.service;

import de.tu_berlin.ise.open_data.library.model.Schema;
import org.joda.time.DateTime;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmadjawid on 5/21/17.
 * All {@link ApplicationService} services are implemented here
 */

@Component
public class ApplicationServiceImpl implements ApplicationService {


    /**
     * Is used to returns non final fields of the model class which is used to map source data to Java Objects
     * @return String[]
     * */
    @Override
    public String[] getFields(Class<? extends Object> aClass) {

        Field[] aClassDeclaredFields = aClass.getDeclaredFields();

        List<String> fields = new ArrayList<>();

        for (Field field : aClassDeclaredFields) {

            //Final fields are not needed for source data to object mapping (as the names says they are final)
            if (Modifier.isFinal(field.getModifiers()))
                continue;

            fields.add(field.getName());
        }

        return fields.toArray(new String[fields.size()]);
    }

    @Override
    public Double parseToDouble(String number) {

        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            return null;
        }

    }

    @Override
    public String toISODateTimeFormat(String date) {

        DateTime dateTime = new DateTime(date);

        return dateTime.toDateTimeISO().toString();
    }


    /**
     * Covert to ISO DateTime format
     * @param localDate
     * @param localTime
     * @param isUTC should be 'true' if to consider the param 'localTime' as UTC
     * @return String
     * */
    @Override
    public String toISODateTimeFormat(String localDate, String localTime, boolean isUTC) {

        //Create DateTime objects considering whether to create UTC DateTime or LocalDateTime
        DateTime dateTime = new DateTime((isUTC ? localDate + "T" + localTime + "Z" : localDate + "T" + localTime));

        return dateTime.toDateTimeISO().toString();
    }


    /**
     * Convert to ISO DateTime format.
     * @param localDate
     * @param localTime
     * @param zoneOffsetHours offset hours compared to UTC DateTime.
     * */
    @Override
    public String toISODateTimeFormat(String localDate, String localTime, int zoneOffsetHours) throws IllegalArgumentException {

        DateTime dateTime = new DateTime(localDate + "T" + localTime + ZoneOffset.ofHours(zoneOffsetHours));
        return dateTime.toDateTimeISO().toString();
    }

    @Override
    public String toISODateTimeFormat(LocalDate localDate) {


        //Default time used by some of our importers if no time is provided.
        LocalTime localTime = LocalTime.of(22, 00, 00);

        DateTime dateTime = new DateTime(localDate.toString() + "T" + localTime);

        return dateTime.toDateTimeISO().toString();
    }


    /**
     * Covert to ISO DateTime format
     * @param localDate
     * @param localTime
     * @param isUTC should be 'true' if to consider the param 'localTime' as UTC
     * @return String
     * */
    @Override
    public String toISODateTimeFormat(LocalDate localDate, LocalTime localTime, boolean isUTC) {

        //Create DateTime objects considering whether to create UTC DateTime or LocalDateTime
        DateTime dateTime = new DateTime((isUTC ? localDate + "T" + localTime + "Z" : localDate + "T" + localTime));

        return dateTime.toDateTimeISO().toString();
    }

    @Override
    public String toISODateTimeFormat(LocalDate localDate, LocalTime localTime, ZoneOffset zoneOffset) {


        LocalDateTime localDateTime = LocalDateTime.parse(localDate.toString() + "T" + localTime.toString() + zoneOffset.toString());

        DateTime dateTime = new DateTime(localDateTime);


        return dateTime.toDateTimeISO().toString();
    }

    /**
     * Convert to ISO DateTime format.
     * @param localDate
     * @param localTime
     * @param zoneOffsetHours offset hours compared to UTC DateTime.
     * */
    @Override
    public String toISODateTimeFormat(LocalDate localDate, LocalTime localTime, int zoneOffsetHours) {
        LocalDateTime localDateTime = LocalDateTime.parse(localDate.toString() + "T" + localTime.toString() + ZoneOffset.ofHours(zoneOffsetHours));

        DateTime dateTime = new DateTime(localDateTime);


        return dateTime.toDateTimeISO().toString();
    }

    @Override
    public String toISODateTimeFormat(LocalDateTime localDateTime) {

        DateTime dateTime = new DateTime(localDateTime);

        return dateTime.toDateTimeISO().toString();
    }


    /**
     * Is used to automatically translate rows of a flat file to Java Objects
     * @param aClass
     * @return LineMapper
     * */
    @Override
    public LineMapper createLineMapper(Class<? extends Schema> aClass) throws IllegalAccessException, InstantiationException {
        return new DefaultLineMapper<Schema>() {{
            //Specify how to tokenize each column of the source file (comma, semicolon, etc)
            setLineTokenizer(new DelimitedLineTokenizer(aClass.newInstance().getDelimiter()) {{

                //Get the fields fo the class set them to map rows of source data to Java Objects
                setNames(getFields(aClass));
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Schema>() {{
                setTargetType(aClass);
            }});
        }};
    }
}
