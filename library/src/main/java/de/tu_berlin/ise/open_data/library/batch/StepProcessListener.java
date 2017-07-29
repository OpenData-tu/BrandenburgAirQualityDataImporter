package de.tu_berlin.ise.open_data.library.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

/**
 * Created by ahmadjawid on 7/28/17.
 */
@Component
public class StepProcessListener implements StepExecutionListener, ChunkListener {

    //this is step process listener

    private static final Logger LOGGER = LoggerFactory.getLogger(StepProcessListener.class);
    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        LOGGER.info(stepExecution.getSummary());
        return null;
    }

    @Override
    public void beforeChunk(ChunkContext context) {

        LOGGER.info("Processing next chunk...");

    }

    @Override
    public void afterChunk(ChunkContext context) {

        LOGGER.info(context.getStepContext().getStepExecution().getSummary());
    }

    @Override
    public void afterChunkError(ChunkContext context) {

    }
}
