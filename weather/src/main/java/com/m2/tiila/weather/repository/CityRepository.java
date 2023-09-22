package com.m2.tiila.weather.repository;

import com.m2.tiila.weather.dto.City;
import jakarta.inject.Inject;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CityRepository {

  private final static String SQL_INSERT_CITY = "INSERT INTO CITY (id, name, postCode, region, country) VALUES (:id, :name, :postCode, :region, :country)";

  @Inject
  private NamedParameterJdbcTemplate jdbcTemplate;

  public void createCity(City city) {
    var params = new HashMap();
    params.put("id", city.getId());
    params.put("name", city.getName());
    params.put("postCode", city.getPostCode());
    params.put("region", city.getRegion());
    params.put("country", city.getCountry());

    this.jdbcTemplate.update(SQL_INSERT_CITY, params);
  }
}
