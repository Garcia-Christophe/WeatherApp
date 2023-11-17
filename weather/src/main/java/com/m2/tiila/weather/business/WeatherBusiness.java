package com.m2.tiila.weather.business;

import com.m2.tiila.weather.entity.PrevisionEntity;
import com.m2.tiila.weather.repository.WeatherRepository;
import dto.weatherapi.City;
import jakarta.inject.Inject;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.m2.tiila.weather.mapper.WeatherMapper.toEntity;

@Component
public class WeatherBusiness {

  @Inject
  private WeatherRepository weatherRepository;
  @Inject
  private CityBusiness cityBusiness;

  public PrevisionEntity getCityWeather(String city, boolean isAdmin) {
    if (isAdmin) {
      return toEntity(weatherRepository.getCityWeather(city));
    } else {
      List<City> cities = cityBusiness.getCities();
      City cityOfDatabase = cities.stream()
          .filter(it -> it.getName().equals(city))
          .findAny()
          .orElse(null);
      if (cityOfDatabase != null) {
        return toEntity(weatherRepository.getCityWeather(city));
      }
      return null;
    }
  }
}
