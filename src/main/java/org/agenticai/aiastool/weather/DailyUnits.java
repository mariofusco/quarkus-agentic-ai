package org.agenticai.aiastool.weather;

public record DailyUnits(String time,
                         String temperature_2m_max,
                         String precipitation_sum,
                         String wind_speed_10m_max) {
}
