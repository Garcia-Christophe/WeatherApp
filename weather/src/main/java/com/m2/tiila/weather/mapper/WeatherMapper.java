package com.m2.tiila.weather.mapper;

import com.m2.tiila.weather.entity.PrevisionEntity;
import dto.openweatherapi.Model200;
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
    previsionEntity.setState(WeatherState.valueOf(dto.getWeather().get(0).getMain().toUpperCase()));

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
