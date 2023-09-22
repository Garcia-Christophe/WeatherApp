package com.m2.tiila.weather.business;

import com.m2.tiila.weather.entity.CityEntity;
import com.m2.tiila.weather.repository.CityRepository;
import jakarta.inject.Inject;
import org.springframework.stereotype.Component;

import static com.m2.tiila.weather.mapper.CityMapper.toDto;

@Component
public class CityBusiness {
  @Inject
  private CityRepository cityRepository;

  public void createCity(CityEntity city) {
    this.cityRepository.createCity(toDto(city));
  }
}
