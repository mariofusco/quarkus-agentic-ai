package org.agenticai.routing;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/expert")
public class ExpertAssistanceResource {

    private final RouterService routerService;

    public ExpertAssistanceResource(RouterService routerService) {
        this.routerService = routerService;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("request/{request}")
    public String assist(String request) {
        Log.infof("User request is: %s", request);
        return routerService.findExpert(request).apply(request);
    }
}
