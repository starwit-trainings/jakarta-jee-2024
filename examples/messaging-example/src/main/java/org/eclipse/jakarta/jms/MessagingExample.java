package org.eclipse.jakarta.jms;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("jms")
public class MessagingExample {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public String sendMessage(@QueryParam("name") String name) {
		return "test";
	}
}