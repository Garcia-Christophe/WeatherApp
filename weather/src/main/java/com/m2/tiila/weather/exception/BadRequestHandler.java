package com.m2.tiila.weather.exception;

import dto.weatherapi.Error;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BadRequestHandler implements ExceptionMapper<FunctionalException> {
  @Override
  public Response toResponse(FunctionalException exception) {
    var error = new Error();
    error.setCode(exception.getCode());
    error.setDescription(exception.getMessage());
    return Response.status(400).entity(error).build();
  }
}
