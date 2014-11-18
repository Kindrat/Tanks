package com.github.kindrat.programmerwars.tanks.common.util;

import org.slf4j.bridge.SLF4JBridgeHandler;

import java.util.logging.Level;

import static java.util.logging.Logger.*;

public final class LogUtils {

   private LogUtils() {
      throw new AssertionError("Instantiating utility class");
   }

   public static void initLoggers() {
      SLF4JBridgeHandler.removeHandlersForRootLogger();
      SLF4JBridgeHandler.install();

      getLogger("global").setLevel(Level.FINEST);
   }
}
