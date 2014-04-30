package com.github.kindrat.programmerwars.tanks.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component("tankServer")
public class TankServer {

   private final Logger logger = LoggerFactory.getLogger(TankServer.class);

   @Value("${http.api.url}")
   private String url;

   @Value("${http.api}")
   private String packages;

   private HttpServer httpServer;

   public void start() {
      try{
         httpServer = GrizzlyHttpServerFactory.createHttpServer(new URI(url), getConfig());
      }catch (URISyntaxException e){
         logger.error("TankServer.start :\tillegal http server uri - check config.properties ("+url+")");
         System.exit(1);
      }
   }

   private ResourceConfig getConfig() {
      return new ResourceConfig().packages(true, packages);
   }

   public HttpServer getHttpServer() {
      return httpServer;
   }
}
