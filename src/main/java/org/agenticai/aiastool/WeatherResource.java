package org.agenticai.aiastool;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/weather")
public class WeatherResource {

    private final WeatherForecastAgent agent;

    public WeatherResource(WeatherForecastAgent agent) {
        this.agent = agent;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("city/{city}")
    public String getWeather(String city) {
        return agent.chat(String.format("What is the weather in %s ?", city));
    }


}
