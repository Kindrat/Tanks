package com.github.kindrat.programmerwars.tanks.server.api;

import com.github.kindrat.programmerwars.tanks.common.network.RestUtil;
import com.github.kindrat.programmerwars.tanks.server.persistence.domain.Player;
import com.github.kindrat.programmerwars.tanks.server.services.LobbyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service("lobbyService")
@Path("lobby")
public class LobbyApi
{
   private static final Logger LOGGER = LoggerFactory.getLogger(LobbyApi.class);

   @Autowired
   private LobbyService lobbyService;

   @GET
   @Path("status")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getStatus()
   {
      return RestUtil.buildResponse("OK", Response.Status.OK);
   }

   @GET
   @Path("auth")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getPlayer(@QueryParam("login") String login, @QueryParam("password") String password)
   {
      LOGGER.debug("Received authorization request for username : {}, password : {}", login, password);
      try
      {
         Player player = lobbyService.getPlayerByCredentials(login, password);
         //TODO redirect headers
         return RestUtil.buildResponse(player, Response.Status.OK);
      }
      catch (Exception e)
      {
         return RestUtil.buildResponse("Could not register user. " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
      }
   }

   @GET
   @Path("register")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getNewPlayer(@QueryParam("login") String login, @QueryParam("password") String password)
   {
      LOGGER.debug("Received registration request for username : {}, password : {}", login, password);
      try
      {
         Player player = lobbyService.getNewPlayer(login, password);
         //TODO redirect headers
         return RestUtil.buildResponse(player, Response.Status.OK);
      }
      catch (Exception e)
      {
         return RestUtil.buildResponse("Could not register user. " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
      }
   }
}
