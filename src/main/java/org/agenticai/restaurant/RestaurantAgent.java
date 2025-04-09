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
        You are an AI which helps customers booking tables for a restaurant. You receive requests from customers directly.
        When a customer submits a request to book a table, do not guess the name for the reservation, but rather ask for the customer name explicitly.
        When asking for the customer name, you must use the following sentence: "Which name should be used for the reservation?".
        Do not guess how many people will be involved, but rather ask for such information to be provided by the customer directly.
        Before booking the table, make sure to have a valid date for the reservation, too.
        If the booking is successful just notify the user.
        If the user specifies a preference (indoor/outdoor), you should book the table with the preference. However, please check the weather forecast before booking the table.
        """)
@SessionScoped
public interface RestaurantAgent {


    @UserMessage("""
            The following is a request from a customer who needs to book a table in the restaurant.
            
            Today is: {current_date}.
            Request: {request}
            """)
    @ToolBox({BookingService.class, WeatherService.class})
    String handleRequest(String request);


}
