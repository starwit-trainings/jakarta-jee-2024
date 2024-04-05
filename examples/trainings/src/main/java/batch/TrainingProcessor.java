package batch;

import java.util.logging.Logger;

import entities.TrainingEntity;
import jakarta.inject.Named;

@Named(value = "trainingProcessor")
public class TrainingProcessor implements jakarta.batch.api.chunk.ItemProcessor {

    static final Logger LOG = Logger.getLogger("TrainingProcessor");
   
    @Override
    public TrainingEntity processItem(Object t) {
        LOG.info("Process batch item");       
        return parseLine((String) t);
    }

    private TrainingEntity parseLine(String line) {
        String[] x = line.split(",");
        TrainingEntity training = new TrainingEntity(
            x[0], 
            x[1], 
            Integer.parseInt(x[2].trim()), 
            x[3], Integer.parseInt(x[4].trim()), 
            Integer.parseInt(x[5].trim()));
        return training;
    }    
}
