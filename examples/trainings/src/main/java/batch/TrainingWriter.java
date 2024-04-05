package batch;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import entities.TrainingEntity;
import jakarta.batch.api.chunk.AbstractItemWriter;
import jakarta.batch.runtime.context.JobContext;
import jakarta.batch.runtime.context.StepContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import services.TrainingService;

@Named(value = "trainingWriter")
public class TrainingWriter extends AbstractItemWriter {

    static final Logger LOG = Logger.getLogger("TrainingWriter");

    @Inject
    private JobContext jobContext;

    @Inject
    private TrainingService trainingService;

    @Inject
    private StepContext stepContext;

    @Override
    public void writeItems(List list) {
        LOG.info("Write batch output");
        String resourceName = (String) stepContext.getProperties().get("output_file");
                Jsonb jsonb = JsonbBuilder.create();
        try (PrintWriter pw = new PrintWriter(new FileWriter(resourceName, true))) {
            for (Object t : list) {
                t = (TrainingEntity) t;
                trainingService.updateByTitle((TrainingEntity) t);
                String itemAsJSON = jsonb.toJson(t, TrainingEntity.class);
                LOG.info("write element " + itemAsJSON);
                pw.write(itemAsJSON);
            }
            pw.flush();
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}