package com.m2.tiila.weather.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("TEST")
public class ControllerTest {

  @LocalServerPort
  protected int port;

  @BeforeAll
  public void setUp() {
    RestAssured.basePath = "/api/v1";
    RestAssured.port = port;
  }

  protected Map<String, String> headersMock() {
    var headers = new HashMap<String, String>();
    headers.put("Authentification", "toto");
    return headers;
  }

  protected Map<String, String> headersAdminMock() {
    var headers = new HashMap<String, String>();
    headers.put("is-admin", "true");
    return headers;
  }

}
