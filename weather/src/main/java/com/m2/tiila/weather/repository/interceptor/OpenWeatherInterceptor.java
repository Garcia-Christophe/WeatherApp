package com.m2.tiila.weather.repository.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OpenWeatherInterceptor implements RequestInterceptor {
  @Override
  public void apply(RequestTemplate template) {
    template.header("from", "weather-api");
    log.info("1 2 3 going to weather api ------> ");
  }
}
