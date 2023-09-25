package com.m2.tiila.weather.business;

import com.m2.tiila.weather.dto.City;
import com.m2.tiila.weather.entity.CityEntity;
import com.m2.tiila.weather.mapper.CityMapper;
import com.m2.tiila.weather.repository.CityRepository;
import jakarta.inject.Inject;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.m2.tiila.weather.mapper.CityMapper.toDto;

@Component
public class CityBusiness {
  @Inject
  private CityRepository cityRepository;

  public void createCity(CityEntity city) {
    this.cityRepository.createCity(toDto(city));
  }

  public void updateCity(String id, CityEntity city) {
    this.cityRepository.updateCity(id, toDto(city));
  }

  public List<City> getCities() {
    return this.cityRepository.getCities().stream().map(CityMapper::toDto).toList();
  }

  public City getCity(String id) {
    return toDto(this.cityRepository.getCity(id));
  }

  public void deleteCity(String id) {
    this.cityRepository.deleteCity(id);
  }
}
