package org.agenticai.restaurant;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.ToolBox;
import jakarta.enterprise.context.SessionScoped;
import org.agenticai.restaurant.booking.BookingService;
import org.agenticai.restaurant.weather.WeatherService;

@RegisterAiService(modelName = "tool-use")
@SystemMessage("""
        You are an AI dealing with the booking for a restaurant.
        Do not invent the customer name or party size, but explicitly ask for them if not provided. 
        If the user specifies a preference (indoor/outdoor), you should book the table with the preference.  However, please check the weather forecast before booking the table.
        """)
@SessionScoped
public interface RestaurantAgent {


    @UserMessage("""
            You receive request from customer and need to book their table in the restaurant.
            Please be polite and try to handle the user request.
            
            Before booking the table, makes sure to have valid date for the reservation, and that the user explicitly provided his name and party size.
            If the booking is successful just notify the user.
            
            Today is: {current_date}.
            Request: {request}
            """)
    @ToolBox({BookingService.class, WeatherService.class})
    String handleRequest(String request);


}
