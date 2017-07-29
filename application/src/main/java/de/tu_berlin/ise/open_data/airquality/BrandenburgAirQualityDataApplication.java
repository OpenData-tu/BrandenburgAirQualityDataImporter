package de.tu_berlin.ise.open_data.airquality;

import de.tu_berlin.ise.open_data.airquality.util.NumberToGermanDaysOfWeek;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * Created by ahmadjawid on 7/1/17.
 */

@SpringBootApplication
@EnableTask
public class BrandenburgAirQualityDataApplication {


    public void init() throws IOException, InvalidFormatException {

        LocalDateTime localDateTime = LocalDateTime.of(2017, 07, 04, 22, 00, 00);
        System.out.println(localDateTime.toString());
        LocalTime localTime = LocalTime.now();
        System.out.println(localDateTime.toEpochSecond(ZoneOffset.UTC));

        int dayOfWeek = localDateTime.getDayOfWeek().getValue();

        dayOfWeek--;
        if (dayOfWeek <= 0){
            dayOfWeek = 7;
        }
        URL url = new URL("https://luftdaten.brandenburg.de/home/-/bereich/datenexport/" + NumberToGermanDaysOfWeek.dayNumberToGermanDayOfWeek.get(dayOfWeek) + ".xls");
        //TODO correct it. problem -> on Friday it was importing Monday
        System.out.println("Importing data for : " + NumberToGermanDaysOfWeek.dayNumberToGermanDayOfWeek.get(dayOfWeek));
       // System.out.println(url.getPath());

        InputStream inputStream = url.openStream();
			Workbook wb = WorkbookFactory.create(inputStream);
			Sheet sheet = wb.getSheetAt(0);

        while (sheet.getNumMergedRegions() > 0){

            sheet.removeMergedRegion(0);

        }

        removeRow(sheet, 0);
        removeRow(sheet, 0);
        removeRow(sheet, 0);
        removeRow(sheet, 0);

        File outWB = new File("source.xls");
        OutputStream out = new FileOutputStream(outWB);
       // fis.close();
        wb.write(out);
        out.flush();
        out.close();

    }

    public static void main(String[] args) throws IOException {
        try {
            new BrandenburgAirQualityDataApplication().init();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        SpringApplication.run(BrandenburgAirQualityDataApplication.class, args);
    }


    public void removeRow(Sheet sheet, int rowIndex) {
		int lastRowNum = sheet.getLastRowNum();
		if (rowIndex >= 0 && rowIndex < lastRowNum) {
			sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
		}
if (rowIndex == lastRowNum) {
        Row removingRow = sheet.getRow(rowIndex);
        	if (removingRow != null) {
        sheet.removeRow(removingRow);
        }
        }
    }

}
