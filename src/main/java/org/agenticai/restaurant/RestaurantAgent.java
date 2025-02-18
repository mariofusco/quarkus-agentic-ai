package org.agenticai.restaurant;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.ToolBox;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.SessionScoped;
import org.agenticai.restaurant.booking.BookingService;
import org.agenticai.restaurant.weather.WeatherService;

@RegisterAiService(modelName = "restaurant")
@SystemMessage("""
        You are an AI dealing with the booking for a restaurant.
        Today is: {current_date}.
        
        If the user specifies a preference (indoor/outdoor), you should book the table with the preference.  However, please check the weather forecast before booking the table.
        """)
@SessionScoped
public interface RestaurantAgent {


    @UserMessage("""
            You receive request from customer and need to book their table in the restaurant.
            Please be polite and try to handle the user request.
            
            Before booking the table, makes sure the user validates the date, name of the customer and party size.
            
            Request: {request}
            """)
    @ToolBox({BookingService.class, WeatherService.class})
    String handleRequest(String request);


}
