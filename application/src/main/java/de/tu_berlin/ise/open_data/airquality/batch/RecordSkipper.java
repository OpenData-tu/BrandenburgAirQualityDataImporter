package de.tu_berlin.ise.open_data.airquality.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.excel.ExcelFileParseException;

import java.io.FileNotFoundException;

/**
 * Created by ahmadjawid on 7/1/17.
 *A defined amount of records could be skipped when a particular exception occurs
 */
public class RecordSkipper implements SkipPolicy {

    private static final Logger logger = LoggerFactory.getLogger("badRecordLogger");

    @Override
    public boolean shouldSkip(Throwable exception, int skipCount) throws SkipLimitExceededException {
        //Do not skip if exception is instance of FileNotFoundException
        if (exception instanceof FileNotFoundException) {
            return false;

            //If exception is instance of ExcelFileParseException and skip limit is not reached
            //log an error
        } else if (exception instanceof ExcelFileParseException && skipCount <= 5) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("An error occurred while processing");
            errorMessage.append( "\n");
            //log an error and continue
            logger.error("{}", errorMessage.toString());
            return true;
        } else {
            return false;
        }
    }

}
