package de.starwit.batch;

import jakarta.batch.api.AbstractBatchlet;
import jakarta.batch.runtime.context.JobContext;
import jakarta.batch.runtime.context.StepContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.starwit.batch.model.Training;

@Named
@Stateless
public class MyBatchlet extends AbstractBatchlet {

    static final Logger LOG = LoggerFactory.getLogger(MyBatchlet.class);

    @Inject 
    private StepContext stepContext;

    @Inject 
    private JobContext jobContext; 

	@Override
	public String process() {
        LOG.info("Starting simple batch");

        Properties jobParameters = jobContext.getProperties();
        boolean hasHeader = Boolean.parseBoolean((String) jobParameters.get("header"));

        LOG.info("current dir " + new File("").getAbsolutePath());

        List<Training> records = readFile("training-catalog.csv", hasHeader);

        LOG.info("parsed " + records.size() + " elements");

		return "COMPLETED";
	}

    private List<Training> readFile(String filename, boolean hasHeader) {
        List<Training> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            if (!hasHeader) {
                br.readLine(); // skip header
            }
            while ((line = br.readLine()) != null) {
                records.add(parseLine(line));
            }
        } catch(IOException e) {
            LOG.info("Couldn't read input file " + e.getMessage());
        }

        return records;
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
