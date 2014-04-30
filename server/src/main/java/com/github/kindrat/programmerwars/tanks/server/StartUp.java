package com.github.kindrat.programmerwars.tanks.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.github.kindrat.programmerwars.tanks.common.util.LogUtils;

public class StartUp {

   public static void main(String[] args) throws InterruptedException {
      LogUtils.initLoggers();
      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
      final TankServer server = context.getBean("tankServer", TankServer.class);
      server.start();

      Runtime.getRuntime().addShutdownHook(
              new Thread(new Runnable() {
                 @Override
                 public void run() {
                    server.getHttpServer().shutdownNow();
                 }
              }, "shutdownHook")
      );

      Thread.currentThread().join();
   }
}
