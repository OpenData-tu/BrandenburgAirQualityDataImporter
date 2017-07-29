package de.tu_berlin.ise.open_data.airquality.util;

import de.tu_berlin.ise.open_data.airquality.model.Location;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rohullah on 05.07.17.
 */
public class LocationToCoordinates {

    public static final Map<String, Location> locationNamesToCoordinates;

    static {
        HashMap<String, Location> locationDictionary = new HashMap<>();

        locationDictionary.put("Cottbus", new Location(51.74634, 14.33455));
        locationDictionary.put("Blankenfelde-Mahlow", new Location(52.34970, 13.42431));
        locationDictionary.put("Brandenburg a.d.Havel", new Location(52.42044, 112.55230));
        locationDictionary.put("Eisenhüttenstadt", new Location(52.14626, 14.63817));
        locationDictionary.put("Elsterwerda", new Location(51.46273, 13.52679));
        locationDictionary.put("Frankfurt (Oder)", new Location(52.33467, 14.52596));
        locationDictionary.put("Hasenholz (Buckow) +", new Location(52.56383, 14.01525));
        locationDictionary.put("Lütte (Belzig) +", new Location(52.19422, 12.56139));
        locationDictionary.put("Nauen", new Location(52.60861, 12.88519));
        locationDictionary.put("Neuruppin", new Location(52.93189, 12.80948));
        locationDictionary.put("Potsdam, Groß Glienicke", new Location(52.48417, 13.12370));
        locationDictionary.put("Potsdam-Zentrum", new Location(52.40135, 13.05993));
        locationDictionary.put("Schönefeld, Flughafen*", new Location(52.37679, 13.53263));
        locationDictionary.put("Schwedt/Oder", new Location(53.06426, 14.28514));
        locationDictionary.put("Spreewald +", new Location(51.89748, 14.05689));
        locationDictionary.put("Spremberg", new Location(51.56445, 14.37644));
        locationDictionary.put("Wittenberge", new Location(52.99875, 11.73846));
        locationDictionary.put("Bernau, Lohmühlenstr. v", new Location(52.67765, 13.58227));
        locationDictionary.put("Brandenburg,Neuendorfer Str. v", new Location(52.40931, 12.54838));
        locationDictionary.put("Cottbus, Bahnhofstr. v", new Location(51.75648, 14.32800));
        locationDictionary.put("Eberswalde, Breite Str. v", new Location(52.83247, 13.82221));
        locationDictionary.put("Herzfelde, Hauptstr. v", new Location(52.48188,13.84825));
        locationDictionary.put("Lütte (Belzig) +", new Location(52.19422, 12.56139));
        locationDictionary.put("Potsdam, Großbeerenstr. v", new Location(52.38887, 13.09462));
        locationDictionary.put("Potsdam, Zeppelinstr. v", new Location(52.39403, 13.03785));
        locationDictionary.put("Frankfurt(O), Leipziger Str. v", new Location(52.33734, 14.53859));
        locationDictionary.put("Cottbus, W.-Külz-Str. v", new Location(51.75413, 14.32552));
        locationDictionary.put("Premnitz, OT Döberitz", new Location(52.52401, 12.37016));

        locationNamesToCoordinates = Collections.unmodifiableMap(locationDictionary);
    }
}