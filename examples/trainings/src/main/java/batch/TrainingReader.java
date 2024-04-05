package batch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import jakarta.batch.api.chunk.AbstractItemReader;
import jakarta.inject.Named;

@Named(value = "trainingReader")
public class TrainingReader extends AbstractItemReader {

    static final Logger LOG = Logger.getLogger("TrainingReader");
   
    private BufferedReader reader;
    
    @Override
    public void open(Serializable checkpoint) throws Exception {
        LOG.info("open batch file ");
      
        reader = new BufferedReader(new FileReader("training-catalog-no-header.csv"));
    }

    @Override
    public String readItem() {
        LOG.info("parse next line");
        try {
            String line = reader.readLine();
            return line;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
