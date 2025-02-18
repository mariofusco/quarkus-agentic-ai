package org.agenticai.restaurant.weather;

import dev.langchain4j.agent.tool.Tool;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.agenticai.aiastool.weather.DailyWeatherData;
import org.agenticai.aiastool.weather.WeatherForecast;
import org.agenticai.aiastool.weather.WeatherForecastService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class WeatherService {


    @RestClient
    WeatherForecastService weatherForecastService;

    @ConfigProperty(name = "restaurant.location.latitude")
    double latitude;

    @ConfigProperty(name = "restaurant.location.longitude")
    double longitude;

    @Tool("Provides weather forecast for a given day (0 for today, 1 for tomorrow, etc.). Predictions are only available for 6 days.")
    public DailyWeatherData getWeatherForecast(int day) {
        WeatherForecast forecast = weatherForecastService.forecast(latitude, longitude);
        Log.infof("Weather forecast: %s", forecast);
        int code = forecast.daily().weather_code()[day];
        double precipitation = forecast.daily().precipitation_sum()[day];
        double temp_max = forecast.daily().temperature_2m_max()[day];
        double temp_min = forecast.daily().temperature_2m_min()[day];
        double wind_speed = forecast.daily().wind_speed_10m_max()[day];
        return new DailyWeatherData(temp_max, temp_min, precipitation, wind_speed, code);
    }

}
