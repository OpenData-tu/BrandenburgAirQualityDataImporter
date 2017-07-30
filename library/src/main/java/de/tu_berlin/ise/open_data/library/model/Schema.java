package de.tu_berlin.ise.open_data.library.model;

/**
 * Created by ahmadjawid on 7/12/17.
 * All model classes of importers which are used to convert source data to Java Objects extends this class
 */
public abstract class Schema {

    /**
     * Forces the extender to provide the delimiter in case of flat files (csv, tsv, etc)
     * @return String
     * */
    public abstract String getDelimiter();
}
