package ua.pirateparty.games.tanks.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.pirateparty.games.tanks.common.util.LogUtils;

import java.net.URI;
import java.net.URISyntaxException;


public class TankServer extends ResourceConfig {

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
       new ClassPathXmlApplicationContext("applicationContext.xml");
       LogUtils.initLoggers();

       final HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(new URI("http://localhost:8080"), getConfig());

       // register shutdown hook
       Runtime.getRuntime().addShutdownHook(
               new Thread(new Runnable()
               {
                  @Override
                  public void run()
                  {
                     httpServer.shutdownNow();
                  }
               }, "shutdownHook")
       );

       // prevent auto-exiting to OS
       Thread.currentThread().join();
    }

   private static ResourceConfig getConfig()
   {
      return new ResourceConfig()
              .packages(true, "ua.pirateparty.games.tanks.server.services");
   }
}
