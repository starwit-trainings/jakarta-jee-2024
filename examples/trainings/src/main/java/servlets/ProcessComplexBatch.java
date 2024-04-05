package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.batch.operations.JobOperator;
import jakarta.batch.operations.JobSecurityException;
import jakarta.batch.operations.JobStartException;
import jakarta.batch.runtime.BatchRuntime;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/ComplexBatchlet" })
public class ProcessComplexBatch extends HttpServlet {

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			JobOperator jo = BatchRuntime.getJobOperator();

			long id = jo.start("complexbatchlet", null);

			out.println("Batchlet submitted: " + id);

		} catch (JobStartException | JobSecurityException ex) {
			out.println("Error submitting Job! " + ex.getMessage());
			ex.printStackTrace();
		}
		out.flush();	
	}
    
}
