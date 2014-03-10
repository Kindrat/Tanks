package ua.pirateparty.games.tanks.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pirateparty.games.tanks.common.network.RestUtil;
import ua.pirateparty.games.tanks.server.persistence.domain.Player;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Service("gameService")
@Path("/game")
public class GameService {

   private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

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
}
