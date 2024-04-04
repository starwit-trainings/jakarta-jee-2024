package rest;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import cdibeans.ApplicationScopedCounter;
import cdibeans.RequestScopedCounter;
import cdibeans.SessionScopedCounter;
import entities.Hello;
import jakarta.enterprise.context.ApplicationScoped;
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

	@Inject
	private ApplicationScopedCounter applicationScopedCounter;

	@Inject
	private SessionScopedCounter sessionScopedCounter;

	@Inject
	private RequestScopedCounter requestScopedCounter;

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
		applicationScopedCounter.count();
		sessionScopedCounter.count();
		requestScopedCounter.count();
		String result = "applicationScopedCounter: " + applicationScopedCounter.getCounter() + "\r\n";
		result = result + "sessionScopedCounter: " + sessionScopedCounter.getCounter() + "\r\n";
		result = result + "requestScopedCounter: " + requestScopedCounter.getCounter() + "\r\n";
		return result;
	}
}