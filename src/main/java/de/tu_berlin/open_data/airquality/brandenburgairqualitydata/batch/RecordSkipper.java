package de.tu_berlin.open_data.airquality.brandenburgairqualitydata.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.excel.ExcelFileParseException;

import java.io.FileNotFoundException;

/**
 * Created by ahmadjawid on 7/1/17.
 */
public class RecordSkipper implements SkipPolicy {

    private static final Logger logger = LoggerFactory.getLogger("badRecordLogger");

    @Override
    public boolean shouldSkip(Throwable exception, int skipCount) throws SkipLimitExceededException {
        if (exception instanceof FileNotFoundException) {
            return false;
        } else if (exception instanceof ExcelFileParseException && skipCount <= 5) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("An error occured while processing");
            errorMessage.append( "\n");
            logger.error("{}", errorMessage.toString());
            return true;
        } else {
            return false;
        }
    }

}
