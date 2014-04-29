package com.github.kindrat.programmerwars.tanks.common.util;

import org.slf4j.bridge.SLF4JBridgeHandler;

import java.util.logging.Level;

public class LogUtils {

   public static void initLoggers() {
      SLF4JBridgeHandler.removeHandlersForRootLogger();
      SLF4JBridgeHandler.install();

      java.util.logging.Logger.getLogger("global").setLevel(Level.FINEST);
   }
}
