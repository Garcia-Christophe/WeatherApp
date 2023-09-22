package com.m2.tiila.weather.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class City implements Serializable {

  private String id;
  private String name;
  private Integer postCode;
  private String region;
  private String country;
}
