package de.tu_berlin.ise.open_data.library.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

/**
 * Created by ahmadjawid on 7/28/17.
 * Object of this class can be passed to job steps to keep track of processing steps and chunks
 */
@Component
public class StepProcessListener implements StepExecutionListener, ChunkListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(StepProcessListener.class);


    /**
     * Automatically called before step begins
     * @param stepExecution
     * */
    @Override
    public void beforeStep(StepExecution stepExecution) {

    }


    /**
     * Automatically called after step finishes
     * @param stepExecution
     * */
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        LOGGER.info(stepExecution.getSummary());

        //Return null to indicate that step did not fail
        return null;
    }

    /**
     * Automatically called before processing next chunk
     * @param context
     * */
    @Override
    public void beforeChunk(ChunkContext context) {

        LOGGER.info("Processing next chunk...");

    }


    /**
     * Automatically called after processing the chunk
     * @param context
     * */
    @Override
    public void afterChunk(ChunkContext context) {

        LOGGER.info(context.getStepContext().getStepExecution().getSummary());
    }

    /**
     * Automatically called if an error occurs while processing the chunk
     * @param context
     * */
    @Override
    public void afterChunkError(ChunkContext context) {

    }
}
