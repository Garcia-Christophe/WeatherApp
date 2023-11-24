package com.m2.tiila.weather.controller;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class HealthControllerTest extends ControllerTest {
  @Test
  @DisplayName("Test health check then ok")
  public void testHealthController() {
    given()
        .get("/health")
        .then()
        .statusCode(HttpStatus.SC_OK);
  }

  @Test
  @DisplayName("Test post health then 404")
  public void testPostHealth() {
    given()
        .post("/health")
        .then()
        .statusCode(405);
  }
}
