package com.m2.tiila.weather.entity;

import dto.weatherapi.WeatherState;
import lombok.Data;

import java.time.Instant;

@Data
public class PrevisionEntity {

  private Instant date;
  private String city;
  private WeatherState state;
  private float temperature;
}
