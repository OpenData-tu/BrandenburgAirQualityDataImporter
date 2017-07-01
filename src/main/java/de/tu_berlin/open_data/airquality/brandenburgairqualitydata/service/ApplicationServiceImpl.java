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
    public String[] getFields(Class<? extends Object> aClass) {

        Field[] aClassDeclaredFields = aClass.getDeclaredFields();

        String[] fieldsArray = new String[aClassDeclaredFields.length];

        for (int index = 0; index < aClassDeclaredFields.length; index++)
            fieldsArray[index] = aClassDeclaredFields[index].getName();

        return fieldsArray;
    }

    @Override
    public float parseToFloat(String number) {

        try {
            return Float.parseFloat(number);
        } catch (NumberFormatException e) {
            return 0;
        }

    }

    @Override
    public String toISODateFormat(String date) {
        return date + "Z";
    }

    @Override
    public LineMapper createLineMapper(Class<? extends Schema> aClass, Schema userModelInstance) {
        return new DefaultLineMapper<Schema>() {{
            setLineTokenizer(new DelimitedLineTokenizer(userModelInstance.getDelimiter()) {{
                setNames(getFields(aClass));
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Schema>() {{
                setTargetType(userModelInstance.getClass());
            }});
        }};
    }
}
