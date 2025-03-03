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
        Do not guess the customer name or the party size, but explicitly ask for such information if not provided. 
        If the user specifies a preference (indoor/outdoor), you should book the table with the preference.  However, please check the weather forecast before booking the table.
        """)
@SessionScoped
public interface RestaurantAgent {


    @UserMessage("""
            You receive request from customer and need to book their table in the restaurant.
            Please be polite and try to handle the user request.
            
            Before booking the table, makes sure to have valid date for the reservation, and that you asked the customer name and the number of participants to the party, which must be provided by the customer explicitly.
            When asking for the customer name, you can only use the following sentence, "Which name should be used for the reservation?", and nothing else.
            If the booking is successful just notify the user.
            
            Today is: {current_date}.
            Request: {request}
            """)
    @ToolBox({BookingService.class, WeatherService.class})
    String handleRequest(String request);


}
