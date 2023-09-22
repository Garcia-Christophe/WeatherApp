package com.m2.tiila.weather.mapper;

import com.m2.tiila.weather.dto.City;
import com.m2.tiila.weather.entity.CityEntity;

public class CityMapper {

  public static City toDto(CityEntity entity) {
    City dto = new City();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setPostCode(entity.getPostCode());
    dto.setRegion(entity.getRegion());
    dto.setCountry(entity.getCountry());

    return dto;
  }

  public static CityEntity toEntity(City dto) {
    CityEntity entity = new CityEntity();
    entity.setId(dto.getId());
    entity.setName(dto.getName());
    entity.setPostCode(dto.getPostCode());
    entity.setRegion(dto.getRegion());
    entity.setCountry(dto.getCountry());

    return entity;
  }
}
