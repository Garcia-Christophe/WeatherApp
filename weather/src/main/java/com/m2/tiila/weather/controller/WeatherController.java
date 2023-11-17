package com.m2.tiila.weather.controller;

import com.m2.tiila.weather.business.WeatherBusiness;
import com.m2.tiila.weather.entity.PrevisionEntity;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Controller;

import static com.m2.tiila.weather.mapper.WeatherMapper.toDto;

@Controller
@Path("/previsions")
public class WeatherController {

  @Inject
  private WeatherBusiness weatherBusiness;

  @Path("/{city}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response previsions(@PathParam("city") String city, @HeaderParam("is-admin") boolean isAdmin) {
    PrevisionEntity previsionEntity = weatherBusiness.getCityWeather(city, isAdmin);
    if (previsionEntity == null) {
      return Response.status(300, "Tu n'es pas admin !").build();
    }
    return Response.ok(toDto(previsionEntity)).build();
  }
}
