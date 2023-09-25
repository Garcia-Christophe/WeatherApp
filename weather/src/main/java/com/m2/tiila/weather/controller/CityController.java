package com.m2.tiila.weather.controller;

import com.m2.tiila.weather.business.CityBusiness;
import com.m2.tiila.weather.dto.City;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Controller;

import java.util.List;

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
    return Response.ok("Insertion has been successful!").build();
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Response updateCity(@PathParam("id") Integer id, City city) {
    this.cityBusiness.updateCity(id.toString(), toEntity(city));
    return Response.ok("Update has been successful!").build();
  }

  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public List<City> getCities() {
    return this.cityBusiness.getCities();
  }

  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public City getCity(@PathParam("id") Integer id) {
    return this.cityBusiness.getCity(id.toString());
  }

  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Response deleteCity(@PathParam("id") Integer id) {
    this.cityBusiness.deleteCity(id.toString());
    return Response.ok("Deletion has been successful!").build();
  }
}
