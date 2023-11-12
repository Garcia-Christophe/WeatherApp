package com.m2.tiila.weather.repository.client;

import dto.openweatherapi.Model200;
import feign.Param;
import feign.RequestLine;

public interface OpenWeatherClient {
  @RequestLine("GET /data/2.5/weather?q={cityName}&appid={token}&units=metric&lang=fr")
  Model200 getWeather(@Param("cityName") String cityName, @Param("token") String token);
}
