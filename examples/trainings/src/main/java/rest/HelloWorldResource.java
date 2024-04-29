package rest;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import entities.Hello;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;


@Path("hello")
public class HelloWorldResource {

	@Inject
	@ConfigProperty(name="default-name")
	private String defaultName;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Hello hello(@QueryParam("name") String name) {
		if ((name == null) || name.trim().isEmpty())  {
			name = defaultName;
		}

		return new Hello(name);
	}

	@GET
	@Path("/count")
	public String appCount() {
		return "not implemented";
	}
}