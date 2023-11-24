package com.m2.tiila.weather.controller;

import dto.weatherapi.City;
import jakarta.ws.rs.core.MediaType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CityControllerTest extends ControllerTest {
  @Test
  @DisplayName("Test creation city then 200")
  public void testCreationCityOK() {
    var city = new City();
    city.setPostCode(29200);
    city.setRegion("Bretagne");
    city.setName("Brest");
    city.setCountry("France");
    city.setId("123");

    given()
        .headers(headersMock())
        .contentType(MediaType.APPLICATION_JSON)
        .body(city)
        .post("/city")
        .then()
        .statusCode(HttpStatus.SC_OK);
  }

  @Test
  @DisplayName("Test creation city without headers then 403")
  public void testCreationCityWithoutHeaders403() {
    var city = new City();
    city.setPostCode(29200);
    city.setRegion("Bretagne");
    city.setName("Brest");
    city.setCountry("France");
    city.setId("123");

    given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(city)
        .post("/city")
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }
}
