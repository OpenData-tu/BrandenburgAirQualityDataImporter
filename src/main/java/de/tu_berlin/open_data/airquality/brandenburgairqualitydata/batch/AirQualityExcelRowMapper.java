package de.tu_berlin.open_data.airquality.brandenburgairqualitydata.batch;

import de.tu_berlin.open_data.airquality.brandenburgairqualitydata.model.AirQuality;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

/**
 * Created by ahmadjawid on 7/1/17.
 */
public class AirQualityExcelRowMapper implements RowMapper<AirQuality> {

    @Override
    public AirQuality mapRow(RowSet rowSet) throws Exception {

        AirQuality airQuality = new AirQuality();

//        for (String current: rowSet.getCurrentRow()){
//            System.out.println(current);
//            System.out.println("this is it");
//        }

        airQuality.setMeasurementLocation(rowSet.getColumnValue(0));
        airQuality.setNO2DailyAverage(rowSet.getColumnValue(1));
        airQuality.setNO2Max1hAverage(rowSet.getColumnValue(2));
        airQuality.setNODailyAverage(rowSet.getColumnValue(3));
        airQuality.setNOMax1hAverage(rowSet.getColumnValue(4));
        airQuality.setCODailyAverage(rowSet.getColumnValue(5));
        airQuality.setCOMax8hAverage(rowSet.getColumnValue(6));
        airQuality.setFineDustPM10DailyAverage(rowSet.getColumnValue(7));
        airQuality.setFineDustPM10Max1hAverage(rowSet.getColumnValue(8));
        airQuality.setFineDustPM25DailyAverage(rowSet.getColumnValue(9));
        airQuality.setFineDustPM25Max1hAverage(rowSet.getColumnValue(10));
        airQuality.setSO2DailyAverage(rowSet.getColumnValue(11));
        airQuality.setSO2Max1hAverage(rowSet.getColumnValue(12));
        airQuality.setO3DailyAverage(rowSet.getColumnValue(13));
        airQuality.setO3Max8hAverage(rowSet.getColumnValue(14));

        return airQuality;
    }
}
