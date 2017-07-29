package de.tu_berlin.ise.open_data.airquality.service;

import org.springframework.stereotype.Service;

/**
 * Created by ahmadjawid on 6/10/17.
 */

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Override
    public Double parseToDouble(String number) {

        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            return null;
        }

    }
    @Override
    public String toISODateFormat(String date) {
        return date + "T22:00:00+02:00";
    }
}
