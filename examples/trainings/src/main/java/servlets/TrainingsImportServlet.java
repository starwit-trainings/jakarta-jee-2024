package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class TrainingsImportServlet extends HttpServlet {
    private static final String CONTENT_DISPOSITION_KEY = "content-disposition";
    private static final String FILE_NAME_KEY = "filename";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        readFile(req);    
    }

    private void readFile(HttpServletRequest req) throws ServletException, IOException {
        System.out.println("Content Type: " + req.getContentType());
        System.out.println("Content Length: " + req.getContentLength());
        System.out.println("Parts: " + req.getParts().toString());
        System.out.println("Part 1: " + req.getPart("myfile"));
        // Read parts. Also can be read by directly calling req.getPart(fileName)
        for (Part part : req.getParts()) {
            System.out.println("File Name: " + getFileName(part));
            System.out.println("File Content: " + getTextFromPart(part));
        }    
    }

    private String getFileName(Part part) {
        for (String contentDisposition : part.getHeader(CONTENT_DISPOSITION_KEY).split(";")) {
            if (contentDisposition.trim().startsWith(FILE_NAME_KEY)) {
                return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private String getTextFromPart(Part part) throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        //skip first line
        String line = reader.readLine();

        while (line != null) {
            value.append(line + "\r\n ");
            line = reader.readLine();
        }
        return value.toString();
    }


    private String writeJson(Part part) throws IOException {
        Jsonb jsonb = JsonbBuilder.create();
    
        Pattern pattern = Pattern.compile(",");
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        //TODO create list of trainings
        String line = reader.readLine();
        //skip first line
        line = reader.readLine();
        while (line != null) {
            String[] x = pattern.split(line);
            //TODO add training to list
            line = reader.readLine();
        }

        //TODO jsonb.toJson(
        return "missing";
    }

    private String saveJson(Part part) throws IOException {
        Jsonb jsonb = JsonbBuilder.create();
    
        Pattern pattern = Pattern.compile(",");
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        String line = reader.readLine();
        //skip first line
        line = reader.readLine();
        while (line != null) {
            String[] x = pattern.split(line);
            //TODO create and save training
            //TODO return all trainings from database
            line = reader.readLine();
        }
        //TODO jsonb.toJson(
        return "missing";
    }

}