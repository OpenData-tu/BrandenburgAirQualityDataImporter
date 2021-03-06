package de.tu_berlin.ise.open_data.airquality.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ahmadjawid on 7/2/17.
 * Mapping of day of week as number to its corresponding word
 */
public class NumberToGermanDaysOfWeek {

    public static final Map<Integer, String> dayNumberToGermanDayOfWeek;

    static {
        Map<Integer, String> aMap = new HashMap<>();

        aMap.put(1, "Montag");
        aMap.put(2, "Dienstag");
        aMap.put(3, "Mittwoch");
        aMap.put(4, "Donnerstag");
        aMap.put(5, "Freitag");
        aMap.put(6, "Samstag");
        aMap.put(7, "Sonntag");
        dayNumberToGermanDayOfWeek = Collections.unmodifiableMap(aMap);
    }

}
