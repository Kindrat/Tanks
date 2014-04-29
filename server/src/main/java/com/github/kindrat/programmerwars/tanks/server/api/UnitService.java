package com.github.kindrat.programmerwars.tanks.server.api;

import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Service("unitService")
@Path("unit")
public class UnitService {

   @GET
   @Path("list")
   public Response getUnitList(@QueryParam("filter") String filter){
      return null;//TODO
   }

   @POST
   @Path("{id}")
   public Response updateUnit(){
      return null;//TODO
   }

   @DELETE
   @Path("{id}")
   public Response deleteUnit(){
      return null;//TODO
   }

   @POST
   @Path("new")
   public Response createUnit(){
      return null;//TODO
   }
}
