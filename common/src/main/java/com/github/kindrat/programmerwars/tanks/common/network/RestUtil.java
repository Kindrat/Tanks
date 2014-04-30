package com.github.kindrat.programmerwars.tanks.common.network;

import javax.ws.rs.core.Response;
import java.util.List;

public class RestUtil {

   public static Response buildResponse(Object response, Response.Status code) {
      Response.ResponseBuilder webServiceResponseBuilder = Response.status(code);
      webServiceResponseBuilder.entity(response);
      return webServiceResponseBuilder.build();
   }

   public static Response buildResponse(Object response, Response.Status code, List<Header> headers) {
      Response.ResponseBuilder webServiceResponseBuilder = Response.status(code);
      webServiceResponseBuilder.entity(response);
      for (Header header : headers)
      {
         webServiceResponseBuilder.header(header.getName(), header.getValue());
      }
      return webServiceResponseBuilder.build();
   }
}
