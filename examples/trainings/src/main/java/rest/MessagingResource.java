package rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import services.TrainingSenderService;

@Path("jms")
public class MessagingResource {

    @Inject
    private TrainingSenderService trainingSenderService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public String sendTrainingTitle(@QueryParam("title") String title) {
		if ((title == null) || title.trim().isEmpty())  {
			title = "test";
		}
        trainingSenderService.sendTrainingTitleMessage(title);
		return "sent";
    }
}
