package com.m2.tiila.weather.configuration;

import com.m2.tiila.weather.repository.client.OpenWeatherClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

@Configuration
@Profile("TEST")
public class FeignConfigMock {

  @Bean
  public OpenWeatherClient getOpenWeatherClient() {
    return mock(OpenWeatherClient.class);
  }

}
