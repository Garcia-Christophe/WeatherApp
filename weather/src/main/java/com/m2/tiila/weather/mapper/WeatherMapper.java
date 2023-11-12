package com.m2.tiila.weather.mapper;

import com.m2.tiila.weather.entity.PrevisionEntity;
import dto.openweatherapi.Model200;
import dto.openweatherapi.Weather;
import dto.weatherapi.Prevision;
import dto.weatherapi.WeatherState;

import java.time.ZoneId;
import java.util.Date;

public class WeatherMapper {

  public static PrevisionEntity toEntity(Model200 dto) {
    PrevisionEntity previsionEntity = new PrevisionEntity();
    previsionEntity.setCity(dto.getName());
    previsionEntity.setDate(new Date().toInstant());
    previsionEntity.setTemperature(dto.getMain().getTemp().floatValue());
    Weather w = dto.getWeather().get(0);
    switch (w.getMain()) {
      case "Clouds" -> previsionEntity.setState(WeatherState.NUAGEUX);
      case "Rain" -> previsionEntity.setState(WeatherState.PLUVIEUX);
      case "Snow" -> previsionEntity.setState(WeatherState.NEIGEUX);
      default -> System.out.println("State invalide");
    }

    return previsionEntity;
  }

  public static Prevision toDto(PrevisionEntity entity) {
    Prevision prevision = new Prevision();
    prevision.setCity(entity.getCity());
    prevision.setDate(entity.getDate().atZone(ZoneId.systemDefault()).toLocalDate());
    prevision.setTemperature((int) entity.getTemperature());
    prevision.setState(entity.getState());

    return prevision;
  }
}
