package de.tu_berlin.open_data.airquality.brandenburgairqualitydata.service;

import de.tu_berlin.open_data.airquality.brandenburgairqualitydata.model.Schema;
import org.springframework.batch.item.file.LineMapper;

/**
 * Created by ahmadjawid on 6/10/17.
 */
public interface ApplicationService {
    float parseToFloat(String number);
    String toISODateFormat(String date);

}
