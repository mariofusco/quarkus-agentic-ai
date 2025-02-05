package org.agenticai.searchastool;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.agenticai.aiastool.WeatherForecastAgent;

@Path("/ask")
public class AgenticChatbotResource {

    @Inject
    IntelligentAgent agent;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{question}")
    public String ask(String question) {
        return agent.chat(question);
    }


}
