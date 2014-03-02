package ua.pirateparty.games.tanks.server.services;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Component
@Path("/player")
public class GameService {

   @GET
   @Path("status")
   public Response getStatus(){
      return Response.status(200).entity("OK").build();
   }
}
