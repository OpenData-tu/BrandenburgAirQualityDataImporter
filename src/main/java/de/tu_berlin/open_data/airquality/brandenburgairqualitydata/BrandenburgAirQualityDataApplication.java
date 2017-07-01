package de.tu_berlin.open_data.airquality.brandenburgairqualitydata;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.repository.TaskExecution;

import java.io.*;

@SpringBootApplication
@EnableTask
public class BrandenburgAirQualityDataApplication {


    public void init() throws IOException, InvalidFormatException {

        File file = new File("Montag.xls");
			Workbook wb = WorkbookFactory.create(file);
			Sheet sheet = wb.getSheetAt(0);
//
//        FileInputStream fis = new FileInputStream(file);
//        HSSFWorkbook wb = new HSSFWorkbook(fis);
//
//        HSSFSheet sheet = wb.getSheetAt(0);

        while (sheet.getNumMergedRegions() > 0){

            sheet.removeMergedRegion(0);

        }

        removeRow(sheet, 0);
        removeRow(sheet, 0);
        removeRow(sheet, 0);
        removeRow(sheet, 0);

        File outWB = new File("Montag-work.xls");
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
