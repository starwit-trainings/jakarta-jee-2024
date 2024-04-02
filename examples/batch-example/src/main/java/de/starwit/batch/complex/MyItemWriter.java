package de.starwit.batch.complex;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.starwit.batch.model.Training;
import jakarta.batch.api.chunk.AbstractItemWriter;
import jakarta.batch.runtime.context.JobContext;
import jakarta.batch.runtime.context.StepContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named(value = "myItemWriter")
public class MyItemWriter extends AbstractItemWriter {

    static final Logger LOG = LoggerFactory.getLogger(MyItemWriter.class);

    @Inject
    private JobContext jobContext;

    @Inject
    private StepContext stepContext;

    @Override
    public void writeItems(List list) {
        LOG.info("Write batch output");
        String resourceName = (String) stepContext.getProperties().get("output_file");

        ObjectMapper objectMapper = new ObjectMapper();
        try (PrintWriter pw = new PrintWriter(new FileWriter(resourceName, true))) {

            for (Object t : list) {
                t = (Training) t;
                String itemAsJSON = objectMapper.writeValueAsString(t);
                LOG.debug("write element " + itemAsJSON);
                pw.write(itemAsJSON);
            }
            pw.flush();
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}