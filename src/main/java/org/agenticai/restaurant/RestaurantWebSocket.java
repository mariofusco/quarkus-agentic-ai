package org.agenticai.restaurant;


import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import org.jetbrains.annotations.Blocking;

@WebSocket(path = "/restaurant")
public class RestaurantWebSocket {

    private final RestaurantAgent restaurantAgent;

    public RestaurantWebSocket(RestaurantAgent restaurantAgent) {
        this.restaurantAgent = restaurantAgent;
    }

    @OnTextMessage
    String onMessage(String message) {
        return restaurantAgent.handleRequest(message);
    }

}
