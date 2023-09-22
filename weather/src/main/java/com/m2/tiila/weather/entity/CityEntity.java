package com.m2.tiila.weather.entity;


import lombok.Data;

@Data
public class CityEntity {

  private String id;
  private String name;
  private Integer postCode;
  private String region;
  private String country;
}
