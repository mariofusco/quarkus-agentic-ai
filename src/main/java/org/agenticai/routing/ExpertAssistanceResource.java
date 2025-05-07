package org.agenticai.routing;

import io.quarkus.logging.Log;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/expert")
public class ExpertAssistanceResource {

    private final RouterService routerService;

    private final ExpertsSelectorAgent expertsSelectorAgent;

    public ExpertAssistanceResource(RouterService routerService, ExpertsSelectorAgent expertsSelectorAgent) {
        this.routerService = routerService;
        this.expertsSelectorAgent = expertsSelectorAgent;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("request/{request}")
    public String request(String request) {
        Log.infof("User request is: %s", request);
        return routerService.findExpert(request).apply(request);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("request-agentic/{request}")
    public String requestAgentic(String request) {
        Log.infof("User request is: %s", request);
        return expertsSelectorAgent.askToExpert(request);
    }
}
