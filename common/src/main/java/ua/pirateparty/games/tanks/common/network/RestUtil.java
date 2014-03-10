package ua.pirateparty.games.tanks.common.network;

import javax.ws.rs.core.Response;

public class RestUtil {

   public static Response buildResponse(Object response, Response.Status code) {
      Response.ResponseBuilder webServiceResponseBuilder = Response.status(code);
      webServiceResponseBuilder.entity(response);
      return webServiceResponseBuilder.build();
   }
}
