package de.starwit.batch.complex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.starwit.batch.model.Training;
import jakarta.inject.Named;

@Named(value = "myItemProcessor")
public class MyItemProcessor implements jakarta.batch.api.chunk.ItemProcessor {

    static final Logger LOG = LoggerFactory.getLogger(MyItemProcessor.class);
   
    @Override
    public Training processItem(Object t) {
        LOG.info("Process batch item");       
        return parseLine((String) t);
    }

    private Training parseLine(String line) {
        Training t = new Training();
        String[] content = line.split(",");
        t.setCategory(content[0]);
        t.setTitle(content[1]);
        t.setDurationInDays(Integer.parseInt(content[2].trim()));
        t.setTrainer(content[3]);
        t.setMinParticipants(Integer.parseInt(content[4].trim()));
        t.setMaxParticipants(Integer.parseInt(content[5].trim()));
        return t;
    }    
}
