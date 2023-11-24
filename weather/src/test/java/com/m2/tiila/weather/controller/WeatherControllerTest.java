package com.m2.tiila.weather.controller;

import com.m2.tiila.weather.repository.client.OpenWeatherClient;
import dto.openweatherapi.Main;
import dto.openweatherapi.Model200;
import dto.openweatherapi.Weather;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class WeatherControllerTest extends ControllerTest {

  @Inject
  private OpenWeatherClient openWeatherClient;

  @Test
  @DisplayName("Test previsions then OK")
  public void testPrevisionsThenOK() {
    var model200 = new Model200();
    var main = new Main();
    main.setTemp(new BigDecimal(40));
    Weather weather = new Weather();
    weather.setMain("Clouds");
    model200.setWeather(List.of(weather));
    model200.setMain(main);
    model200.setName("Brest");

    when(openWeatherClient.getWeather(any(), any())).thenReturn(model200);
    given()
        .headers(headersAdminMock())
        .contentType(MediaType.APPLICATION_JSON)
        .get("/previsions/Brest")
        .then()
        .statusCode(HttpStatus.SC_OK);
  }
}
