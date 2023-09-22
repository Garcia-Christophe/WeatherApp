package com.m2.tiila.weather.controller;

import com.m2.tiila.weather.business.CityBusiness;
import com.m2.tiila.weather.dto.City;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Controller;

import static com.m2.tiila.weather.mapper.CityMapper.toEntity;

@Controller
@Path("/city")
public class CityController {

  @Inject
  private CityBusiness cityBusiness;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createCity(City city) {
    this.cityBusiness.createCity(toEntity(city));
    return Response.ok().build();
  }
}
