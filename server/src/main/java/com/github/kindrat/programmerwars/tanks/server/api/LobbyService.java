package com.github.kindrat.programmerwars.tanks.server.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.kindrat.programmerwars.tanks.common.network.RestUtil;
import com.github.kindrat.programmerwars.tanks.server.persistence.domain.Player;
import com.github.kindrat.programmerwars.tanks.server.services.PlayerService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Service("lobbyService")
@Path("lobby")
public class LobbyService {

   private static final Logger LOGGER = LoggerFactory.getLogger(LobbyService.class);

   @Autowired
   private PlayerService playerService;

   @GET
   @Path("status")
   public Response getStatus(){
      LOGGER.debug("");
      return RestUtil.buildResponse("OK", Response.Status.OK);
   }

   @GET
   @Path("auth")
   public Response getPlayer(@QueryParam("login") String login, @QueryParam("password") String password){
      Player player = playerService.getPlayerByCredentials(login, password);
      return RestUtil.buildResponse(player, Response.Status.OK);
   }

   @GET
   @Path("register")
   public Response getNewPlayer(@QueryParam("login") String login, @QueryParam("password") String password){
      Player player = playerService.getNewPlayer(login, password);
      return  RestUtil.buildResponse(player, Response.Status.OK);
   }

   @GET
   @Path("profile")
   public Response getProfile(){
      return null;//TODO
   }

   @POST
   @Path("profile")
   public Response setProfile(){
      return null;//TODO
   }
}
