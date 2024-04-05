package services;

import jakarta.batch.operations.JobOperator;
import jakarta.batch.operations.JobSecurityException;
import jakarta.batch.operations.JobStartException;
import jakarta.batch.runtime.BatchRuntime;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.ejb.Timer;

@Stateless
public class CsvImportService {

    @Schedule(hour = "*", minute = "*", second = "*/5", info = "Every 5 seconds timer")
	protected void importTrainingCatalog(Timer timer) {
		try {
			JobOperator jo = BatchRuntime.getJobOperator();
			long id = jo.start("complexbatchlet", null);
		} catch (JobStartException | JobSecurityException ex) {
			System.out.println("Error submitting Job! " + ex.getMessage());
			ex.printStackTrace();
		}
	}
    
}
