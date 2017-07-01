package de.tu_berlin.open_data.airquality.brandenburgairqualitydata.model;

/**
 * Created by ahmadjawid on 6/29/17.
 */
public class AirQuality extends Schema {


    private String measurementLocation;
    private String NO2DailyAverage;
    private String NO2Max1hAverage;
    private String NODailyAverage;
    private String NOMax1hAverage;
    private String CODailyAverage;
    private String COMax8hAverage;
    private String fineDustPM10DailyAverage;
    private String fineDustPM10Max1hAverage;
    private String fineDustPM25DailyAverage;
    private String fineDustPM25Max1hAverage;
    private String SO2DailyAverage;
    private String SO2Max1hAverage;
    private String O3DailyAverage;
    private String O3Max8hAverage;
    private String timestamp;

    public String getMeasurementLocation() {
        return measurementLocation;
    }

    public void setMeasurementLocation(String measurementLocation) {
        this.measurementLocation = measurementLocation;
    }

    public String getNO2DailyAverage() {
        return NO2DailyAverage;
    }

    public void setNO2DailyAverage(String NO2DailyAverage) {
        this.NO2DailyAverage = NO2DailyAverage;
    }

    public String getNO2Max1hAverage() {
        return NO2Max1hAverage;
    }

    public void setNO2Max1hAverage(String NO2Max1hAverage) {
        this.NO2Max1hAverage = NO2Max1hAverage;
    }

    public String getNODailyAverage() {
        return NODailyAverage;
    }

    public void setNODailyAverage(String NODailyAverage) {
        this.NODailyAverage = NODailyAverage;
    }

    public String getNOMax1hAverage() {
        return NOMax1hAverage;
    }

    public void setNOMax1hAverage(String NOMax1hAverage) {
        this.NOMax1hAverage = NOMax1hAverage;
    }

    public String getCODailyAverage() {
        return CODailyAverage;
    }

    public void setCODailyAverage(String CODailyAverage) {
        this.CODailyAverage = CODailyAverage;
    }

    public String getCOMax8hAverage() {
        return COMax8hAverage;
    }

    public void setCOMax8hAverage(String COMax8hAverage) {
        this.COMax8hAverage = COMax8hAverage;
    }

    public String getFineDustPM10DailyAverage() {
        return fineDustPM10DailyAverage;
    }

    public void setFineDustPM10DailyAverage(String fineDustPM10DailyAverage) {
        this.fineDustPM10DailyAverage = fineDustPM10DailyAverage;
    }

    public String getFineDustPM10Max1hAverage() {
        return fineDustPM10Max1hAverage;
    }

    public void setFineDustPM10Max1hAverage(String fineDustPM10Max1hAverage) {
        this.fineDustPM10Max1hAverage = fineDustPM10Max1hAverage;
    }

    public String getFineDustPM25DailyAverage() {
        return fineDustPM25DailyAverage;
    }

    public void setFineDustPM25DailyAverage(String fineDustPM25DailyAverage) {
        this.fineDustPM25DailyAverage = fineDustPM25DailyAverage;
    }

    public String getFineDustPM25Max1hAverage() {
        return fineDustPM25Max1hAverage;
    }

    public void setFineDustPM25Max1hAverage(String fineDustPM25Max1hAverage) {
        this.fineDustPM25Max1hAverage = fineDustPM25Max1hAverage;
    }

    public String getSO2DailyAverage() {
        return SO2DailyAverage;
    }

    public void setSO2DailyAverage(String SO2DailyAverage) {
        this.SO2DailyAverage = SO2DailyAverage;
    }

    public String getSO2Max1hAverage() {
        return SO2Max1hAverage;
    }

    public void setSO2Max1hAverage(String SO2Max1hAverage) {
        this.SO2Max1hAverage = SO2Max1hAverage;
    }

    public String getO3DailyAverage() {
        return O3DailyAverage;
    }

    public void setO3DailyAverage(String o3DailyAverage) {
        O3DailyAverage = o3DailyAverage;
    }

    public String getO3Max8hAverage() {
        return O3Max8hAverage;
    }

    public void setO3Max8hAverage(String o3Max8hAverage) {
        O3Max8hAverage = o3Max8hAverage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String getDelimiter() {
        return null;
    }
}
