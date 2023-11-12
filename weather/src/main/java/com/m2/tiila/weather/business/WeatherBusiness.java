package com.m2.tiila.weather.business;

import com.m2.tiila.weather.entity.PrevisionEntity;
import com.m2.tiila.weather.repository.WeatherRepository;
import jakarta.inject.Inject;
import org.springframework.stereotype.Component;

import static com.m2.tiila.weather.mapper.WeatherMapper.toEntity;

@Component
public class WeatherBusiness {

  @Inject
  private WeatherRepository weatherRepository;

  public PrevisionEntity getCityWeather(String city) {
    return toEntity(weatherRepository.getCityWeather(city));
  }
}
