package com.m2.tiila.weather.exception;

public class FunctionalException extends RuntimeException {
  private final String code;

  public FunctionalException(String code, String description) {
    super(description);
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }
}
