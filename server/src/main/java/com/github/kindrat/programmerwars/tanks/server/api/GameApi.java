package com.github.kindrat.programmerwars.tanks.server.api;

import org.springframework.stereotype.Service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Service("gameService")
@Path("game")
public class GameApi
{

   @POST
   @Path("action")
   public Response getGameResult(){
      return null;//TODO
   }
}
