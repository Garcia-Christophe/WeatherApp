package com.m2.tiila.weather.repository;

import com.m2.tiila.weather.repository.client.OpenWeatherClient;
import dto.openweatherapi.Model200;
import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeatherRepository {

  @Inject
  private OpenWeatherClient openWeatherClient;

  @Value("${spring.secrets.openweather.token}")
  private String token;

  public Model200 getCityWeather(String cityName) {
    return openWeatherClient.getWeather(cityName, token);
  }
}
