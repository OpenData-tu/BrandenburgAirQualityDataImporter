package de.tu_berlin.open_data.airquality.brandenburgairqualitydata.service;

import de.tu_berlin.open_data.airquality.brandenburgairqualitydata.model.Schema;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Created by ahmadjawid on 6/10/17.
 */

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Override
    public float parseToFloat(String number) {

        try {
            return Float.parseFloat(number);
        } catch (NumberFormatException e) {
            return -999999;
        }

    }
    @Override
    public String toISODateFormat(String date) {
        return date + "T22:00:00+02:00";
    }
}
