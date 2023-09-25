package com.m2.tiila.weather.repository;

import com.m2.tiila.weather.dto.City;
import com.m2.tiila.weather.entity.CityEntity;
import com.m2.tiila.weather.mapper.CityMapper;
import jakarta.inject.Inject;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CityRepository {

  private final static String SQL_INSERT_CITY = "INSERT INTO CITY (id, name, postCode, region, country) VALUES (:id, :name, :postCode, :region, :country)";
  private final static String SQL_UPDATE_CITY = "UPDATE CITY SET name=:name, postCode=:postCode, region=:region, country=:country WHERE id=:id";
  private final static String SQL_GET_ALL_CITY = "SELECT * FROM CITY";
  private final static String SQL_GET_CITY = "SELECT * FROM CITY WHERE id=:id";
  private final static String SQL_DELETE_CITY = "DELETE FROM CITY WHERE id=:id";

  @Inject
  private NamedParameterJdbcTemplate jdbcTemplate;

  public void createCity(City city) {
    var params = new HashMap<String, Object>();
    params.put("id", city.getId());
    params.put("name", city.getName());
    params.put("postCode", city.getPostCode());
    params.put("region", city.getRegion());
    params.put("country", city.getCountry());

    this.jdbcTemplate.update(SQL_INSERT_CITY, params);
  }

  public void updateCity(String id, City city) {
    var params = new HashMap<String, Object>();
    params.put("id", id);
    params.put("name", city.getName());
    params.put("postCode", city.getPostCode());
    params.put("region", city.getRegion());
    params.put("country", city.getCountry());

    this.jdbcTemplate.update(SQL_UPDATE_CITY, params);
  }

  public List<CityEntity> getCities() {
    List<City> cities = new ArrayList<City>(this.jdbcTemplate.query(SQL_GET_ALL_CITY, new BeanPropertyRowMapper<>(City.class)));
    return cities.stream().map(CityMapper::toEntity).toList();
  }

  public CityEntity getCity(String id) {
    var params = new HashMap<String, Object>();
    params.put("id", id);

    List<City> cities = new ArrayList<City>(this.jdbcTemplate.query(SQL_GET_CITY, params, new BeanPropertyRowMapper<>(City.class)));
    return cities.stream().map(CityMapper::toEntity).toList().get(0);
  }

  public void deleteCity(String id) {
    var params = new HashMap<String, Object>();
    params.put("id", id);

    this.jdbcTemplate.update(SQL_DELETE_CITY, params);
  }

}
