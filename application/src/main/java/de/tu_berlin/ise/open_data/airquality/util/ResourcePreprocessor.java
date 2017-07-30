package de.tu_berlin.ise.open_data.airquality.util;

import de.tu_berlin.ise.open_data.airquality.config.ResourceProperties;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * Created by ahmadjawid on 7/29/17.
 * Pre-process excel file and make it ready for parsing
 */

@Component
public class ResourcePreprocessor {

    private File processedFile;

    @Autowired
    private ResourceProperties resourceProperties;



    public File getProcessedFile() {
        return processedFile;
    }

    public void setProcessedFile(File processedFile) {
        this.processedFile = processedFile;
    }

    public ResourcePreprocessor preProcess() throws IOException, InvalidFormatException {

        URL url = new URL(resourceProperties.getUrl());
        InputStream inputStream = url.openStream();
        Workbook wb = WorkbookFactory.create(inputStream);
        Sheet sheet = wb.getSheetAt(0);

        //Removed all cells which are merged and split them
        while (sheet.getNumMergedRegions() > 0) {

            sheet.removeMergedRegion(0);

        }

        //First four lines does not carry usable information
        removeRow(sheet, 0);
        removeRow(sheet, 0);
        removeRow(sheet, 0);
        removeRow(sheet, 0);

        //Create new file
        processedFile = new File("source.xls");
        //Prepare for writing
        OutputStream out = new FileOutputStream(processedFile);

        //Write cleaned usable data to file
        wb.write(out);
        out.flush();
        out.close();

        return this;

    }

    /**
     * Row a particular row from an excel sheet
     * @param sheet
     * @param rowIndex
     * */
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
