package com.github.kindrat.programmerwars.tanks.server.api;

import com.github.kindrat.programmerwars.tanks.server.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Service("roomService")
@Path("room")
public class RoomApi
{
   @Autowired
   private RoomService roomService;

   @GET
   @Path("list")
   public Response getRoomList(@QueryParam("filter") String filter){
      return null; //TODO
   }

   @GET
   @Path("{id}")
   public Response getRoom(){
      return null;//TODO
   }

   @POST
   @Path("new")
   public Response createRoom(){
      return null;//TODO
   }
}
